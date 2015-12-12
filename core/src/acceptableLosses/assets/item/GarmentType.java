package acceptableLosses.assets.item;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stewsters.colony.game.graphics.GameTypes;
import org.json.simple.JSONObject;

public class GarmentType {

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

        if (garmentType.get("fly") != null && (Boolean) garmentType.get("fly")) {
            canFly = true;
        } else {
            canFly = false;
        }
        color = GameTypes.getColor((JSONObject) garmentType.get("color"));
        texture = textureAtlas.findRegion((String) garmentType.get("texture"));

    }
}
