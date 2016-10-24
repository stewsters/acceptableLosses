package acceptableLosses.assets.item;


import acceptableLosses.assets.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class HatType {

    public static HashMap<String, HatType> types;

    public final String name;
    public final Color color;
    public final TextureRegion texture;
    public final boolean breathable;
    public final int armor;

    public HatType(JSONObject hatType, TextureAtlas textureAtlas) {
        name = (String) hatType.get("name");
        armor = (int) ((Long) hatType.get("armor")).longValue();
        breathable = ((Boolean) hatType.get("breathable")).booleanValue();

        color = AssetLoader.getColor((JSONObject) hatType.get("color"));
        texture = textureAtlas.findRegion((String) hatType.get("texture"));
    }
}
