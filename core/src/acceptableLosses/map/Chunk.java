package acceptableLosses.map;

import acceptableLosses.assets.TileType;
import acceptableLosses.work.jobs.Job;

public class Chunk {

    public static final int chunkSize = 64;

    public final int lowCornerX;
    public final int lowCornerY;
    public final int lowCornerZ;

    public TileType[][][] tiles;

    private final Building[][][] building;
    private final Job[][][] jobs;

    public Chunk(int lowCornerX, int lowCornerY, int lowCornerZ, TileType[][][] data) {
        this.lowCornerX = lowCornerX;
        this.lowCornerY = lowCornerY;
        this.lowCornerZ = lowCornerZ;
        this.tiles = data;
        building = new Building[chunkSize][chunkSize][chunkSize];
        jobs = new Job[chunkSize][chunkSize][chunkSize];


    }


    public boolean isBlocked(int x, int y, int z) {
        return false;
    }
}
