package acceptableLosses.assets.item;



import acceptableLosses.assets.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class GarmentType {

    public static HashMap<String, GarmentType> types;
    public final String name;
    public final TextureRegion texture;
    public final Color color;
    public final boolean canFly;

    public int speed;
    public int armor;

    public GarmentType(JSONObject garmentType, TextureAtlas textureAtlas) {
        name = (String) garmentType.get("name");
        speed = (int) ((Long) garmentType.get("speed")).longValue();
        armor = (int) ((Long) garmentType.get("armor")).longValue();

        canFly = garmentType.get("fly") != null && (Boolean) garmentType.get("fly");
        color = AssetLoader.getColor((JSONObject) garmentType.get("color"));
        texture = textureAtlas.findRegion((String) garmentType.get("texture"));

    }
}
