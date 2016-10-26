package acceptableLosses.screens;

import acceptableLosses.AcceptableLossesGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TherapistScreen implements Screen {


    private AcceptableLossesGame game;
    private OrthographicCamera hudCam;

    GameScreen returnScreen;


    public TherapistScreen(AcceptableLossesGame g, GameScreen returnScreen) {
        this.game = g;
        this.returnScreen = returnScreen;

        hudCam = new OrthographicCamera();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        returnScreen.region.world.setDelta(delta);
        returnScreen.region.world.process();


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        returnScreen.spriteBatch.begin();
        returnScreen.spriteBatch.setProjectionMatrix(hudCam.combined);

        returnScreen.therapistRenderSystem.process();

        returnScreen.spriteBatch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(returnScreen);
        }

    }

    @Override
    public void resize(int width, int height) {
        hudCam.setToOrtho(false, width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
