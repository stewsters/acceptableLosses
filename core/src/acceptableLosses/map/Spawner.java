package acceptableLosses.map;


import acceptableLosses.assets.AssetLoader;
import acceptableLosses.assets.BuildingType;
import acceptableLosses.components.*;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
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

        String lastName = NameGen.randomLastName();
        e.edit().create(Citizen.class).set(firstName, lastName, gender);
        e.edit().create(Health.class).set(10, 10);
        e.edit().create(Appearance.class).set(AssetLoader.atlas.findRegion("character/parts/body/maleBody"));
        e.edit().create(Sentience.class);

        Gdx.app.log("Spawn", firstName + " " + lastName + " has entered the station.");
        return e;
    }


    public static boolean spawnFurniture(Region region, int x, int y, int z, BuildingType buildingType) {

        if (region.isOutsideMap(x, y, z))
            return false;
        if (region.tiles[x][y][z].blocks)
            return false;
        else if (region.building[x][y][z] != null)
            return false;

        region.building[x][y][z] = new Building(buildingType);
        return true;

    }

}
