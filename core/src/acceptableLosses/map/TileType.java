package acceptableLosses.map;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum TileType {

    VACUUM("Vacuum",null, false),
    SILICON_ORE("Silicon Ore", "silicon", true),
    CARBON_ORE("Carbon Ore","carbon", true);

    public final String name;
    public final String textureName;
    public final boolean blocks;

    public TextureRegion texture;
    public TextureRegion floor;



    TileType(String name,String textureName, boolean blocksMovement) {
        this.name = name;
        this.textureName = textureName;
        this.blocks = blocksMovement;
    }


}
