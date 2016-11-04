package acceptableLosses.screens;

import acceptableLosses.AcceptableLossesGame;
import acceptableLosses.assets.BuildingType;
import acceptableLosses.assets.TileType;
import acceptableLosses.controls.InputManager;
import acceptableLosses.controls.commands.TapCommand;
import acceptableLosses.map.AsteroidGenerator;
import acceptableLosses.map.Region;
import acceptableLosses.map.Spawner;
import acceptableLosses.systems.AiSystem;
import acceptableLosses.systems.AppearanceRenderSystem;
import acceptableLosses.systems.CitizenRenderSystem;
import acceptableLosses.systems.CameraMovementSystem;
import acceptableLosses.systems.BuildingRenderSystem;
import acceptableLosses.systems.HudRenderSystem;
import acceptableLosses.systems.JobAssignerSystem;
import acceptableLosses.systems.MapRenderSystem;
import acceptableLosses.systems.MovementSystem;
import acceptableLosses.systems.PathFinderSystem;
import acceptableLosses.systems.TherapistRenderSystem;
import acceptableLosses.work.jobs.DigJob;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.stewsters.util.math.Point3i;


public class GameScreen implements Screen {

    private AcceptableLossesGame game;
    public Region region;

    private OrthographicCamera camera;
    private OrthographicCamera hudCam;
    public int zLevel;

    public SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    public InputManager inputManager;

    public CameraMovementSystem cameraMovementSystem;
    public MapRenderSystem mapRenderSystem;

    public AppearanceRenderSystem appearanceRenderSystem;
    public CitizenRenderSystem citizenRenderSystem;
    public HudRenderSystem hudRenderSystem;

    public AiSystem aiSystem;
    public JobAssignerSystem jobAssignerSystem;
    public PathFinderSystem pathFinderSystem;
    public MovementSystem movementSystem;
    public BuildingRenderSystem buildingRenderSystem;

    // Therapist
    public TherapistRenderSystem therapistRenderSystem;


    public boolean simulationPaused = true;

    public GameScreen(AcceptableLossesGame game) {

        this.game = game;


        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.position.set(250, 250, 0);
        camera.zoom = 0.1f;

        hudCam = new OrthographicCamera();


        font = new BitmapFont();
        font.setColor(1f, 1f, 0.25f, 1);

        region = new Region(100, 100, 100);
        zLevel = region.zSize / 2;


        mapRenderSystem = new MapRenderSystem(this, spriteBatch, region);
        buildingRenderSystem = new BuildingRenderSystem(this, spriteBatch, region);
        hudRenderSystem = new HudRenderSystem(this, spriteBatch);

        cameraMovementSystem = region.world.setSystem(new CameraMovementSystem(this, region,camera),true);
        appearanceRenderSystem = region.world.setSystem(new AppearanceRenderSystem(this, spriteBatch), true);
        citizenRenderSystem = region.world.setSystem(new CitizenRenderSystem(this, spriteBatch), true);
        // These should probably be pausable

        jobAssignerSystem = region.world.setSystem(new JobAssignerSystem(region), true);
        aiSystem = region.world.setSystem(new AiSystem(region, jobAssignerSystem), true);
        pathFinderSystem = region.world.setSystem(new PathFinderSystem(region), true);
        movementSystem = region.world.setSystem(new MovementSystem(region), true);

        therapistRenderSystem = region.world.setSystem(new TherapistRenderSystem(spriteBatch, font), true);
        region.world.initialize();

        gen(region);


        inputManager = new InputManager(camera);

    }


    private void gen(Region region) {


        AsteroidGenerator.generateBasicAsteroid(region);

        // set up world
        Point3i center = new Point3i(region.xSize / 2, region.ySize / 2, region.zSize / 2);

        TileType vacuum = TileType.types.get("VACUUM");
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                region.tiles[center.x + x][center.y + y][center.z] = vacuum;
            }
        }

//        for (int x = -6; x <= 6; x++) {
//            for (int y = -6; y <= 6; y++) {
//                if (region.tiles[center.x + x][center.y + y][center.z].blocks)
//                    region.addJob(new DigJob(region, new Point3i(center.x + x, center.y + y, center.z)));
//            }
//        }

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Spawner.spawnMan(region.world, center.x + x, center.y + y, center.z);
            }
        }
        int zLevel = center.z;
        int i = 0;
        for (BuildingType buildingType : BuildingType.types.values()) {
            Spawner.spawnBuilding(region, 5, i + 2, zLevel, buildingType);
            i++;
        }
    }

    long time = 0;

    @Override
    public void render(float delta) {

        //handle commands
        handleCommands();

        region.world.setDelta(delta);
        region.world.process();

        // Don't run too fast
        if (!simulationPaused) {
            if (time + 100 < System.currentTimeMillis()) {
                aiSystem.process();
                jobAssignerSystem.process();
                pathFinderSystem.process();
                movementSystem.process();
                time = System.currentTimeMillis();
            }
        }

        Gdx.gl.glClearColor(0.05f, 0.05f, 0.05f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // controls
        cameraMovementSystem.process();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            simulationPaused = !simulationPaused;
        }

        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        // Game world (world coordinates)
        mapRenderSystem.process();
        buildingRenderSystem.process();
        appearanceRenderSystem.process();
        citizenRenderSystem.process();

        // HUD section (screen coordinates)
        spriteBatch.setProjectionMatrix(hudCam.combined);
        hudRenderSystem.process();

        spriteBatch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new TherapistScreen(game, this));
        }

    }

    private void handleCommands() {
        TapCommand command = (TapCommand) inputManager.popCommand();
        if (command != null) {

            if (region.isOutsideMap(command.point2i.x, command.point2i.y, zLevel)) {
                Gdx.app.log(this.getClass().getName(), "clicked outside map");

            } else if (region.building[command.point2i.x][command.point2i.y][zLevel] != null) {

                if (region.building[command.point2i.x][command.point2i.y][zLevel].buildingType.id.equals("BLAST_DOOR_OPEN")) {
                    region.building[command.point2i.x][command.point2i.y][zLevel].buildingType = BuildingType.types.get("BLAST_DOOR_CLOSED");

                } else if (region.building[command.point2i.x][command.point2i.y][zLevel].buildingType.id.equals("BLAST_DOOR_CLOSED")) {
                    region.building[command.point2i.x][command.point2i.y][zLevel].buildingType = BuildingType.types.get("BLAST_DOOR_OPEN");
                }

                Gdx.app.log(this.getClass().getName(), "switch at " + command.point2i.toString());

            } else if (region.getJobAt(command.point2i.x, command.point2i.y, zLevel) == null) {
                Point3i jobLocation = new Point3i(command.point2i.x, command.point2i.y, zLevel);
                region.addJob(new DigJob(region, jobLocation));
                Gdx.app.log(this.getClass().getName(), "Dig Job added at " + jobLocation.toString());

            } else {
                Gdx.app.log(this.getClass().getName(), "Job already marked, removing");
                region.removeJob(region.jobs[command.point2i.x][command.point2i.y][zLevel]);
            }
        }
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
        simulationPaused = true;
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        font.dispose();
        shapeRenderer.dispose();
        spriteBatch.dispose();

    }
}
