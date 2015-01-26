package acceptableLosses.systems;

import acceptableLosses.map.Region;
import acceptableLosses.screens.GameScreen;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * This system controls the floor we are looking at.
 */
public class ElevationSystem extends VoidEntitySystem {

    private GameScreen gameScreen;
    private Region region;


    public ElevationSystem(GameScreen gameScreen, Region region) {
        this.gameScreen = gameScreen;
        this.region = region;
    }

    @Override
    protected void processSystem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.COMMA)) {
            gameScreen.zLevel = Math.min(gameScreen.zLevel + 1, region.zSize - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.PERIOD)) {
            gameScreen.zLevel = Math.max(gameScreen.zLevel - 1, 0);
        }
    }
}
