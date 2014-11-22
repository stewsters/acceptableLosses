package acceptableLosses.assets;


import acceptableLosses.map.TileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetLoader {

    private static TextureAtlas atlas;

    public static void init() {
        atlas = new TextureAtlas(Gdx.files.internal("textures/tile.atlas"));


        for (TileType tileType : TileType.values()) {
            tileType.texture = atlas.findRegion("wall/" + tileType.textureName);
            tileType.floor = atlas.findRegion("floor/" + tileType.textureName);
        }

    }


    public static void dispose() {
        atlas.dispose();
    }
}
