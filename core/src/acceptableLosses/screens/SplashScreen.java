package acceptableLosses.screens;

import acceptableLosses.AcceptableLossesGame;
import acceptableLosses.assets.BuildingType;
import acceptableLosses.assets.TileType;
import acceptableLosses.map.AsteroidGenerator;
import acceptableLosses.map.Region;
import acceptableLosses.map.Spawner;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stewsters.util.math.Point3i;

public class SplashScreen implements Screen {

    private AcceptableLossesGame game;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private OrthographicCamera hudCam;


    public SplashScreen(AcceptableLossesGame g) {
        game = g;
        spriteBatch = new SpriteBatch();
        hudCam = new OrthographicCamera();

        font = new BitmapFont();
        font.setColor(1f, 1f, 0.25f, 1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(hudCam.combined);
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
        hudCam.setToOrtho(false, width, height);
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
        font.dispose();
        spriteBatch.dispose();
    }


}
