package acceptableLosses.components;

import com.artemis.PooledComponent;

/**
 * Created by dac on 11/20/2014.
 */
public class Health extends PooledComponent{
    public int current = 1;
    public int max = 1;

    @Override
    protected void reset() {
        current = 1;
        max = 1;
    }

    public Health set(int current, int max) {
        this.current = current;
        this.max = max;
        return this;
    }
}
