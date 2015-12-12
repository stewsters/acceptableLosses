package acceptableLosses.assets;


import acceptableLosses.assets.body.HairColor;
import acceptableLosses.assets.body.SkinColor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
        hairColor = new HashMap<String, HairColor>();
        FileHandle handle = Gdx.files.internal("data/json/HairColor.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);

            for (Object object : array) {

                JSONObject hairObj = (JSONObject) object;
                HairColor hairColorInstance = new HairColor(hairObj);
                hairColor.put(hairColorInstance.name, hairColorInstance);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initSkinColor() {
        skinColor = new HashMap<String, SkinColor>();
        FileHandle handle = Gdx.files.internal("data/json/Complexion.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);

            for (Object object : array) {

                JSONObject skinObj = (JSONObject) object;
                SkinColor skin = new SkinColor(skinObj);
                skinColor.put(skin.name, skin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void initHats(TextureAtlas atlas) {
        hatTypes = new HashMap<String, HatType>();

        FileHandle handle = Gdx.files.internal("data/json/type/HatTypes.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);
            for (Object object : array) {
                HatType hatType = new HatType((JSONObject) object, atlas);
                hatTypes.put(hatType.name, hatType);
                System.out.println(hatType.name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void initHair(TextureAtlas atlas) {

        hairTextures = new HashMap<String, TextureRegion>();
        hairTextures.put("longHair", atlas.findRegion("character/parts/hair/longHair"));
        hairTextures.put("shortHair", atlas.findRegion("character/parts/hair/shortHair"));
    }

    private static void initBodies(TextureAtlas atlas) {
        bodyTextures = new HashMap<String, TextureRegion>();
        bodyTextures.put("maleBody", atlas.findRegion("character/parts/body/maleBody"));
        bodyTextures.put("femaleBody", atlas.findRegion("character/parts/body/femaleBody"));
    }

    private static void initGarments(TextureAtlas atlas) {
        garmentTypes = new HashMap<String, GarmentType>();
        FileHandle handle = Gdx.files.internal("data/json/type/GarmentTypes.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);
            for (Object object : array) {
                GarmentType garmentType = new GarmentType((JSONObject) object, atlas);
                garmentTypes.put(garmentType.name, garmentType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void initWeapons(TextureAtlas atlas) {

        weaponTypes = new HashMap<String, WeaponType>();
        FileHandle handle = Gdx.files.internal("data/json/type/WeaponTypes.json");
        String text = handle.readString();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);
            for (Object object : array) {
                WeaponType weaponType = new WeaponType((JSONObject) object, atlas);
                weaponTypes.put(weaponType.name, weaponType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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





}
