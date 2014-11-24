package acceptableLosses.components;

import com.artemis.PooledComponent;
import com.stewsters.util.pathing.threeDimention.shared.FullPath3d;

/**
 * This is an Ai agent's planned path
 */
public class Path extends PooledComponent {

    public FullPath3d fullPath3d;
    public int step;

    @Override
    protected void reset() {
        fullPath3d = null;
        step = 0;
    }

    public Path set(FullPath3d path) {

        fullPath3d = path;
        step = 0;
        return this;
    }
}
