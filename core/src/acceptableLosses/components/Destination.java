package acceptableLosses.components;


import com.artemis.PooledComponent;
import com.stewsters.util.math.Point3i;

public class Destination extends PooledComponent {

    public Point3i destination = new Point3i(0, 0, 0);
    public int distance = 0;

    @Override
    protected void reset() {
        destination.x = 0;
        destination.y = 0;
        destination.z = 0;
        distance = 0;

    }


    public Destination set(int x, int y, int z, int distance) {
        destination.x = x;
        destination.y = y;
        destination.z = z;

        return this;
    }

    public Destination set(int x, int y, int z) {
        return set(x, y, z, 0);
    }

    public Destination set(Point3i target) {
        return set(target.x, target.y, target.z);
    }

    public Destination set(Point3i target, int distance) {
        return set(target.x, target.y, target.z, 0);
    }
}
