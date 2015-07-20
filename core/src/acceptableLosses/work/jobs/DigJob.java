package acceptableLosses.work.jobs;

import acceptableLosses.assets.TileType;
import acceptableLosses.components.Resume;
import acceptableLosses.map.Region;
import com.stewsters.util.math.Point3i;


public class DigJob implements Job {

    private Region region;
    private Point3i startPos;
    private int assignee;

    public DigJob(Region region, Point3i startPos) {
        this.region = region;
        this.startPos = startPos;
        this.assignee = 0;
    }

    @Override
    public boolean satisfiedBy(Resume resume) {

        if (!resume.canDig) {
            //dont dig
            return false;
        }

        for (Point3i pos : startPos.mooreNeighborhood3D()) {

            if (!region.isBlocked(resume.mover3d, pos.x, pos.y, pos.z)) {
                return true;
            }

        }

        return false;
    }

    @Override
    public Point3i getStartPos() {
        return startPos;
    }

//    @Override
//    public int work() {

//        Tile tile = location.getTile(startPos);
//        tile.tileType = GameTypes.tileTypes.get("void");
//        tile.atmosphere = 10; //TODO: fill with air?
//        //if there are now people standing in the air, drop them
//
//        return 50;
//    }

    @Override
    public int getWorkDistance() {
        return 1;
    }

    @Override
    public void setAssignee(int id) {
        assignee = id;
    }

    @Override
    public int getAssignee() {
        return assignee;
    }

    @Override
    public void accomplishWork() {
        region.tiles[startPos.x][startPos.y][startPos.z] = TileType.VACUUM;
    }


}