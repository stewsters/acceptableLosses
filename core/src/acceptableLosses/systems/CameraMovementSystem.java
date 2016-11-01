package acceptableLosses.systems;

import acceptableLosses.map.Region;
import acceptableLosses.screens.GameScreen;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.stewsters.util.math.MatUtils;

/**
 * This system controls the floor we are looking at.
 */
public class CameraMovementSystem extends VoidEntitySystem {

    private GameScreen gameScreen;
    private Region region;
    private OrthographicCamera camera;

    Vector2 camPos;
    Vector2 camVel;

    public CameraMovementSystem(GameScreen gameScreen, Region region, OrthographicCamera camera) {
        this.gameScreen = gameScreen;
        this.region = region;
        this.camera = camera;

        this.camPos = new Vector2(0, 0);
        this.camVel = new Vector2(0, 0);
    }

    @Override
    protected void processSystem() {


        if (Gdx.input.isKeyJustPressed(Input.Keys.COMMA)) {
            gameScreen.zLevel = Math.min(gameScreen.zLevel + 1, region.zSize - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.PERIOD)) {
            gameScreen.zLevel = Math.max(gameScreen.zLevel - 1, 0);
        }


        float delta = world.getDelta();

        float xAccel = -camVel.x * 1000 * delta;
        float yAccel = -camVel.y * 1000 * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xAccel = -500f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xAccel = 500f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yAccel = 500f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yAccel = -500f;
        }

        camVel.x = MatUtils.limit(camVel.x + xAccel * delta, -1000, 1000);
        camVel.y = MatUtils.limit(camVel.y + yAccel * delta, -1000, 1000);

//        camPos.x += camVel.x * delta;
//        camPos.y += camVel.y * delta;

        camera.translate(camVel.x * delta, camVel.y * delta);
    }
}
