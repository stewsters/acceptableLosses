package acceptableLosses.systems;


import acceptableLosses.components.Appearance;
import acceptableLosses.components.Position;
import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;

public class AppearanceRenderSystem extends EntityProcessingSystem {


    public AppearanceRenderSystem() {
        super(Aspect.getAspectForAll(Appearance.class, Position.class));
    }

    @Override
    protected void process(Entity e) {

    }
}
