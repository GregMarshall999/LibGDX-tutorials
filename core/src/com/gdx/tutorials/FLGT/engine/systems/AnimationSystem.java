package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.tutorials.FLGT.engine.components.AnimationComponent;
import com.gdx.tutorials.FLGT.engine.components.StateComponent;
import com.gdx.tutorials.FLGT.engine.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {
    ComponentMapper<TextureComponent> textureMapper;
    ComponentMapper<AnimationComponent> animationMapper;
    ComponentMapper<StateComponent> stateMapper;

    public AnimationSystem() {
        super(Family.all(TextureComponent.class, AnimationComponent.class, StateComponent.class).get());

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        animationMapper = ComponentMapper.getFor(AnimationComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = animationMapper.get(entity);
        StateComponent stateComponent = stateMapper.get(entity);

        if(animationComponent.animations.containsKey(stateComponent.get())) {
            TextureComponent textureComponent = textureMapper.get(entity);
            textureComponent.region = (TextureRegion) animationComponent.animations.get(stateComponent.get())
                    .getKeyFrame(stateComponent.time, stateComponent.isLooping);
        }

        stateComponent.time += deltaTime;
    }
}
