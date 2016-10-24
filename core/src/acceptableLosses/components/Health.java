package acceptableLosses.components;

import com.artemis.PooledComponent;

/**
 * Stores the Health of someone
 */
public class Health extends PooledComponent {
    public int currentHitpoints = 1;
    public int maxHitpoints = 1;

    @Override
    protected void reset() {
        currentHitpoints = 1;
        maxHitpoints = 1;
    }

    public Health set(int current, int max) {
        this.currentHitpoints = current;
        this.maxHitpoints = max;
        return this;
    }
}
