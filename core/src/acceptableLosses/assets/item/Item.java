package acceptableLosses.assets.item;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stewsters.colony.game.entity.stats.Entity;
import com.stewsters.colony.game.entity.stats.item.weapon.WeaponType;

public class Item {
    public Entity owner;
    public TextureRegion texture;
    public Comestible comestible;//TODO: move inside item

    public WeaponType weaponType;
    public GarmentType garmentType;

    public Item(TextureRegion texture) {
        this.texture = texture;
    }
}
