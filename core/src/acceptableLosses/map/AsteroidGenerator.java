package acceptableLosses.map;


import acceptableLosses.systems.AppearanceRenderSystem;
import com.artemis.World;
import com.badlogic.gdx.Gdx;

public class AsteroidGenerator {

    public static Region generateBasicAsteroid(Region region) {

        int asteroidCount = 0;
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {
                for (int z = 0; z < region.zSize; z++) {

//                    int radius = Math.max(region.xSize, Math.max(region.ySize, region.zSize)) / 4;
//                    int distSquared = (2 * x - region.xSize) * (2 * x - region.xSize) / 4 +
//                            (2 * y - region.ySize) * (2 * y - region.ySize) / 4 +
//                            (2 * z - region.zSize) * (2 * z - region.zSize) / 4;
//                    if (radius * radius > distSquared) {
//                        region.tiles[x][y][z] = ((x + y + z) % 2 == 0) ? TileType.CARBON_ORE : TileType.SILICON_ORE;
//                        asteroidCount++;
//                    } else {
//                        region.tiles[x][y][z] = TileType.VACUUM;    //default
//                    }

                    if (z % 3 == 0) {
                        region.tiles[x][y][z] = TileType.VACUUM;    //default
                    } else {
                        region.tiles[x][y][z] = (x > y) ? TileType.CARBON_ORE : TileType.SILICON_ORE;
                    }

                }
            }
        }
        Gdx.app.log("AsteroidGenerator","Count of asteroid is " + asteroidCount);

        return region;
    }
}
