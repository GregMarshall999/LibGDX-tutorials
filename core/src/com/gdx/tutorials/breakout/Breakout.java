package com.gdx.tutorials.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gdx.tutorials.breakout.components.Ball;
import com.gdx.tutorials.breakout.components.Block;
import com.gdx.tutorials.breakout.components.GameComponent;
import com.gdx.tutorials.breakout.components.Paddle;

import java.util.ArrayList;
import java.util.List;

public class Breakout extends ApplicationAdapter {
    private ShapeRenderer shape;
    private List<GameComponent> components;

    @Override
    public void create () {
        shape = new ShapeRenderer();
        components = new ArrayList<>();

        components.add(new Ball(
                Gdx.graphics.getWidth() / 2,
                50,
                10,
                5, 5));

        components.add(new Paddle());

        int blockWidth = 63;
        int blockHeight = 20;
        for(int y = Gdx.graphics.getHeight() /2; y < Gdx.graphics.getHeight(); y += blockHeight + 10)
            for(int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10)
                components.add(new Block(x, y, blockWidth, blockHeight));
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < components.size(); i++) {
            components.get(i).update();

            if(components.get(i) instanceof Ball)
                ((Ball)components.get(i)).checkCollision(components);

            if(components.get(i) instanceof Block && ((Block)components.get(i)).isDestroy())
                components.remove(components.get(i));

            components.get(i).draw(shape);
        }
        shape.end();
    }
}
