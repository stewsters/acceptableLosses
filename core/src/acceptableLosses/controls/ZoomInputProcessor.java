package acceptableLosses.controls;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ZoomInputProcessor implements InputProcessor {

    private final float zoomFactor = 0.1f;
    private OrthographicCamera camera;

    public ZoomInputProcessor(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean scrolled(int dd) {
        if (dd > 0f && camera.zoom < 1f)
            camera.zoom += dd * zoomFactor * camera.zoom;
        else if (dd < 0f && camera.zoom > 0.01f) {
            camera.zoom += dd * zoomFactor * camera.zoom;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }


}
