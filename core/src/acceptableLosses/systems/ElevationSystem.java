package acceptableLosses.systems;

import acceptableLosses.screens.GameScreen;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * This system controls the floor we are looking at.
 */
public class ElevationSystem extends VoidEntitySystem {

    private GameScreen gameScreen;


    public ElevationSystem(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    protected void processSystem() {
        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
            gameScreen.zLevel++;
        } else if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            gameScreen.zLevel--;
        }
    }
}
