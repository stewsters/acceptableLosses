package acceptableLosses.assets.item;


import acceptableLosses.assets.item.weapon.WeaponType;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class OldItem {

    public TextureRegion texture;
    public Comestible comestible;//TODO: move inside item

    public WeaponType weaponType;
    public GarmentType garmentType;

    public OldItem(TextureRegion texture) {
        this.texture = texture;
    }
}
