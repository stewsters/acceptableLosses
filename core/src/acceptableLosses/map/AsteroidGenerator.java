package acceptableLosses.map;


import acceptableLosses.assets.TileType;
import com.stewsters.util.noise.OpenSimplexNoise;

public class AsteroidGenerator {

    public static Region generateBasicAsteroid(Region region) {
        int xCenter = region.xSize / 2;
        int yCenter = region.ySize / 2;
        int zCenter = region.zSize / 2;
        int radius = Math.max(region.xSize, Math.max(region.ySize, region.zSize)) / 4;  //diameter is half the total space
        float radiusRange = radius / 25f;
        float edgeRandomness = 5f;
        float oreClumpRandomness = 10f;
        return generateGeneralAsteroid(region, xCenter, yCenter, zCenter, radius, radiusRange, edgeRandomness, oreClumpRandomness);
    }

    public static Region generateGeneralAsteroid(Region region, int xCenter, int yCenter, int zCenter, int radius, float radiusRange, float edgeRandomness, float oreClumpRandomness) {

        OpenSimplexNoise openSimplexNoise = new OpenSimplexNoise();
        OpenSimplexNoise alternateOpenSimplexNoise = new OpenSimplexNoise();
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {
                for (int z = 0; z < region.zSize; z++) {

                    int distSquared = ((x - xCenter) * (x - xCenter)) +
                            ((y - yCenter) * (y - yCenter)) +
                            ((z - zCenter) * (z - zCenter));
                    double randomValue = openSimplexNoise.eval(x / edgeRandomness, y / edgeRandomness, z / edgeRandomness);
                    double randomRadius = radius + (randomValue * radiusRange);

                    if (randomRadius * randomRadius > distSquared) {
                        region.tiles[x][y][z] = (alternateOpenSimplexNoise.eval(x / oreClumpRandomness, y / oreClumpRandomness, z / oreClumpRandomness) >= 0)
                                ? TileType.CARBON_ORE
                                : TileType.SILICON_ORE;
                    }

                }
            }
        }

        return region;
    }
}
