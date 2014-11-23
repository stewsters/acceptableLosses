package acceptableLosses.components;

import com.artemis.PooledComponent;

import java.util.List;

/**
 * This entity can hold cargo
 */
public class Cargo extends PooledComponent {

    /*
     * These are the entities we are holding.  Remove their location while they are being carried and add it again when
      * they are set on the ground.
     */
    public List<Integer> containedEntities;

    // Max number we can hold.  Perhaps we should do weight?
    public int capacity;

    @Override
    protected void reset() {
    }
}
