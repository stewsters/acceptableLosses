package acceptableLosses.systems;


import acceptableLosses.components.Citizen;
import acceptableLosses.components.Resume;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stewsters.util.types.Gender;

public class TherapistRenderSystem extends EntityProcessingSystem {

    @Wire
    ComponentMapper<Citizen> cm;

    @Wire
    ComponentMapper<Resume> rm;

    private SpriteBatch spriteBatch;
    private BitmapFont font;

    private int lineNo = 0;

    public TherapistRenderSystem(SpriteBatch spriteBatch, BitmapFont font) {

        super(Aspect.getAspectForAll(Citizen.class));
        this.spriteBatch = spriteBatch;
        this.font = font;
    }

    protected void begin() {
        lineNo = 1;
    }

    @Override
    protected void process(Entity e) {

        Citizen citizen = cm.get(e);
        Resume resume = rm.get(e);
        Gdx.app.log(this.getClass().getName(), Integer.toString(lineNo) + " " + citizen.firstName);


        String citizenInfo = (citizen.gender == Gender.MALE ? "M" : "F") +
                " " + citizen.firstName +
                " " + citizen.lastName;


        font.draw(spriteBatch, citizenInfo, 10, font.getLineHeight() * lineNo);

        lineNo++;


//        System.out.println("lol");
//
//
//        // BODY
//        if (citizen.skinColor != null) {
//            spriteBatch.setColor(citizen.skinColor.color);
//        } else {
//            spriteBatch.setColor(1, 1, 1, 1);
//        }
//        spriteBatch.draw(citizen.bodyShape.texture, position.x, position.y, 1, 1);
//
//        // GARMENT
//
//        spriteBatch.setColor(1, 1, 1, 1);
//        if (citizen.garment != null) {
//            spriteBatch.draw(citizen.garment.texture, position.x, position.y, 1, 1);
//        }
//
//        //HAIR or HAT
//        if (citizen.hat != null) {
//            spriteBatch.draw(citizen.hat.texture, position.x, position.y, 1, 1);
//
//        } else if (citizen.hairStyle != null && citizen.hairStyle.texture != null) {
//
//            if (citizen.hairColor != null) {
//                spriteBatch.setColor(citizen.hairColor.color);
//            } else
//                spriteBatch.setColor(1, 1, 1, 1);
//
//            spriteBatch.draw(citizen.hairStyle.texture, position.x, position.y, 1, 1);
//        }
    }
}
