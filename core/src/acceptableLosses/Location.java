package acceptableLosses;


public class Location {

    public final TileType[][][] tiles;

    public final int xSize;
    public final int ySize;
    public final int zSize;

    public Location(int xSize, int ySize, int zSize) {
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


    }
}
