package acceptableLosses.systems;

import acceptableLosses.Location;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is not a real system, it prints the visible map
 */
public class MapRenderSystem extends VoidEntitySystem {

    private SpriteBatch spriteBatch;
    private Location location;


    public MapRenderSystem(SpriteBatch spriteBatch, Location location) {
        this.spriteBatch = spriteBatch;
        this.location = location;
    }

    @Override
    protected void processSystem() {

        spriteBatch.setColor(Color.WHITE);

        //TODO: render location

//        location.render(spriteBatch, zLevel);

    }
}
