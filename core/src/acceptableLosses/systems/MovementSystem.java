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

public class MovementSystem extends EntityProcessingSystem {

    @Wire
    ComponentMapper<Position> positionComponentMapper;

    @Wire
    ComponentMapper<Path> pathComponentMapper;

    private Region region;

    public MovementSystem(Region region) {
        super(Aspect.getAspectForAll(Position.class, Path.class));
        this.region = region;
    }

    @Override
    protected void process(Entity e) {
        Position position = positionComponentMapper.get(e);
        Path path = pathComponentMapper.get(e);


        if (path.step < path.fullPath3d.getLength()) {

            // See if we can travel there
            if (region.tiles[path.fullPath3d.getX(path.step)]
                    [path.fullPath3d.getY(path.step)]
                    [path.fullPath3d.getZ(path.step)].blocks) {

                e.edit().create(Destination.class).set(
                        path.fullPath3d.getX(path.fullPath3d.getLength() - 1),
                        path.fullPath3d.getY(path.fullPath3d.getLength() - 1),
                        path.fullPath3d.getZ(path.fullPath3d.getLength() - 1));

                e.edit().remove(Path.class);

                Gdx.app.debug("MovementSystem", "Cannot follow path, signaling a repath");

            } else {
                //move
                position.set(
                        path.fullPath3d.getX(path.step),
                        path.fullPath3d.getY(path.step),
                        path.fullPath3d.getZ(path.step));
                path.step++;
            }

        } else {
            //we are at destination
            e.edit().remove(Path.class);
            //TODO: do we want to do something here?

        }

    }
}
