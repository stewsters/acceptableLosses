package acceptableLosses.components;


import com.artemis.PooledComponent;

public class Position extends PooledComponent {

    public int x, y, z = 0;


    @Override
    protected void reset() {
        x = y = z = 0;
    }

    public Position set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
