package acceptableLosses.assets;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum FurnitureType {

    AI_CORE("ai core", "aiCore"),
    AIRLOCK("airlock", "airlock"),
    BED("bed", "bed"),
    BLAST_DOOR_CLOSED("blast door closed", "blastDoorClosed"),
    BLAST_DOOR_OPEN("blast door opened", "blastDoorOpened"),
    CRYOSTATIS_POD("cryostasis pod", "cryostatisPod"),
    HATCH("hatch", "hatch"),
    KITCHEN("kitchen", "kitchen"),
    LADDER("ladder", "ladder"),
    LIFE_SUPPORT("life support", "lifeSupport"),
    PORTAL("portal", "portal"),
    PROCESSING_CORE("processing core", "processingCore"),
    REACTOR("reactor", "reactor");

    public String name;
    public String textureName;
    public TextureRegion texture;

    // construction time, required materials

    FurnitureType(String name, String textureName) {
        this.name = name;
        this.textureName = textureName;
    }


}
