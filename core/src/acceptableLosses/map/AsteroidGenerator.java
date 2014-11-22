package acceptableLosses.map;


import acceptableLosses.systems.AppearanceRenderSystem;
import com.artemis.World;

public class AsteroidGenerator {

    public static Region generateBasicAsteroid(Region region) {

        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {
                for (int z = 0; z < region.zSize; z++) {

                    int radius = Math.min(x,Math.min(y,z));
                    int distSquared = (x-(region.xSize/2)) * (x-(region.xSize/2)) +
                            (y-(region.ySize/2)) * (y-(region.ySize/2)) +
                            (z-(region.zSize/2)) * (z-(region.zSize/2));
                    if (radius * radius > distSquared) {
                        region.tiles[x][y][z] = ((x + y + z) % 2 == 0) ? TileType.CARBON_ORE : TileType.SILICON_ORE;
                    } else {
                        region.tiles[x][y][z] = TileType.VACUUM;    //default
                    }

                }
            }
        }

        return region;
    }
}
