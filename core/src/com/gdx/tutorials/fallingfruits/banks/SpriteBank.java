package com.gdx.tutorials.fallingfruits.banks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public abstract class SpriteBank  {
    private static final Map<String, Sprite> bank = new HashMap<>();

    public static void initBank(TextureAtlas textureAtlas) {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for(TextureAtlas.AtlasRegion region : regions)
            bank.put(region.name, textureAtlas.createSprite(region.name));
    }

    public static void drawSprite(SpriteBatch batch, String name, float x, float y) {
        bank.get(name).setPosition(x, y);
        bank.get(name).draw(batch);
    }
}
