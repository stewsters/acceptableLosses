package acceptableLosses.components;

import com.artemis.PooledComponent;

public class Resume extends PooledComponent {


    // This will contain parameters on what kind of jobs we want to look for
    public boolean canDig;


    @Override
    protected void reset() {
        canDig = true;
    }
}
