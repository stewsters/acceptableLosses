package acceptableLosses.components;


import com.artemis.PooledComponent;
import com.stewsters.util.types.Gender;

public class Citizen extends PooledComponent {

    public String firstName;
    public String lastName;
    public Gender gender;

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
