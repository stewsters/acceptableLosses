package acceptableLosses.work;


import acceptableLosses.map.Region;
import com.stewsters.util.pathing.threeDimention.shared.Mover3d;
import com.stewsters.util.pathing.threeDimention.shared.PathNode3d;

public class CivilianMover implements Mover3d {

    private Region region;

    public CivilianMover(Region region) {
        this.region = region;
    }

    @Override
    public boolean canTraverse(PathNode3d pathNode) {
        //Tile
        if (region.tiles[pathNode.x][pathNode.y][pathNode.z].blocks)
            return false;

        //Walk over terrain
        if (pathNode.z < 1 || !region.tiles[pathNode.x][pathNode.y][pathNode.z - 1].blocks) {
            return false;
        }

        //Furniture
        if (region.furniture[pathNode.x][pathNode.y][pathNode.z] != null &&
                region.furniture[pathNode.x][pathNode.y][pathNode.z].furnitureType.blocks)
            return false;

        return true;
    }
}
