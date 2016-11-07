package acceptableLosses.systems;


import acceptableLosses.components.Citizen;
import acceptableLosses.components.Position;
import acceptableLosses.screens.GameScreen;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CitizenRenderSystem extends EntityProcessingSystem {

    @Wire
    ComponentMapper<Citizen> cm;

    @Wire
    ComponentMapper<Position> pm;

    private GameScreen gameScreen;
    private SpriteBatch spriteBatch;

    public CitizenRenderSystem(GameScreen gameScreen, SpriteBatch spriteBatch) {
        super(Aspect.getAspectForAll(Citizen.class, Position.class));
        this.gameScreen = gameScreen;
        this.spriteBatch = spriteBatch;
    }


    @Override
    protected void process(Entity e) {

        Position position = pm.get(e);
        if (position.z != gameScreen.zLevel) {
            // TODO: check to see if we are on screen horizontally, no sense drawing stuff off screen.
            return;
        }

        Citizen citizen = cm.get(e);

        // BODY
        if (citizen.skinColor != null) {
            spriteBatch.setColor(citizen.skinColor.color);
        } else {
            spriteBatch.setColor(1, 1, 1, 1);
        }
        spriteBatch.draw(citizen.bodyShape.texture, position.x, position.y, 1, 1);

        // GARMENT

        spriteBatch.setColor(citizen.garmentColor.r, citizen.garmentColor.g, citizen.garmentColor.b, 1);
        if (citizen.garment != null) {
            spriteBatch.draw(citizen.garment.texture, position.x, position.y, 1, 1);
        }

        //HAIR or HAT
        spriteBatch.setColor(citizen.hatColor.r, citizen.hatColor.g, citizen.hatColor.b, 1);
        if (citizen.hat != null) {
            spriteBatch.draw(citizen.hat.texture, position.x, position.y, 1, 1);

        } else if (citizen.hairStyle != null && citizen.hairStyle.texture != null) {

            if (citizen.hairColor != null) {
                spriteBatch.setColor(citizen.hairColor.color);
            } else
                spriteBatch.setColor(1, 1, 1, 1);

            spriteBatch.draw(citizen.hairStyle.texture, position.x, position.y, 1, 1);

        }
    }
}
