package acceptableLosses.components;


import acceptableLosses.assets.body.BodyShape;
import acceptableLosses.assets.body.HairColor;
import acceptableLosses.assets.body.HairStyle;
import acceptableLosses.assets.body.SkinColor;
import acceptableLosses.assets.item.GarmentType;
import acceptableLosses.assets.item.HatType;
import com.artemis.PooledComponent;
import com.stewsters.util.types.Gender;

public class Citizen extends PooledComponent {

    public String firstName;
    public String lastName;
    public Gender gender;

    public BodyShape bodyShape;
    public HairStyle hairStyle;
    public HairColor hairColor;
    public SkinColor skinColor;

    public GarmentType garment;
    public HatType hat;

    @Override
    protected void reset() {
        firstName = null;
        lastName = null;
        gender = null;
    }

}
