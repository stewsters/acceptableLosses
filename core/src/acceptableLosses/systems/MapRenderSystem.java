package acceptableLosses.systems;

import acceptableLosses.map.Region;
import acceptableLosses.map.TileType;
import acceptableLosses.screens.GameScreen;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is not a real system, it prints the visible map
 */
public class MapRenderSystem extends VoidEntitySystem {

    private SpriteBatch spriteBatch;
    private Region region;
    private GameScreen gameScreen;


    public MapRenderSystem(GameScreen gameScreen, SpriteBatch spriteBatch, Region region) {
        this.gameScreen = gameScreen;
        this.spriteBatch = spriteBatch;
        this.region = region;
    }

    @Override
    protected void processSystem() {

        spriteBatch.setColor(Color.WHITE);


        // Don't use transparency... yet
        spriteBatch.disableBlending();

        // if we can limit this to what the camera sees, we can speed it up slightly
        for (int x = 0; x < region.xSize; x++) {
            for (int y = 0; y < region.ySize; y++) {

                TileType tileType = region.tiles[x][y][gameScreen.zLevel];


                if (tileType.texture != null) {

                    spriteBatch.setColor(1, 1, 1, 1);
                    spriteBatch.draw(tileType.texture, 16 * x, 16 * y, 16f, 16f);

                } else if (gameScreen.zLevel - 1 >= 0 && region.tiles[x][y][gameScreen.zLevel - 1].floor != null) {

                    spriteBatch.setColor(0.5f, 0.5f, 0.5f, 1);
                    spriteBatch.draw(region.tiles[x][y][gameScreen.zLevel - 1].floor, 16 * x, 16 * y, 16f, 16f);

                } else if (gameScreen.zLevel - 2 >= 0 && region.tiles[x][y][gameScreen.zLevel - 2].floor != null) {

                    spriteBatch.setColor(0.25f, 0.25f, 0.25f, 1);
                    spriteBatch.draw(region.tiles[x][y][gameScreen.zLevel - 2].floor, 16 * x, 16 * y, 16f, 16f);
                }

            }
        }
        spriteBatch.enableBlending();


    }
}
