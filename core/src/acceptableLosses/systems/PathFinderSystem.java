package acceptableLosses.systems;


import acceptableLosses.components.Destination;
import acceptableLosses.components.Path;
import acceptableLosses.components.Position;
import acceptableLosses.map.Region;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.stewsters.util.pathing.threeDimention.pathfinder.AStarPathFinder3d;
import com.stewsters.util.pathing.threeDimention.pathfinder.PathFinder3d;
import com.stewsters.util.pathing.threeDimention.shared.FullPath3d;
import com.stewsters.util.pathing.threeDimention.shared.Mover3d;
import com.stewsters.util.pathing.threeDimention.shared.PathNode3d;

public class PathFinderSystem extends EntityProcessingSystem {

    @Wire
    ComponentMapper<Position> positionComponentMapper;

    @Wire
    ComponentMapper<Destination> destinationComponentMapper;

    private PathFinder3d pathFinder;

    private Region region;

    public PathFinderSystem(Region region) {
        super(Aspect.getAspectForAll(Destination.class, Position.class));
        pathFinder = new AStarPathFinder3d(region, region.xSize * region.ySize, false);

        this.region = region;
    }

    @Override
    protected void process(Entity e) {

        Destination destination = destinationComponentMapper.get(e);
        Position position = positionComponentMapper.get(e);


        if (destination.dest.x == position.x &&
                destination.dest.y == position.y &&
                destination.dest.z == position.z) {

            Gdx.app.log("PathFinderSystem", "Already there.");

            e.edit().remove(Destination.class);
            return;
        }


        FullPath3d path = pathFinder.findPath(new Mover3d() {
            @Override
            public boolean canTraverse(PathNode3d pathNode) {
                return !region.tiles[pathNode.x][pathNode.y][pathNode.z].blocks;
            }
        }, position.x, position.y, position.z, destination.dest.x, destination.dest.y, destination.dest.z);


        if (path != null) {
            e.edit().create(Path.class).set(path);
            Gdx.app.log("PathFinderSystem", "Path created.");
        } else {
            Gdx.app.log("PathFinderSystem", "Could not path. Abandoned.");
        }
        e.edit().remove(Destination.class);


    }
}
