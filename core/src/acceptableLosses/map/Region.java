package acceptableLosses.map;


import acceptableLosses.assets.TileType;
import acceptableLosses.work.jobs.Job;
import com.artemis.World;
import com.stewsters.util.math.Point3i;
import com.stewsters.util.pathing.threeDimention.shared.TileBasedMap3d;

public class Region implements TileBasedMap3d {

    public final TileType[][][] tiles;
    public final Building[][][] building;
    public final Job[][][] jobs;

    public final int xSize;
    public final int ySize;
    public final int zSize;

    public World world;

    public Region(int xSize, int ySize, int zSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

        tiles = new TileType[xSize][ySize][zSize];
        building = new Building[xSize][ySize][zSize];
        jobs = new Job[xSize][ySize][zSize];

        TileType vacuum = TileType.types.get("VACUUM");
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < zSize; z++) {
                    tiles[x][y][z] = vacuum;

                }
            }
        }

        // we will need to initialize this before we use it.
        world = new World();

    }

    @Override
    public int getXSize() {
        return xSize;
    }

    @Override
    public int getYSize() {
        return ySize;
    }

    @Override
    public int getZSize() {
        return zSize;
    }

    @Override
    public void pathFinderVisited(int x, int y, int z) {
        // This is for testing.  Its called if the pathfinder visits this tile.
    }

    public boolean isOutsideMap(int x, int y, int z) {
        return (x < 0 || x >= xSize || y < 0 || y >= ySize || z < 0 || z >= zSize);
    }

    public boolean isOutsideMap(Point3i p) {
        return isOutsideMap(p.x, p.y, p.z);
    }

    public Job getJobAt(int x, int y, int z) {
        if (isOutsideMap(x, y, z))
            return null;

        return jobs[x][y][z];
    }

    public void removeJob(Job job) {

        Point3i p = job.getStartPos();
        if (!isOutsideMap(p))
            jobs[p.x][p.y][p.z] = null;
    }

    public void addJob(Job job) {
        Point3i p = job.getStartPos();
        if (!isOutsideMap(p))
            jobs[p.x][p.y][p.z] = job;
    }
}
