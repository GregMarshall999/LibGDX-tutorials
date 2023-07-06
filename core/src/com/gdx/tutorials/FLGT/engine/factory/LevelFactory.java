package com.gdx.tutorials.FLGT.engine.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.tutorials.FLGT.engine.FLGTUtils;
import com.gdx.tutorials.FLGT.engine.ai.SteeringPresets;
import com.gdx.tutorials.FLGT.engine.components.*;
import com.gdx.tutorials.FLGT.engine.components.constants.BulletOwner;
import com.gdx.tutorials.FLGT.engine.effects.FLGTEffect;
import com.gdx.tutorials.FLGT.engine.effects.ParticleEffectManager;
import com.gdx.tutorials.FLGT.engine.noise.OpenSimplexNoise;
import com.gdx.tutorials.FLGT.game.FLGTAssets;
import com.gdx.tutorials.FLGT.game.FLGTContactListener;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.*;
import static com.gdx.tutorials.FLGT.engine.components.constants.EnemyType.CLOUD;
import static com.gdx.tutorials.FLGT.engine.components.constants.SteeringState.WANDER;
import static com.gdx.tutorials.FLGT.engine.effects.FLGTEffect.*;
import static com.gdx.tutorials.FLGT.engine.factory.MaterialType.STONE;

public class LevelFactory {
    private BodyFactory bodyFactory;
    private PooledEngine engine;
    private TextureRegion floorTex;
    private TextureRegion enemyTex;
    private TextureRegion waterTex;
    private TextureRegion platformTex;
    private TextureRegion bulletTex;
    private TextureAtlas atlas;
    private OpenSimplexNoise openSim;
    private ParticleEffectManager pem;

    public Entity player;
    public int currentLevel = 0;
    public World world;
    public FLGTAssets assman;

    public LevelFactory(PooledEngine en, FLGTAssets assMan) {
        engine = en;
        this.assman = assMan;
        this.atlas = assMan.get(FLGTAssets.GAME_IMAGES, TextureAtlas.class);;
        floorTex = atlas.findRegion("reallybadlydrawndirt");
        enemyTex = atlas.findRegion("waterdrop");

        waterTex  = atlas.findRegion("water");
        bulletTex = FLGTUtils.makeTextureRegion(10,10,"444444FF");
        platformTex = atlas.findRegion("platform");
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new FLGTContactListener());
        bodyFactory = BodyFactory.getInstance(world);

        openSim = new OpenSimplexNoise(MathUtils.random(2000l));

