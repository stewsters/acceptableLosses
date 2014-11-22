package acceptableLosses;

import acceptableLosses.assets.AssetLoader;
import acceptableLosses.screens.SplashScreen;
import com.badlogic.gdx.Game;

public class AcceptableLossesGame extends Game {

    @Override
    public void create() {

        // Load assets.  Tiles, configs, whatever
        AssetLoader.init();

        // Open a splash screen
        this.setScreen(new SplashScreen(this));
    }
}
