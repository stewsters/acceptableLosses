package acceptableLosses.assets;


import acceptableLosses.assets.body.BodyShape;
import acceptableLosses.assets.body.HairColor;
import acceptableLosses.assets.body.HairStyle;
import acceptableLosses.assets.body.SkinColor;
import acceptableLosses.assets.item.GarmentType;
import acceptableLosses.assets.item.HatType;
import acceptableLosses.assets.item.weapon.WeaponType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class AssetLoader {

    public static TextureAtlas atlas;

    public static void init() {
        atlas = new TextureAtlas(Gdx.files.internal("textures/tile.atlas"));

        initHairColor();
        initSkinColor();

        initHats(atlas);
        initHair(atlas);
        initBodies(atlas);
        initGarments(atlas);

        initWeapons(atlas);

        initTileTypes();
        initBuildingTypes();
    }

    public static void dispose() {
        atlas.dispose();
    }


    private static void initHairColor() {
        HairColor.types = new HashMap<String, HairColor>();
        FileHandle handle = Gdx.files.internal("data/json/HairColor.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);

            for (Object object : array) {

                JSONObject hairObj = (JSONObject) object;
                HairColor hairColorInstance = new HairColor(hairObj);
                HairColor.types.put(hairColorInstance.name, hairColorInstance);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initSkinColor() {
        SkinColor.types = new HashMap<String, SkinColor>();
        FileHandle handle = Gdx.files.internal("data/json/Complexion.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);

            for (Object object : array) {

                JSONObject skinObj = (JSONObject) object;
                SkinColor skin = new SkinColor(skinObj);
                SkinColor.types.put(skin.name, skin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void initHats(TextureAtlas atlas) {
        HatType.types = new HashMap<String, HatType>();

        FileHandle handle = Gdx.files.internal("data/json/type/HatTypes.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);
            for (Object object : array) {
                HatType hatType = new HatType((JSONObject) object, atlas);
                HatType.types.put(hatType.name, hatType);
                System.out.println(hatType.name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void initHair(TextureAtlas atlas) {
        HairStyle.types = new HashMap<String, HairStyle>();

        HairStyle hairStyle = null;
        for (String line : Gdx.files.internal("data/dat/hairStyle.txt").readString().split("\\n")) {
            String trimmed = line.trim();

            if (trimmed.startsWith("#") || trimmed.length() == 0) {
                continue;
            }

            if (trimmed.startsWith("[")) {
                //Then we have an id
                if (hairStyle != null) {
                    //New one, save the old
                    HairStyle.types.put(hairStyle.id, hairStyle);
                }
                hairStyle = new HairStyle();
                hairStyle.id = trimmed.substring(1, trimmed.length() - 1);

            } else if (hairStyle == null) {
                Gdx.app.log("LOADING", "Bad ordering");
                break;

            } else if (trimmed.startsWith("name:")) {
                //Then we have an id
                hairStyle.name = trimmed.split(":")[1];

            } else if (trimmed.startsWith("texture:")) {
                hairStyle.texture = atlas.findRegion("character/parts/hair/" + trimmed.split(":")[1]);

            }
        }
        HairStyle.types.put(hairStyle.id, hairStyle);
    }

    private static void initBodies(TextureAtlas atlas) {
        BodyShape.types = new HashMap<String, BodyShape>();

        BodyShape bodyShape = null;
        for (String line : Gdx.files.internal("data/dat/bodyType.txt").readString().split("\\n")) {
            String trimmed = line.trim();

            if (trimmed.startsWith("#") || trimmed.length() == 0) {
                continue;
            }

            if (trimmed.startsWith("[")) {
                //Then we have an id
                if (bodyShape != null) {
                    //New one, save the old
                    BodyShape.types.put(bodyShape.id, bodyShape);
                }
                bodyShape = new BodyShape();
                bodyShape.id = trimmed.substring(1, trimmed.length() - 1);

            } else if (bodyShape == null) {
                Gdx.app.log("LOADING", "Bad ordering");
                break;

            } else if (trimmed.startsWith("name:")) {
                //Then we have an id
                bodyShape.name = trimmed.split(":")[1];

            } else if (trimmed.startsWith("texture:")) {
                bodyShape.texture = atlas.findRegion("character/parts/body/" + trimmed.split(":")[1]);

            }
        }
        BodyShape.types.put(bodyShape.id, bodyShape);

    }

    private static void initGarments(TextureAtlas atlas) {
        GarmentType.types = new HashMap<String, GarmentType>();
        FileHandle handle = Gdx.files.internal("data/json/type/GarmentTypes.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);
            for (Object object : array) {
                GarmentType garmentType = new GarmentType((JSONObject) object, atlas);
                GarmentType.types.put(garmentType.name, garmentType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void initWeapons(TextureAtlas atlas) {

        WeaponType.types = new HashMap<String, WeaponType>();
        FileHandle handle = Gdx.files.internal("data/json/type/WeaponTypes.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);
            for (Object object : array) {
                WeaponType weaponType = new WeaponType((JSONObject) object, atlas);
                WeaponType.types.put(weaponType.name, weaponType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initTileTypes() {
        TileType.types = new LinkedHashMap<String, TileType>();

        TileType tileType = null;
        for (String line : Gdx.files.internal("data/dat/tileType.txt").readString().split("\\n")) {
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
        for (String line : Gdx.files.internal("data/dat/buildingType.txt").readString().split("\\n")) {
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


    public static Color getColor(JSONObject weaponColor) {
        if (weaponColor == null)
            return Color.WHITE;

        float r = (float) ((Long) weaponColor.get("r")).longValue();
        float g = (float) ((Long) weaponColor.get("g")).longValue();
        float b = (float) ((Long) weaponColor.get("b")).longValue();

        System.out.println(r + " " + g + " " + b);

        return new Color(
                r / 255f,
                g / 255f,
                b / 255f,
                1);
    }


}
