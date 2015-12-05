package acceptableLosses.assets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.LinkedHashMap;

public class AssetLoader {

    public static TextureAtlas atlas;

    public static void init() {
        atlas = new TextureAtlas(Gdx.files.internal("textures/tile.atlas"));

        TileType.types = new LinkedHashMap<String, TileType>();

        TileType tileType = null;
        for (String line : Gdx.files.internal("data/tileType.txt").readString().split("\\n")) {
            String trimmed = line.trim();

            if (trimmed.startsWith("#") || trimmed.length() == 0) {
                continue;
            }

            if (trimmed.startsWith("[")) {
                //Then we have an id
                if (tileType != null) {
                    //New one, save the old
                    TileType.types.put(tileType.id, tileType);
                }
                tileType = new TileType();
                tileType.id = trimmed.substring(1, trimmed.length() - 1);

            } else if (tileType == null) {
                Gdx.app.log("LOADING", "Bad ordering");
                break;

            } else if (trimmed.startsWith("name:")) {
                //Then we have an id
                tileType.name = trimmed.split(":")[1];

            } else if (trimmed.startsWith("blocks:")) {
                //default to true, only false if specified false
                tileType.blocks = !trimmed.split(":")[1].toLowerCase().equals("false");

            } else if (trimmed.startsWith("texture:")) {
                tileType.texture = atlas.findRegion("wall/" + trimmed.split(":")[1]);
                tileType.floor = atlas.findRegion("floor/" + trimmed.split(":")[1]);
            }

        }
        TileType.types.put(tileType.id, tileType);

        for (FurnitureType furnitureType : FurnitureType.values()) {
            furnitureType.texture = atlas.findRegion("building/" + furnitureType.textureName);
        }

    }


    public static void dispose() {
        atlas.dispose();
    }
}
