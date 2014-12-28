package acceptableLosses.map;


import acceptableLosses.assets.TileType;
import acceptableLosses.work.jobs.DigJob;
import com.stewsters.util.math.Point3i;
import com.stewsters.util.noise.OpenSimplexNoise;

public class AsteroidGenerator {

    public static Region generateBasicAsteroid(Region region) {
        Point3i center = new Point3i(region.xSize / 2, region.ySize / 2, region.zSize / 2);
        int radius = Math.max(region.xSize, Math.max(region.ySize, region.zSize)) / 4;  //diameter is half the total space
        float radiusRange = radius / 25f;
        float edgeRandomness = 5f;
        float oreClumpRandomness = 10f;
        return generateGeneralAsteroid(region, center, radius, radiusRange, edgeRandomness, oreClumpRandomness);
    }

    public static Region generateGeneralAsteroid(Region region, Point3i center, int radius, float radiusRange, float edgeRandomness, float oreClumpRandomness) {

        OpenSimplexNoise openSimplexNoise = new OpenSimplexNoise();
        OpenSimplexNoise alternateOpenSimplexNoise = new OpenSimplexNoise();
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {
                for (int z = 0; z < region.zSize; z++) {

                    int distSquared = ((x - center.x) * (x - center.x)) +
                            ((y - center.y) * (y - center.y)) +
                            ((z - center.z) * (z - center.z));
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


        // TODO: remove this.  This mines the whole asteroid
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {
                for (int z = 0; z < region.zSize; z++) {

                    if (region.tiles[x][y][z].blocks)
                        region.addJob(new DigJob(region, new Point3i(x, y, z)));

                }
            }
        }


        return region;
    }
}
