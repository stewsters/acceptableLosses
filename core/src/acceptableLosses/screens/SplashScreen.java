package acceptableLosses.screens;

import acceptableLosses.AcceptableLossesGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SplashScreen implements Screen {

    private AcceptableLossesGame game;
    private SpriteBatch spriteBatch;
    private BitmapFont font;

    public SplashScreen(AcceptableLossesGame g) {
        game = g;
        spriteBatch = new SpriteBatch();

        font = new BitmapFont();
        font.setColor(0.5f, 0.4f, 0, 1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();

        font.setColor(1f, 1f, 0.25f, 1);
        font.draw(spriteBatch, "Start Game", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

        spriteBatch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {

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
        spriteBatch.dispose();
    }


}
