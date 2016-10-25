package acceptableLosses.work.jobs;

import acceptableLosses.assets.ItemType;
import acceptableLosses.assets.TileType;
import acceptableLosses.components.Appearance;
import acceptableLosses.components.Item;
import acceptableLosses.components.Position;
import acceptableLosses.components.Resume;
import acceptableLosses.map.Region;
import com.artemis.Entity;
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

            if (!resume.mover3d.canOccupy(pos.x, pos.y, pos.z)) {
                return true;
            }

        }

        return false;
    }

    @Override
    public Point3i getStartPos() {
        return startPos;
    }

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
        ItemType itemType = region.tiles[startPos.x][startPos.y][startPos.z].produces;
        if (itemType != null) {
            Entity e = region.world.createEntity();
            e.edit().create(Position.class).set(startPos.x, startPos.y, startPos.z);
            e.edit().create(Appearance.class).set(itemType.texture);
            e.edit().create(Item.class).set(itemType);
        }
        region.tiles[startPos.x][startPos.y][startPos.z] = TileType.types.get("VACUUM");
    }


}