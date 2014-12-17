package acceptableLosses.components;


import com.artemis.PooledComponent;
import com.stewsters.util.math.Point3i;

public class Destination extends PooledComponent {

    public Point3i dest = new Point3i(0, 0, 0);

    @Override
    protected void reset() {
        dest.x = 0;
        dest.y = 0;
        dest.z = 0;
    }

    public Destination set(int x, int y, int z) {
        dest.x = x;
        dest.y = y;
        dest.z = z;

        return this;
    }

    public Destination set(Point3i target) {
        return set(target.x, target.y, target.z);
    }
}
