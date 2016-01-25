package acceptableLosses.systems;


import acceptableLosses.components.Destination;
import acceptableLosses.components.Path;
import acceptableLosses.components.Position;
import acceptableLosses.map.Region;
import acceptableLosses.pathing.FastNonOptimalHeuristic;
import acceptableLosses.work.CivilianMover;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.stewsters.util.pathing.threeDimention.pathfinder.AStarPathFinder3d;
import com.stewsters.util.pathing.threeDimention.pathfinder.PathFinder3d;
import com.stewsters.util.pathing.threeDimention.shared.FullPath3d;

public class PathFinderSystem extends EntityProcessingSystem {

    @Wire
    ComponentMapper<Position> positionComponentMapper;

    @Wire
    ComponentMapper<Destination> destinationComponentMapper;

    private PathFinder3d pathFinder;

    private Region region;

    public PathFinderSystem(Region region) {
        super(Aspect.getAspectForAll(Destination.class, Position.class));
        pathFinder = new AStarPathFinder3d(region, region.xSize * region.ySize, false, new FastNonOptimalHeuristic());

        this.region = region;
    }

    @Override
    protected void process(Entity e) {

        Destination destination = destinationComponentMapper.get(e);
        Position position = positionComponentMapper.get(e);


        if (destination.destination.x == position.x &&
                destination.destination.y == position.y &&
                destination.destination.z == position.z) {

            Gdx.app.log("PathFinderSystem", "Already there.");

            e.edit().remove(Destination.class);
            return;
        }


        //TODO: this should pull the civilian mover from the
        FullPath3d path = pathFinder.findPath(new CivilianMover(region), position.x, position.y, position.z, destination.destination.x, destination.destination.y, destination.destination.z);


        if (path == null) {
            Gdx.app.log("PathFinderSystem", "Could not path. Abandoned.");
        } else {
            e.edit().create(Path.class).set(path);
            Gdx.app.log("PathFinderSystem", "Path created.");
        }
        e.edit().remove(Destination.class);

    }
}
