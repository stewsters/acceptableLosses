package acceptableLosses.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Appearance extends PooledComponent {

    public TextureRegion textureRegion;

    @Override
    protected void reset() {
        textureRegion = null;
    }


    public Appearance set(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        return this;
    }
}
