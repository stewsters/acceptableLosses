package acceptableLosses.systems;

import acceptableLosses.components.Position;
import acceptableLosses.components.Sentience;
import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;

/**
 * This controls the ai of sentient creatures
 */
public class AiSystem extends EntityProcessingSystem {


    public AiSystem() {
        super(Aspect.getAspectForAll(Sentience.class, Position.class));
    }

    @Override
    protected void process(Entity e) {

        //Yeah, not sure yet

    }
}
