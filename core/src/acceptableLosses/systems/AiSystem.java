package acceptableLosses.systems;

import acceptableLosses.components.Destination;
import acceptableLosses.components.Path;
import acceptableLosses.components.Position;
import acceptableLosses.components.Sentience;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.MathUtils;

/**
 * This controls the ai of sentient creatures.
 *
 * The primary goal of this class is to assign them things to do,
 * whether it is to walk somewhere, shoot someone, or build something
 */
public class AiSystem extends EntityProcessingSystem {


    @Wire
    ComponentMapper<Path> pathComponentMapper;

    @Wire
    ComponentMapper<Destination> destinationComponentMapper;

    public AiSystem() {
        super(Aspect.getAspectForAll(Sentience.class, Position.class));
    }

    @Override
    protected void process(Entity e) {

        //Yeah, not sure yet what this will involve.  Right now keep them moving

        Path path = pathComponentMapper.getSafe(e);
        Destination destination = destinationComponentMapper.getSafe(e);

        if(path==null && destination==null){
            //TODO: come up with something better
            e.edit().create(Destination.class).set(MathUtils.random(1, 10), MathUtils.random(1, 10), 50);
        }



    }
}
