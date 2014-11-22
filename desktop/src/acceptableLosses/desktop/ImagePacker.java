package acceptableLosses.desktop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class ImagePacker {

    public static void run() {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.filterMin = Texture.TextureFilter.Nearest;
        settings.filterMag = Texture.TextureFilter.Nearest;
        settings.pot = false;
        settings.combineSubdirectories = true;

        TexturePacker.process(settings, "../assetsRaw/texture", "textures", "tile");
    }

}
