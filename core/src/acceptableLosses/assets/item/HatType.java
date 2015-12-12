package acceptableLosses.assets.item;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stewsters.colony.game.graphics.GameTypes;
import org.json.simple.JSONObject;

public class HatType {

    public final String name;
    public final Color color;
    public final TextureRegion texture;
    public final boolean breathable;
    public final int armor;

    public HatType(JSONObject hatType, TextureAtlas textureAtlas) {
        name = (String) hatType.get("name");
        armor = (int) ((Long) hatType.get("armor")).longValue();
        breathable = (boolean) ((Boolean) hatType.get("breathable")).booleanValue();


        color = GameTypes.getColor((JSONObject) hatType.get("color"));
        //TODO: texture loader
        texture = textureAtlas.findRegion((String) hatType.get("texture"));
    }
}
