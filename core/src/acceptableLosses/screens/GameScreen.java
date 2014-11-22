package acceptableLosses.screens;

import acceptableLosses.AcceptableLossesGame;
import acceptableLosses.map.Region;
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


//    public InputManager inputManager;

    private ElevationSystem elevationSystem;
    private MapRenderSystem mapRenderSystem;

    public GameScreen(AcceptableLossesGame game) {

        this.game = game;

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.zoom = 0.5f;

        hudCam = new OrthographicCamera();

        zLevel = 50;

        region = new Region(500, 500, 500);

        elevationSystem = new ElevationSystem(this, region);
        mapRenderSystem = new MapRenderSystem(this, spriteBatch, region);


//        inputManager = new InputManager(camera);

//        LocationBuilder builder = new LocationBuilder();
//        location = builder.generateLocation(universe);

    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.05f, 0.05f, 0.05f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // controls

        elevationSystem.process();

        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);

        //Draw
        spriteBatch.begin();

        mapRenderSystem.process();

        // render characters

        // menu.render(spriteBatch);


        spriteBatch.end();
    }

    private float timeSinceMove = 0f;
    private static final float moveTime = 0.1f;

//    private void control(float deltaT) {
//        if (timeSinceMove < moveTime) {
//            timeSinceMove += deltaT;
//            return;
//        }
//
//        timeSinceMove = 0;
//
//        Vector3 cam = game.controller.getCameraMovement();
//        focus.x += cam.x;
//        focus.y += cam.y;
//        camera.zoom += cam.z;
//
//        focus.z += game.controller.checkLevelChange();
//
//        game.controller.clear();
//
////        menu = menu.control(keysPressed);
//    }

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
