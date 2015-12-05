package acceptableLosses.map;


import acceptableLosses.assets.TileType;
import acceptableLosses.work.jobs.Job;
import com.artemis.World;
import com.stewsters.util.math.Point3i;
import com.stewsters.util.pathing.threeDimention.shared.Mover3d;
import com.stewsters.util.pathing.threeDimention.shared.PathNode3d;
import com.stewsters.util.pathing.threeDimention.shared.TileBasedMap3d;

public class Region implements TileBasedMap3d {

    public final TileType[][][] tiles;
    public final Furniture[][][] furniture;
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
        furniture = new Furniture[xSize][ySize][zSize];
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
    public int getWidthInTiles() {
        return xSize;
    }

    @Override
    public int getHeightInTiles() {
        return ySize;
    }

    @Override
    public int getDepthInTiles() {
        return zSize;
    }

    @Override
    public void pathFinderVisited(int x, int y, int z) {
        // This is for testing.  Its called if the pathfinder visits this tile.
    }

    @Override
    public boolean isBlocked(Mover3d mover, PathNode3d pathNode) {

        return isBlocked(mover, pathNode.x, pathNode.y, pathNode.z);

    }

    @Override
    public boolean isBlocked(Mover3d mover, int x, int y, int z) {
        return tiles[x][y][z].blocks || (furniture[x][y][z] != null && furniture[x][y][z].furnitureType.blocks);
    }

    @Override
    public float getCost(Mover3d mover, int sx, int sy, int sz, int tx, int ty, int tz) {
        // This is the cost of the move for the heuristic
        return 1f;
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
