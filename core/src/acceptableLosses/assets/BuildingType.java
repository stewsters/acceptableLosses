package acceptableLosses.assets;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedHashMap;

public class BuildingType {

    public static LinkedHashMap<String, BuildingType> types;

    public String id;
    public String name;
    public TextureRegion texture;

    public boolean blocks = false;
    public boolean airtight = false;
    public boolean climbable = false;
    public boolean bed = false;

    public int movementSpeed = 1;

    // construction time, required materials

}
