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

        initItemTypes();
        initTileTypes();
        initBuildingTypes();
        initRecipes();
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

        HatType hatType = null;
        for (String line : Gdx.files.internal("data/dat/hatTypes.txt").readString().split("\\n")) {
            String trimmed = line.trim();

            if (trimmed.startsWith("#") || trimmed.length() == 0) {
                continue;
            }

            if (trimmed.startsWith("[")) {
                //Then we have an id
                if (hatType != null) {
                    //New one, save the old
                    HatType.types.put(hatType.id, hatType);
                }
                hatType = new HatType();
                hatType.id = trimmed.substring(1, trimmed.length() - 1);

            } else if (hatType == null) {
                Gdx.app.log("LOADING", "Bad ordering");
                break;

            } else {
                String tag = trimmed.split(":")[0].trim().toLowerCase();
                String value = trimmed.split(":")[1].trim();

                switch (tag) {
                    case "name":
                        hatType.name = value;
                        break;
                    case "texture":
                        hatType.texture = atlas.findRegion("character/parts/hats/" + value);
                        assert hatType.texture!=null;
                        break;
                    case "color":
                        hatType.color = AssetLoader.getColor(value);
                        break;
                    case "armor":
                        hatType.armor = Integer.parseInt(value);
                        break;
                    case "breathable":
                        hatType.breathable = Boolean.parseBoolean(value);
                        break;
                    default:
                        Gdx.app.log("AssetLoader", "Unknown tag:" + trimmed);
                }
            }
        }
        if (hatType == null) {
            Gdx.app.log("LOADING", "No type!");
        } else {
            HatType.types.put(hatType.id, hatType);
        }

    }

    private static Color getColor(String s) {
        return null;
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
        if (hairStyle == null) {
            Gdx.app.log("LOADING", "No type!");
        } else {
            HairStyle.types.put(hairStyle.id, hairStyle);
        }
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
        if (bodyShape == null) {
            Gdx.app.log("LOADING", "No type!");
        } else {
            BodyShape.types.put(bodyShape.id, bodyShape);
        }

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
            } else if (trimmed.startsWith("produces:")) {
                tileType.produces = ItemType.types.get(trimmed.split(":")[1]);
            } else if (trimmed.startsWith("blocks")) {
                tileType.blocks = true;
            }

        }
        if (tileType == null) {
            Gdx.app.log("LOADING", "No type!");
        } else {
            TileType.types.put(tileType.id, tileType);
        }
    }

    private static void initRecipes() {
        RecipeType.types = new LinkedHashMap<String, RecipeType>();

        RecipeType recipeType = null;
        boolean isInput = false;
        boolean isOutput = false;
        for (String line : Gdx.files.internal("data/dat/recipeType.txt").readString().split("\\n")) {
            String trimmed = line.trim();

            if (trimmed.startsWith("#") || trimmed.length() == 0) {
                continue;
            }

            if (trimmed.startsWith("[")) {
                //Then we have an id
                if (recipeType != null) {
                    //New one, save the old
                    RecipeType.types.put(recipeType.id, recipeType);
                }
                recipeType = new RecipeType();
                recipeType.id = trimmed.substring(1, trimmed.length() - 1);
                recipeType.inputs = new LinkedHashMap<String, Integer>();
                recipeType.outputs = new LinkedHashMap<String, Integer>();
                isInput = false;
                isOutput = false;

            } else if (recipeType == null) {
                Gdx.app.log("LOADING", "Bad ordering");
                break;

            } else if (trimmed.startsWith("name:")) {
                //Then we have an id
                recipeType.name = trimmed.split(":")[1];

            } else if (trimmed.startsWith("transformer:")) {
                recipeType.transformer = trimmed.split(":")[1];

            } else if (trimmed.startsWith("input")) {
                isInput = true;
                isOutput = false;
            } else if (trimmed.startsWith("output")) {
                isInput = false;
                isOutput = true;
            } else if (isInput && trimmed.contains(":")) {
                String quality = trimmed.split(":")[0];
                Integer quantity = Integer.parseInt(trimmed.split(":")[1]);
                recipeType.inputs.put(quality, quantity);
            } else if (isOutput && trimmed.contains(":")) {
                String quality = trimmed.split(":")[0];
                Integer quantity = Integer.parseInt(trimmed.split(":")[1]);
                recipeType.outputs.put(quality, quantity);
            } else {
//                error
            }
        }
        if (recipeType == null) {
            Gdx.app.log("LOADING", "No type!");
        } else {
            RecipeType.types.put(recipeType.id, recipeType);
        }
    }

    private static void initItemTypes() {
        ItemType.types = new LinkedHashMap<String, ItemType>();

        ItemType itemType = null;
        for (String line : Gdx.files.internal("data/dat/itemType.txt").readString().split("\\n")) {
            String trimmed = line.trim();

            if (trimmed.startsWith("#") || trimmed.length() == 0) {
                continue;
            }

            if (trimmed.startsWith("[")) {
                //Then we have an id
                if (itemType != null) {
                    //New one, save the old
                    ItemType.types.put(itemType.id, itemType);
                }
                itemType = new ItemType();
                itemType.id = trimmed.substring(1, trimmed.length() - 1);

            } else if (itemType == null) {
                Gdx.app.log("LOADING", "Bad ordering");
                break;

            } else if (trimmed.startsWith("name:")) {
                //Then we have an id
                itemType.name = trimmed.split(":")[1];

            } else if (trimmed.startsWith("texture:")) {
                itemType.texture = atlas.findRegion("item/" + trimmed.split(":")[1]);
            }
        }
        if (itemType == null) {
            Gdx.app.log("LOADING", "No type!");
        } else {
            ItemType.types.put(itemType.id, itemType);
        }
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

            } else if (trimmed.startsWith("slow:")) {
                buildingType.movementSpeed = Integer.parseInt(trimmed.split(":")[1]);

            } else if (trimmed.startsWith("blocks")) {
                buildingType.blocks = true;
            } else if (trimmed.startsWith("airtight")) {
                buildingType.airtight = true;
            } else if (trimmed.startsWith("bed")) {
                buildingType.bed = true;
            } else if (trimmed.startsWith("climbable")) {
                buildingType.climbable = true;
            }

        }
        if (buildingType == null) {
            Gdx.app.log("LOADING", "No type!");
        } else {
            BuildingType.types.put(buildingType.id, buildingType);
        }
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
