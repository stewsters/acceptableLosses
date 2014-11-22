package acceptableLosses.map;


import com.badlogic.gdx.Gdx;
import com.stewsters.util.noise.OpenSimplexNoise;

public class AsteroidGenerator {

    public static Region generateBasicAsteroid(Region region) {

        OpenSimplexNoise openSimplexNoise = new OpenSimplexNoise();
        OpenSimplexNoise alternateOpenSimplexNoise = new OpenSimplexNoise();
        int asteroidCount = 0;
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {
                for (int z = 0; z < region.zSize; z++) {

                    double randomValue = openSimplexNoise.eval(x / 10f, y / 10f, z / 10f);

                    //times 4
                    int radius = Math.max(region.xSize, Math.max(region.ySize, region.zSize));  //diameter is half the total space
                    int distSquared = (2 * x - region.xSize) * (2 * x - region.xSize) +
                            (2 * y - region.ySize) * (2 * y - region.ySize) +
                            (2 * z - region.zSize) * (2 * z - region.zSize);

                    double randomRadius = radius + ((randomValue * radius) / 50);

                    if (randomRadius * randomRadius > distSquared) {
                        region.tiles[x][y][z] = (alternateOpenSimplexNoise.eval(x / 20f, y / 20f, z / 20f) >= 0) ? TileType.CARBON_ORE : TileType.SILICON_ORE;
                        asteroidCount++;
                    } else {
                        region.tiles[x][y][z] = TileType.VACUUM;    //default
                    }

                }
            }
        }
        Gdx.app.log("AsteroidGenerator", "Count of asteroid is " + asteroidCount);


        return region;
    }
}
