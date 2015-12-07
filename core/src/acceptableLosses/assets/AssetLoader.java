package acceptableLosses.assets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.LinkedHashMap;

public class AssetLoader {

    public static TextureAtlas atlas;

    public static void init() {
        atlas = new TextureAtlas(Gdx.files.internal("textures/tile.atlas"));

        initTileTypes();

        initBuildingTypes();

    }


    private static void initTileTypes() {
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

            } else if (trimmed.startsWith("texture:")) {
                tileType.texture = atlas.findRegion("wall/" + trimmed.split(":")[1]);
                tileType.floor = atlas.findRegion("floor/" + trimmed.split(":")[1]);

            } else if (trimmed.startsWith("blocks")) {
                tileType.blocks = true;
            }

        }
        TileType.types.put(tileType.id, tileType);
    }

    private static void initBuildingTypes() {
        BuildingType.types = new LinkedHashMap<String, BuildingType>();

        BuildingType buildingType = null;
        for (String line : Gdx.files.internal("data/buildingType.txt").readString().split("\\n")) {
            String trimmed = line.trim();

            if (trimmed.startsWith("#") || trimmed.length() == 0) {
                continue;
            }

            if (trimmed.startsWith("[")) {
                //Then we have an id
                if (buildingType != null) {
                    //New one, save the old
                    BuildingType.types.put(buildingType.id, buildingType);
                }
                buildingType = new BuildingType();
                buildingType.id = trimmed.substring(1, trimmed.length() - 1);

            } else if (buildingType == null) {
                Gdx.app.log("LOADING", "Bad ordering");
                break;

            } else if (trimmed.startsWith("name:")) {
                //Then we have an id
                buildingType.name = trimmed.split(":")[1];

            } else if (trimmed.startsWith("texture:")) {
                buildingType.texture = atlas.findRegion("building/" + trimmed.split(":")[1]);

            } else if (trimmed.startsWith("blocks")) {
                buildingType.blocks = true;
            } else if (trimmed.startsWith("airtight")) {
                buildingType.airtight = true;
            } else if (trimmed.startsWith("climbable")) {
                buildingType.climbable = true;
            }

        }
        BuildingType.types.put(buildingType.id, buildingType);
    }

    public static void dispose() {
        atlas.dispose();
    }
}
