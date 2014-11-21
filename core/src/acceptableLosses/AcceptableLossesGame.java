package acceptableLosses;

import acceptableLosses.screens.SplashScreen;
import com.badlogic.gdx.Game;

public class AcceptableLossesGame extends Game {

    @Override
    public void create() {

        // Load assets

        // Open a splash screen
        this.setScreen(new SplashScreen(this));
    }
}
