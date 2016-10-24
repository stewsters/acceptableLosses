package acceptableLosses.assets.item.weapon;

import acceptableLosses.assets.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class WeaponType {

    public static HashMap<String, WeaponType> types;

    public final WeaponSlot weaponSlot;
    public final String name;
    public final TextureRegion texture;
    public final Color color;

    public final int meleeDamage;
    public final int rangedDamage;
    public final int range;


    //TODO: add attachments? or is that a character thing
//    public void attack(Entity target) {
//
//        if (target.soldier != null) {
//            target.soldier.injure(rangedDamage);
//            FlameAnima.spawnFlame(target.location, target.pos.x, target.pos.y, target.pos.z);
//        }
//    }

    public WeaponType(JSONObject weapon, TextureAtlas textureAtlas) {
        name = (String) weapon.get("name");
        weaponSlot = (weapon.get("type") == "secondary") ? WeaponSlot.SECONDARY : WeaponSlot.PRIMARY;

        meleeDamage = (int) ((Long) weapon.get("meleeDamage")).longValue();
        rangedDamage = (int) ((Long) weapon.get("rangedDamage")).longValue();

        if (weapon.get("range") != null)
            range = (int) ((Long) weapon.get("range")).longValue();
        else
            range = 1;

        color = AssetLoader.getColor((JSONObject) weapon.get("color"));
        texture = textureAtlas.findRegion((String) weapon.get("texture"));

    }


}
