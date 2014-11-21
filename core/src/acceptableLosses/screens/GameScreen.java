package acceptableLosses.screens;

import acceptableLosses.AcceptableLossesGame;
import com.badlogic.gdx.Screen;


public class GameScreen implements Screen {
    private AcceptableLossesGame game;

    public GameScreen(AcceptableLossesGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {


    }

    @Override
    public void resize(int width, int height) {
//        camera.setToOrtho(false, width, height);
//        hudCam.setToOrtho(false, width, height);
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

    }
}
