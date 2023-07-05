package com.gdx.tutorials.FLGT.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gdx.tutorials.FLGT.engine.components.TextureComponent;
import com.gdx.tutorials.FLGT.engine.components.TransformComponent;

import java.util.Comparator;


public class RenderingSystem extends SortedIteratingSystem {
    public static final float PPM = 32.0f; //amount of pixels per metre of object

    //camera width and height
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth()/PPM;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight()/PPM;

    public static final float PIXELS_TO_METRES = 1.0f/PPM; //pixel to meter ratio

    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();

    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera camera;

    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<TransformComponent> transformMapper;

    public RenderingSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

        renderQueue = new Array<>();

        this.batch = batch;

        camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        camera.position.set(FRUSTUM_WIDTH / 2f, FRUSTUM_HEIGHT / 2f, 0);
    }

    /**
     * Converts the dimension of the screen in meters
     * @return screen dimension in meters
     */
    public static Vector2 getScreenSizeInMeters() {
        meterDimensions.set(Gdx.graphics.getWidth()*PIXELS_TO_METRES, Gdx.graphics.getHeight()*PIXELS_TO_METRES);
        return meterDimensions;
    }


    /**
     * Converts the dimension of the screen in pixels
     * @return screen dimension in pixels
     */
    public static Vector2 getScreenInPixels() {
        pixelDimensions.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return pixelDimensions;
    }

    /**
     * Cpnverts pixels to meters
     * @param pixelValue pixel number to convert
     * @return meter value of pixels
     */
    public static float pixelsToMetres(float pixelValue) {
        return pixelValue * PIXELS_TO_METRES;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();

        for(Entity entity : renderQueue) {
            TextureComponent texture = textureMapper.get(entity);
            TransformComponent transform = transformMapper.get(entity);

            if(texture == null || transform.isHidden)
                continue;

            float width = texture.region.getRegionWidth();
            float height = texture.region.getRegionHeight();

            float originX = width/2f;
            float originY = width/2f;

            batch.draw(texture.region,
                    transform.position.x - originX, transform.position.y - originY,
                    originX, originY,
                    width, height,
                    pixelsToMetres(transform.scale.x), pixelsToMetres(transform.scale.y),
                    transform.rotation);
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
