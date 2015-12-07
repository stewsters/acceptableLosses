package acceptableLosses.systems;

import acceptableLosses.map.Building;
import acceptableLosses.map.Region;
import acceptableLosses.screens.GameScreen;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class FurnitureRenderSystem extends VoidEntitySystem {

    private SpriteBatch spriteBatch;
    private Region region;
    private GameScreen gameScreen;


    public FurnitureRenderSystem(GameScreen gameScreen, SpriteBatch spriteBatch, Region region) {
        this.gameScreen = gameScreen;
        this.spriteBatch = spriteBatch;
        this.region = region;
    }

    @Override
    protected void processSystem() {

        spriteBatch.setColor(Color.WHITE);

        // if we can limit this to what the camera sees, we can speed it up slightly
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {

                Building building = region.building[x][y][gameScreen.zLevel];
                if (building != null && building.buildingType != null && building.buildingType.texture != null) {

                    spriteBatch.draw(building.buildingType.texture, x, y, 1, 1);
                }

            }
        }

    }
}
