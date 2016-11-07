package acceptableLosses.components;


import acceptableLosses.assets.body.BodyShape;
import acceptableLosses.assets.body.HairColor;
import acceptableLosses.assets.body.HairStyle;
import acceptableLosses.assets.body.SkinColor;
import acceptableLosses.assets.item.GarmentType;
import acceptableLosses.assets.item.HatType;
import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.Color;
import com.stewsters.util.math.MatUtils;
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
    public Color garmentColor = new Color(
            MatUtils.getFloatInRange(0.1f, 1f),
            MatUtils.getFloatInRange(0.1f, 1f),
            MatUtils.getFloatInRange(0.1f, 1f),
            1
    );

    public HatType hat;
    public Color hatColor = new Color(
            MatUtils.getFloatInRange(0.1f, 1f),
            MatUtils.getFloatInRange(0.1f, 1f),
            MatUtils.getFloatInRange(0.1f, 1f),
            1
    );


    @Override
    protected void reset() {
        firstName = null;
        lastName = null;
        gender = null;
    }

    public Citizen set(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        return this;
    }
}
