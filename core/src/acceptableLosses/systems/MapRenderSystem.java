package acceptableLosses.systems;

import acceptableLosses.map.Region;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is not a real system, it prints the visible map
 */
public class MapRenderSystem extends VoidEntitySystem {

    private SpriteBatch spriteBatch;
    private Region region;


    public MapRenderSystem(SpriteBatch spriteBatch, Region region) {
        this.spriteBatch = spriteBatch;
        this.region = region;
    }

    @Override
    protected void processSystem() {

        spriteBatch.setColor(Color.WHITE);

        //TODO: render location

//        location.render(spriteBatch, zLevel);

    }
}
