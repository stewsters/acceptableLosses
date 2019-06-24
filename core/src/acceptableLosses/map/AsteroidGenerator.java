package acceptableLosses.map;


import acceptableLosses.assets.TileType;
import com.stewsters.util.math.Point3i;
import com.stewsters.util.noise.OpenSimplexNoise;

import java.util.ArrayList;
import java.util.List;

public class AsteroidGenerator {

    public static Region generateBasicAsteroid(Region region) {
        Point3i center = new Point3i(region.xSize / 2, region.ySize / 2, region.zSize / 2);
        int radius = Math.max(region.xSize, Math.max(region.ySize, region.zSize)) / 4;  //diameter is half the total space
        float radiusRange = radius / 25f;
        float edgeRandomness = 10f;
        float oreClumpRandomness = 15f;
        return generateGeneralAsteroid(region, center, radius, radiusRange, edgeRandomness, oreClumpRandomness);
    }

    public static Region generateGeneralAsteroid(Region region, Point3i center, int radius, float radiusRange, float edgeRandomness, float oreClumpRandomness) {

        TileType carbon = TileType.types.get("CARBON_ORE");
        TileType silicon = TileType.types.get("SILICON_ORE");

        OpenSimplexNoise openSimplexNoise = new OpenSimplexNoise();
        OpenSimplexNoise alternateOpenSimplexNoise = new OpenSimplexNoise();

        List<TileType> types =  new ArrayList(TileType.types.values());
        types.remove(TileType.types.get("VACUUM"));
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {
                for (int z = 0; z < region.zSize; z++) {

                    int distSquared = ((x - center.x) * (x - center.x)) +
                            ((y - center.y) * (y - center.y)) +
                            ((z - center.z) * (z - center.z));
                    double randomValue = openSimplexNoise.eval(x / edgeRandomness, y / edgeRandomness, z / edgeRandomness);
                    double randomRadius = radius + (randomValue * radiusRange);

                    if (randomRadius * randomRadius > distSquared) {

                        double tileRandom = (alternateOpenSimplexNoise.eval(x / oreClumpRandomness, y / oreClumpRandomness, z / oreClumpRandomness) + 1.0) / 2.0;
                        int tileId = (int) (tileRandom * types.size());
                        region.tiles[x][y][z] = types.get(tileId);
                    }

                }
            }
        }


        return region;
    }
}
