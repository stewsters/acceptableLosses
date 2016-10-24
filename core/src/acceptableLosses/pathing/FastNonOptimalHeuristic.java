package acceptableLosses.pathing;

import com.stewsters.util.pathing.threeDimention.pathfinder.AStarHeuristic3d;
import com.stewsters.util.pathing.threeDimention.shared.Mover3d;
import com.stewsters.util.pathing.threeDimention.shared.TileBasedMap3d;

public class FastNonOptimalHeuristic implements AStarHeuristic3d {

    @Override
    public float getCost(TileBasedMap3d map, int x, int y, int z, int tx, int ty, int tz) {
        //Find minimum distance along each direction
        int dx = Math.abs(tx - x);
        int dy = Math.abs(ty - y);
        int dz = Math.abs(tz - z);

        //find the greatest distance
        return Math.max(dz, Math.max(dx, dy));
    }
}
