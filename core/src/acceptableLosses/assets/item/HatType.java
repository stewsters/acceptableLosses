package acceptableLosses.assets.item;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class HatType {

    public static HashMap<String, HatType> types;

    public String id;
    public String name;
    public Color color;
    public TextureRegion texture;
    public boolean breathable;
    public int armor;

}
