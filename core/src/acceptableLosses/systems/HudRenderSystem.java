package acceptableLosses.systems;

import acceptableLosses.screens.GameScreen;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HudRenderSystem extends VoidEntitySystem {

    private final SpriteBatch spriteBatch;
    private final GameScreen gameScreen;
    private BitmapFont font;

    public HudRenderSystem(GameScreen gameScreen, SpriteBatch spriteBatch) {
        this.gameScreen = gameScreen;
        this.spriteBatch = spriteBatch;

        font = new BitmapFont();
        font.setColor(0.5f, 0.4f, 0, 1);
        font.setColor(1f, 1f, 0.25f, 1);
    }

    @Override
    protected void processSystem() {
        if(gameScreen.simulationPaused){
            font.draw(spriteBatch, "Paused", Gdx.graphics.getWidth() / 2f,  Gdx.graphics.getHeight() / 8f);
        }

    }
}
