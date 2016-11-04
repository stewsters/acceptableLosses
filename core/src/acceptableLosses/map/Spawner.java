package acceptableLosses.map;


import acceptableLosses.assets.BuildingType;
import acceptableLosses.assets.body.BodyShape;
import acceptableLosses.assets.body.HairColor;
import acceptableLosses.assets.body.HairStyle;
import acceptableLosses.assets.body.SkinColor;
import acceptableLosses.assets.item.GarmentType;
import acceptableLosses.assets.item.HatType;
import acceptableLosses.components.Citizen;
import acceptableLosses.components.Health;
import acceptableLosses.components.Position;
import acceptableLosses.components.Sentience;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.stewsters.util.math.MatUtils;
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
//        e.edit().create(Citizen.class).set(firstName, lastName, gender);

        Citizen c = e.edit().create(Citizen.class);
        c.firstName = firstName;
        c.lastName = lastName;
        c.gender = gender;
        c.bodyShape = BodyShape.types.get(gender.name());
        c.hairStyle = (HairStyle) MatUtils.randVal(HairStyle.types.values().toArray());
        c.hairColor = (HairColor) MatUtils.randVal(HairColor.types.values().toArray());
        c.skinColor = (SkinColor) MatUtils.randVal(SkinColor.types.values().toArray());

        e.edit().create(Health.class).set(10, 10);

        if (MatUtils.getBoolean(0.8f))
            c.garment = (GarmentType) MatUtils.randVal(GarmentType.types.values().toArray());

        if (MatUtils.getBoolean())
            c.hat = (HatType) MatUtils.randVal(HatType.types.values().toArray());

//        e.edit().create(Appearance.class).set(AssetLoader.atlas.findRegion("character/parts/body/maleBody"));
        e.edit().create(Sentience.class);

        Gdx.app.log("Spawn", firstName + " " + lastName + " has entered the station.");
        return e;
    }


    public static boolean spawnBuilding(Region region, int x, int y, int z, BuildingType buildingType) {

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
