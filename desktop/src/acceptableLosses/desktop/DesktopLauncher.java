package acceptableLosses.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import acceptableLosses.AcceptableLossesGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

        ImagePacker.run();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 1024;
        config.height = 768;

		new LwjglApplication(new AcceptableLossesGame(), config);
	}
}
