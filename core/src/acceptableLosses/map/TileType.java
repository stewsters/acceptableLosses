package acceptableLosses.map;


public enum TileType {

    VACUUM("Vacuum", false),
    SILICON_ORE("Silicon Ore", true),
    CARBON_ORE("Silicon Ore", true);

    public final String name;
    public final boolean blocks;

    TileType(String name, boolean blocksMovement) {
        this.name = name;
        this.blocks = blocksMovement;
    }


}
