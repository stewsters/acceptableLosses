package acceptableLosses.systems;


import acceptableLosses.components.Appearance;
import acceptableLosses.components.Position;
import acceptableLosses.screens.GameScreen;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AppearanceRenderSystem extends EntityProcessingSystem {

    @Wire
    ComponentMapper<Appearance> am;

    @Wire
    ComponentMapper<Position> pm;

    private GameScreen gameScreen;
    private SpriteBatch spriteBatch;

    public AppearanceRenderSystem(GameScreen gameScreen, SpriteBatch spriteBatch) {
        super(Aspect.getAspectForAll(Appearance.class, Position.class));
        this.gameScreen = gameScreen;
        this.spriteBatch = spriteBatch;
    }


    @Override
    protected void process(Entity e) {

        Position position = pm.get(e);
        if (position.z != gameScreen.zLevel) {
            return;
        }

        // check to see if we are on screen

        Appearance appearance = am.get(e);
        spriteBatch.setColor(1, 1, 1, 1);
        spriteBatch.draw(appearance.textureRegion,  position.x,  position.y, 1, 1);


    }
}
