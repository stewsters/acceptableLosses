package acceptableLosses.controls;

import acceptableLosses.map.MapTools;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class GestureDetectorController implements GestureDetector.GestureListener {

    private OrthographicCamera camera;

    private InputManager inputManager;


    public GestureDetectorController(OrthographicCamera camera, InputManager inputManager) {
        this.camera = camera;
        this.inputManager = inputManager;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        inputManager.tapCommand(MapTools.window2world(Gdx.input.getX(), Gdx.input.getY(), camera));

        return false;
    }


    @Override
    public boolean longPress(float x, float y) {
        //TODO: Should pop open spell and special ability menu
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        //TODO: movement?  of camera or player?
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        camera.translate(-deltaX * camera.zoom, deltaY * camera.zoom);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        //TODO: this zooms to fast when zoomed in, should change by a percentage
        float dd = (initialDistance - distance) * 0.0001f;
        if (dd > 0f && camera.zoom < 1f)
            camera.zoom += dd * camera.zoom;
        else if (dd < 0f && camera.zoom > 0.1f) {
            camera.zoom += dd * camera.zoom;
        }
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }


}
