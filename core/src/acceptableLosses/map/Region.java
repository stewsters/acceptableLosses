package acceptableLosses.map;


import acceptableLosses.systems.AppearanceRenderSystem;
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
//                    tiles[x][y][z] = TileType.VACUUM;
                    tiles[x][y][z] = ((x + y + z) % 2 == 0) ? TileType.CARBON_ORE : TileType.SILICON_ORE;

                }
            }
        }

        world = new World();
        world.setSystem(new AppearanceRenderSystem());
    }
}
