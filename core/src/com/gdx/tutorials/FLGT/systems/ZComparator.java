package com.gdx.tutorials.FLGT.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.gdx.tutorials.FLGT.components.TransformComponent;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {
    private ComponentMapper<TransformComponent> componentMapper;

    public ZComparator() {
        componentMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        float az = componentMapper.get(entityA).position.z;
        float bz = componentMapper.get(entityB).position.z;

        int res = 0;

        return Float.compare(az, bz);
    }
}
