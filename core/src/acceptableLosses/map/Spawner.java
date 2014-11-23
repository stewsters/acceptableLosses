package acceptableLosses.map;


import acceptableLosses.assets.AssetLoader;
import acceptableLosses.components.Appearance;
import acceptableLosses.components.Health;
import acceptableLosses.components.Position;
import com.artemis.Entity;
import com.artemis.World;

public class Spawner {

    public static Entity spawnMan(World world, int x, int y, int z) {

        Entity e = world.createEntity();
        e.edit().create(Position.class).set(x, y, z);
        e.edit().create(Health.class).set(10, 10);
        e.edit().create(Appearance.class).set(AssetLoader.atlas.findRegion("character/parts/body/maleBody"));
        return e;
    }

}
