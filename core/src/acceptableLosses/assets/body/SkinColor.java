package acceptableLosses.assets.body;

import com.badlogic.gdx.graphics.Color;
import org.json.simple.JSONObject;

public class SkinColor {
    public String name;
    public Color color;

    public SkinColor(JSONObject hairObj) {
        this.name = (String) hairObj.get("name");

        float r = (float) ((Long) hairObj.get("r")).longValue();
        float g = (float) ((Long) hairObj.get("g")).longValue();
        float b = (float) ((Long) hairObj.get("b")).longValue();


        System.out.println(r + " " + g + " " + b);
        this.color = new Color(
            r / 255f,
            g / 255f,
            b / 255f,
            1);

    }

}
