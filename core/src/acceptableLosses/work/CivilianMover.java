package acceptableLosses.work;


import acceptableLosses.map.Region;
import com.stewsters.util.pathing.threeDimention.shared.Mover3d;

public class CivilianMover implements Mover3d {

    private Region region;

    public CivilianMover(Region region) {
        this.region = region;
    }

    @Override
    public boolean canTraverse(int sx, int sy, int sz, int tx, int ty, int tz) {
        //Tile
        if (region.tiles[tx][ty][tz].blocks)
            return false;

        //Walk over terrain
        if (tz < 1 || !region.tiles[tx][ty][tz - 1].blocks) {
            return false;
        }

        //Furniture
        if (region.building[tx][ty][tz] != null &&
                region.building[tx][ty][tz].buildingType.blocks)
            return false;

        return true;
    }

    @Override
    public boolean canOccupy(int tx, int ty, int tz) {
        if (region.tiles[tx][ty][tz].blocks)
            return false;
        if (region.building[tx][ty][tz] != null &&
                region.building[tx][ty][tz].buildingType.blocks)
            return false;

        return true;
    }

    @Override
    public float getCost(int sx, int sy, int sz, int tx, int ty, int tz) {
        return 1;
    }
}
