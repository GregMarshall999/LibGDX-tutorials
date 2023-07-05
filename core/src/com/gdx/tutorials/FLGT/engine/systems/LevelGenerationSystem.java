package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gdx.tutorials.FLGT.engine.components.PlayerComponent;
import com.gdx.tutorials.FLGT.engine.components.TransformComponent;
import com.gdx.tutorials.FLGT.engine.factory.LevelFactory;

public class LevelGenerationSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    private LevelFactory factory;

    public LevelGenerationSystem(LevelFactory factory) {
        super(Family.all(PlayerComponent.class).get());
        this.factory = factory;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = transformMapper.get(entity);
        int currentPosition = (int) transformComponent.position.y;
        if((currentPosition + 7) > factory.currentLevel)
            factory.generateLevel(currentPosition + 7);
    }
}
