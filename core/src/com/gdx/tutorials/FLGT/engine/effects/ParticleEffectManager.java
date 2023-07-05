package com.gdx.tutorials.FLGT.engine.effects;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.utils.IntMap;

public class ParticleEffectManager {
    private IntMap<ParticleEffect> partyEffects;
    private IntMap<ParticleEffectPool> partyEffectPool;

    public ParticleEffectManager() {
        partyEffects = new IntMap<>();
        partyEffectPool = new IntMap<>();
    }

    public void addParticleEffect(FLGTEffect type, ParticleEffect party) {
        addParticleEffect(type, party, 1);
    }

    public void addParticleEffect(FLGTEffect type, ParticleEffect party, float scale){
        addParticleEffect(type, party, scale, 5, 20);
    }

    public void addParticleEffect(FLGTEffect type, ParticleEffect party, float scale, int startCapacity, int maxCapacity){
        party.scaleEffect(scale);
        partyEffects.put(type.getId(), party);
        partyEffectPool.put(type.getId(), new ParticleEffectPool(party,startCapacity,maxCapacity));
    }

    public PooledEffect getPooledParticleEffect(FLGTEffect type){
        return partyEffectPool.get(type.getId()).obtain();
    }
}
