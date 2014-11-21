package acceptableLosses.map;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum TileType {

    VACUUM("Vacuum", false),
    SILICON_ORE("Silicon Ore", true),
    CARBON_ORE("Carbon Ore", true);

    public final String name;
    public final boolean blocks;

    public TextureRegion texture;
    public TextureRegion floor;

    TileType(String name, boolean blocksMovement) {
        this.name = name;
        this.blocks = blocksMovement;
    }


}
