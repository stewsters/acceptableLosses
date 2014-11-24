package acceptableLosses.components;

import com.artemis.PooledComponent;
import com.stewsters.util.pathing.threeDimention.shared.FullPath3d;

/**
 * This is an Ai agent's planned path
 */
public class Path extends PooledComponent {

    FullPath3d fullPath3d;

    @Override
    protected void reset() {
        fullPath3d = null;
    }

    public Path set(FullPath3d path) {

        fullPath3d = path;
        return this;
    }
}
