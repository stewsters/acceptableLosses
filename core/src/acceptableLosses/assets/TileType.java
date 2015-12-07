package acceptableLosses.assets;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedHashMap;

public class TileType {

    public static LinkedHashMap<String, TileType> types;

    public String id;
    public String name;
    public boolean blocks = false;

    public TextureRegion texture;
    public TextureRegion floor;

}
