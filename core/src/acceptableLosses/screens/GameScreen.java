package acceptableLosses.screens;

import acceptableLosses.AcceptableLossesGame;
import acceptableLosses.controls.InputManager;
import acceptableLosses.map.AsteroidGenerator;
import acceptableLosses.map.Region;
import acceptableLosses.map.Spawner;
import acceptableLosses.systems.AppearanceRenderSystem;
import acceptableLosses.systems.ElevationSystem;
import acceptableLosses.systems.MapRenderSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class GameScreen implements Screen {

    private AcceptableLossesGame game;
    private Region region;

    private OrthographicCamera camera;
    private OrthographicCamera hudCam;
    public int zLevel;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    public InputManager inputManager;
    private ElevationSystem elevationSystem;
    private MapRenderSystem mapRenderSystem;

    private AppearanceRenderSystem appearanceRenderSystem;

    public GameScreen(AcceptableLossesGame game) {

        this.game = game;

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.position.set(250 * 16, 250 * 16, 0);
        camera.zoom = 0.5f;

        hudCam = new OrthographicCamera();

        region = new Region(100, 100, 100);
        AsteroidGenerator.generateBasicAsteroid(region);

        zLevel = region.zSize / 2;

        elevationSystem = new ElevationSystem(this, region);
        mapRenderSystem = new MapRenderSystem(this, spriteBatch, region);
        appearanceRenderSystem = region.world.setSystem(new AppearanceRenderSystem(this, spriteBatch), true);
        region.world.initialize();


        Spawner.spawnMan(region.world, 5, 5, zLevel);

        inputManager = new InputManager(camera);

    }


    @Override
    public void render(float delta) {

        region.world.setDelta(delta);
        region.world.process();

        Gdx.gl.glClearColor(0.05f, 0.05f, 0.05f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // controls

        elevationSystem.process();

        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);

        //Draw
        spriteBatch.begin();

        mapRenderSystem.process();
        appearanceRenderSystem.process();


        // menu.render(spriteBatch);


        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        hudCam.setToOrtho(false, width, height);
    }

    @Override
    public void show() {


    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }
}
