package acceptableLosses.map;


import com.artemis.World;

public class Region {

    public final TileType[][][] tiles;

    public final int xSize;
    public final int ySize;
    public final int zSize;

    public World world;

    public Region(int xSize, int ySize, int zSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

        tiles = new TileType[xSize][ySize][zSize];

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < zSize; z++) {
                    tiles[x][y][z] = TileType.VACUUM;
                }
            }
        }

        // we will need to initialize this before we use it.
        world = new World();

    }
}
