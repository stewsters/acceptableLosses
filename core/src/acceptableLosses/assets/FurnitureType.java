package acceptableLosses.assets;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum FurnitureType {

    AI_CORE("ai core", "aiCore", true),
    AIRLOCK("airlock", "airlock", false),
    BED("bed", "bed", false),
    BLAST_DOOR_CLOSED("blast door closed", "blastDoorClosed", true),
    BLAST_DOOR_OPEN("blast door opened", "blastDoorOpened", false),
    CRYOSTATIS_POD("cryostasis pod", "cryostatisPod", true),
    HATCH("hatch", "hatch", false),
    KITCHEN("kitchen", "kitchen", true),
    LADDER("ladder", "ladder", false),
    LIFE_SUPPORT("life support", "lifeSupport", true),
    PORTAL("portal", "portal", false),
    PROCESSING_CORE("processing core", "processingCore", true),
    REACTOR("reactor", "reactor", true);

    public final String name;
    public final String textureName;
    public TextureRegion texture;
    public final boolean blocks;

    // construction time, required materials

    FurnitureType(String name, String textureName, boolean blocks) {
        this.name = name;
        this.textureName = textureName;
        this.blocks = blocks;
    }


}
