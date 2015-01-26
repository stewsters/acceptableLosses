package acceptableLosses.map;


import acceptableLosses.assets.AssetLoader;
import acceptableLosses.assets.FurnitureType;
import acceptableLosses.components.*;
import com.artemis.Entity;
import com.artemis.World;
import com.stewsters.util.name.NameGen;
import com.stewsters.util.types.Gender;

public class Spawner {

    public static Entity spawnMan(World world, int x, int y, int z) {

        Entity e = world.createEntity();
        e.edit().create(Position.class).set(x, y, z);

        Gender gender = Gender.random();
        String firstName;

        if (gender == Gender.MALE)
            firstName = NameGen.randomMaleFirstName();
        else
            firstName = NameGen.randomFemaleFirstName();

        e.edit().create(Citizen.class).set(firstName, NameGen.randomLastName(), gender);
        e.edit().create(Health.class).set(10, 10);
        e.edit().create(Appearance.class).set(AssetLoader.atlas.findRegion("character/parts/body/maleBody"));
        e.edit().create(Sentience.class);
        return e;
    }


    public static boolean spawnFurniture(Region region, int x, int y, int z, FurnitureType furnitureType) {

        if (region.isOutsideMap(x, y, z))
            return false;
        if (region.tiles[x][y][z].blocks)
            return false;
        else if (region.furniture[x][y][z] != null)
            return false;

        region.furniture[x][y][z] = new Furniture(furnitureType);
        return true;

    }

}
