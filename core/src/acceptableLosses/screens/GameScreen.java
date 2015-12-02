package acceptableLosses.screens;

import acceptableLosses.AcceptableLossesGame;
import acceptableLosses.assets.FurnitureType;
import acceptableLosses.controls.InputManager;
import acceptableLosses.map.AsteroidGenerator;
import acceptableLosses.map.Region;
import acceptableLosses.map.Spawner;
import acceptableLosses.systems.*;
import acceptableLosses.work.jobs.DigJob;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.stewsters.util.math.Point3i;


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

    private AiSystem aiSystem;
    private JobAssignerSystem jobAssignerSystem;
    private PathFinderSystem pathFinderSystem;
    private MovementSystem movementSystem;
    private FurnitureRenderSystem furnitureRenderSystem;


    public GameScreen(AcceptableLossesGame game) {

        this.game = game;

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.position.set(250, 250, 0);
        camera.zoom = 0.1f;

        hudCam = new OrthographicCamera();

        region = new Region(100, 100, 100);
        AsteroidGenerator.generateBasicAsteroid(region);

        zLevel = region.zSize / 2;

        elevationSystem = new ElevationSystem(this, region);
        mapRenderSystem = new MapRenderSystem(this, spriteBatch, region);
        furnitureRenderSystem = new FurnitureRenderSystem(this, spriteBatch, region);

        appearanceRenderSystem = region.world.setSystem(new AppearanceRenderSystem(this, spriteBatch), true);

        // These should probably be pausable

        jobAssignerSystem = region.world.setSystem(new JobAssignerSystem(region), true);
        aiSystem = region.world.setSystem(new AiSystem(region, jobAssignerSystem), true);
        pathFinderSystem = region.world.setSystem(new PathFinderSystem(region), true);
        movementSystem = region.world.setSystem(new MovementSystem(region), true);

        region.world.initialize();


        // TODO: remove this.  This mines the whole asteroid
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {
                for (int z = 0; z < region.zSize; z++) {

                    if (region.tiles[x][y][z].blocks)
                        jobAssignerSystem.addJob(new DigJob(region, new Point3i(x, y, z)));

                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Spawner.spawnMan(region.world, i, i, zLevel);
        }


        for (int i = 0; i < FurnitureType.values().length; i++) {

            Spawner.spawnFurniture(region, 5, i + 2, zLevel, FurnitureType.values()[i]);
        }


        inputManager = new InputManager(camera);

    }


    long time = 0;

    @Override
    public void render(float delta) {

        region.world.setDelta(delta);
        region.world.process();


        // Dont run too fast
        if (time + 100 < System.currentTimeMillis()) {
            aiSystem.process();
            jobAssignerSystem.process();
            pathFinderSystem.process();
            movementSystem.process();
            time = System.currentTimeMillis();
        }

        Gdx.gl.glClearColor(0.05f, 0.05f, 0.05f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // controls

        elevationSystem.process();

        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);

        //Draw
        spriteBatch.begin();

        mapRenderSystem.process();
        furnitureRenderSystem.process();
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
