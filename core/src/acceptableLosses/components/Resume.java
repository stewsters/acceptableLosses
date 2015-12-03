package acceptableLosses.components;

import com.artemis.PooledComponent;
import com.stewsters.util.pathing.threeDimention.shared.Mover3d;

public class Resume extends PooledComponent {


    // This will contain parameters on what kind of jobs we want to look for
    public boolean canDig = true;
    public Mover3d mover3d = null;


    @Override
    protected void reset() {
        canDig = true;
        mover3d = null;

    }

    public Resume set(Mover3d mover3d) {
        this.mover3d = mover3d;
        return this;
    }

}