        pem = new ParticleEffectManager();
        pem.addParticleEffect(FIRE, assMan.get("particles/fire.pe", ParticleEffect.class),1f/128f);
        pem.addParticleEffect(WATER, assMan.get("particles/water.pe", ParticleEffect.class),1f/8f);
        pem.addParticleEffect(SMOKE, assMan.get("particles/smoke.pe", ParticleEffect.class),1f/64f);
    }

    public void generateLevel(int yLevel) {
        while (yLevel > currentLevel) {
            for (int i = 1; i < 5; i++)
                generateSingleColumn(i);

            currentLevel++;
        }
    }

    private float genNForL(int level, int height){
        return (float)openSim.eval(height, level);
    }

    private void generateSingleColumn(int i){
        int offset = 10 * i;
        int range = 15;
        if(genNForL(i,currentLevel) > -0.5f){
            createPlatform(genNForL(i * 100,currentLevel) * range + offset ,currentLevel * 2);

            if(genNForL(i * 200,currentLevel) > 0.3f)
                createBouncyPlatform(genNForL(i * 100,currentLevel) * range + offset,currentLevel * 2);
            if(currentLevel > 7)
                if(genNForL(i * 300,currentLevel) > 0.2f)
                    createEnemy(enemyTex,genNForL(i * 100,currentLevel) * range + offset,currentLevel * 2 + 1);
            if(currentLevel > 0)
                if(genNForL(i * 400,currentLevel) > 0.3f)
                    createSeeker(genNForL(i * 100,currentLevel) * range + offset,currentLevel * 2 + 1);
        }
    }

    public void createPlatform(float x, float y) {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        body.body = bodyFactory.makeBoxPolyBody(x, y, 3f, 0.3f, STONE, StaticBody);
        body.body.setUserData(entity);
        entity.add(body);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = platformTex;
        entity.add(texture);

        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = TypeComponent.SCENERY;
        entity.add(type);

        TransformComponent trans = engine.createComponent(TransformComponent.class);
        trans.position.set(x, y, 0);
        entity.add(trans);

        engine.addEntity(entity);

    }

    public Entity createBouncyPlatform(float x, float y) {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        body.body = bodyFactory.makeBoxPolyBody(x, y, 1f, 1f, STONE, StaticBody);
        bodyFactory.makeAllFixturesSensors(body.body);

        //todo get another texture and anim for springy action
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = platformTex;

        TransformComponent trans = engine.createComponent(TransformComponent.class);
        trans.position.set(x, y, 0);
        entity.add(trans);

        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = TypeComponent.SPRING;

        body.body.setUserData(entity);
        entity.add(body);
        entity.add(texture);
        entity.add(type);
        engine.addEntity(entity);

        return entity;
    }

    public void createFloor() {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);

        position.position.set(20,0,0);
        texture.region = floorTex;
        type.type = TypeComponent.SCENERY;
        body.body = bodyFactory.makeBoxPolyBody(20, -16, 46, 32, STONE, StaticBody);

        entity.add(body);
        entity.add(texture);
        entity.add(position);
        entity.add(type);

        body.body.setUserData(entity);

        engine.addEntity(entity);
    }

    public Entity createEnemy(TextureRegion tex, float x, float y) {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        EnemyComponent enemy = engine.createComponent(EnemyComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);

        body.body = bodyFactory.makeCirclePolyBody(x,y,1, STONE, KinematicBody,true);
        position.position.set(x,y,0);
        texture.region = tex;
        enemy.xPosCenter = x;
        type.type = TypeComponent.ENEMY;
        body.body.setUserData(entity);

        entity.add(colComp);
        entity.add(body);
        entity.add(position);
        entity.add(texture);
        entity.add(enemy);
        entity.add(type);

        engine.addEntity(entity);

        return entity;
    }

    public Entity createPlayer(OrthographicCamera camera) {
        Entity entity = engine.createEntity();
        BodyComponent b2dbody = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        AnimationComponent animCom = engine.createComponent(AnimationComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);
        SteeringComponent scom = engine.createComponent(SteeringComponent.class);

        player.camera = camera;
        b2dbody.body = bodyFactory.makeCirclePolyBody(10,1,1, STONE, DynamicBody,true);
        Animation anim = new Animation(0.1f,atlas.findRegions("flame_a"));
        animCom.animations.put(StateComponent.STATE_NORMAL, anim);
        animCom.animations.put(StateComponent.STATE_MOVING, anim);
        animCom.animations.put(StateComponent.STATE_JUMPING, anim);
        animCom.animations.put(StateComponent.STATE_FALLING, anim);
        animCom.animations.put(StateComponent.STATE_HIT, anim);

        position.position.set(10,1,0);
        texture.region = atlas.findRegion("player");
        type.type = TypeComponent.PLAYER;
        stateCom.set(StateComponent.STATE_NORMAL);
        b2dbody.body.setUserData(entity);

        scom.body = b2dbody.body;

        entity.add(b2dbody);
        entity.add(position);
        entity.add(texture);
        entity.add(animCom);
        entity.add(player);
        entity.add(colComp);
        entity.add(type);
        entity.add(stateCom);
        entity.add(scom);

        engine.addEntity(entity);
        this.player = entity;
        return entity;
    }

    public void createWalls(TextureRegion tex){
        for(int i = 0; i < 2; i++){
            Entity entity = engine.createEntity();
            BodyComponent body = engine.createComponent(BodyComponent.class);
            TransformComponent position = engine.createComponent(TransformComponent.class);
            TextureComponent texture = engine.createComponent(TextureComponent.class);
            TypeComponent type = engine.createComponent(TypeComponent.class);
            WallComponent wallComp = engine.createComponent(WallComponent.class);

            body.body = bodyFactory.makeBoxPolyBody((i * 40),30,1,60, STONE, KinematicBody,true);
            position.position.set((i * 40), 30, 0);
            texture.region = tex;
            type.type = TypeComponent.SCENERY;

            entity.add(body);
            entity.add(position);
            entity.add(texture);
            entity.add(type);
            entity.add(wallComp);
            body.body.setUserData(entity);

            engine.addEntity(entity);
        }
    }

    public Entity createWaterFloor() {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        WaterFloorComponent waterFloor = engine.createComponent(WaterFloorComponent.class);

        type.type = TypeComponent.ENEMY;
        texture.region = waterTex;
        body.body = bodyFactory.makeBoxPolyBody(20,-40,40,44, STONE, KinematicBody,true);
        position.position.set(20,-15,0);
        entity.add(body);
        entity.add(position);
        entity.add(texture);
        entity.add(type);
        entity.add(waterFloor);

        body.body.setUserData(entity);

        engine.addEntity(entity);

        makeParticleEffect(WATER, body,-15,22);
        return entity;
    }

    public Entity createBullet(float x, float y, float xVel, float yVel, BulletOwner own){
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        AnimationComponent animCom = engine.createComponent(AnimationComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        BulletComponent bul = engine.createComponent(BulletComponent.class);

        bul.owner = own;

        body.body = bodyFactory.makeCirclePolyBody(x,y,0.5f, STONE, DynamicBody,true);
        body.body.setBullet(true);
        bodyFactory.makeAllFixturesSensors(body.body);
        position.position.set(x,y,0);
        texture.region = bulletTex;
        Animation anim = new Animation(0.05f,FLGTUtils.spriteSheetToFrames(atlas.findRegion("FlameSpriteAnimation"), 7, 1));
        anim.setPlayMode(Animation.PlayMode.LOOP);
        animCom.animations.put(0, anim);

        type.type = TypeComponent.BULLET;
        body.body.setUserData(entity);
        bul.xVel = xVel;
        bul.yVel = yVel;

        bul.particleEffect = makeParticleEffect(FIRE,body);

        entity.add(bul);
        entity.add(colComp);
        entity.add(body);
        entity.add(position);
        entity.add(texture);
        entity.add(animCom);
        entity.add(stateCom);
        entity.add(type);

        engine.addEntity(entity);

        return entity;
    }

    public Entity makeParticleEffect(FLGTEffect type, float x, float y){
        Entity entPE = engine.createEntity();
        ParticleEffectComponent pec = engine.createComponent(ParticleEffectComponent.class);
        pec.particleEffect = pem.getPooledParticleEffect(type);
        pec.particleEffect.setPosition(x, y);
        entPE.add(pec);
        engine.addEntity(entPE);
        return entPE;
    }

    public Entity makeParticleEffect(FLGTEffect type, BodyComponent body){
        return makeParticleEffect(type,body,0,0);
    }

    public Entity makeParticleEffect(FLGTEffect type, BodyComponent body, float xo, float yo){
        Entity entPE = engine.createEntity();
        ParticleEffectComponent pec = engine.createComponent(ParticleEffectComponent.class);
        pec.particleEffect = pem.getPooledParticleEffect(type);
        pec.particleEffect.setPosition(body.body.getPosition().x, body.body.getPosition().y);
        pec.particleEffect.getEmitters().first().setAttached(true);
        pec.xOffset = xo;
        pec.yOffset = yo;
        pec.isattached = true;
        pec.particleEffect.getEmitters().first().setContinuous(true);
        pec.attachedBody = body.body;
        entPE.add(pec);
        engine.addEntity(entPE);
        return entPE;
    }

    public void removeEntity(Entity ent){
        engine.removeEntity(ent);
    }

    public Entity createSeeker(float x, float y) {
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);
        EnemyComponent enemy = engine.createComponent(EnemyComponent.class);
        SteeringComponent scom = engine.createComponent(SteeringComponent.class);


        body.body = bodyFactory.makeCirclePolyBody(x,y,1, STONE, DynamicBody,true);
        body.body.setGravityScale(0f);
        body.body.setLinearDamping(0.3f);

        position.position.set(x,y,0);
        texture.region = atlas.findRegion("player");
        type.type = TypeComponent.ENEMY;
        stateCom.set(StateComponent.STATE_NORMAL);
        body.body.setUserData(entity);
        scom.body = body.body;
        enemy.enemyType = CLOUD;

        scom.steeringBehavior  = SteeringPresets.getWander(scom);
        scom.currentMode = WANDER;

        entity.add(body);
        entity.add(position);
        entity.add(texture);
        entity.add(colComp);
        entity.add(type);
        entity.add(enemy);
        entity.add(stateCom);
        entity.add(scom);

        engine.addEntity(entity);
        return entity;
    }
}
