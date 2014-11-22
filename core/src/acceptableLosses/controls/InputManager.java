package acceptableLosses.controls;

import acceptableLosses.controls.commands.Command;
import acceptableLosses.controls.commands.TapCommand;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.stewsters.util.math.Point2i;

import java.util.LinkedList;


public class InputManager {


    public GestureDetector gesture;
    public ZoomInputProcessor zoomInputProcessor;
    private InputMultiplexer manager;
    private LinkedList<Command> commandQueue;

    public InputManager(OrthographicCamera camera) {

        commandQueue = new LinkedList<Command>();

        gesture = new GestureDetector(new GestureDetectorController(camera, this));
        zoomInputProcessor = new ZoomInputProcessor(camera);
        manager = new InputMultiplexer(gesture, zoomInputProcessor);
        Gdx.input.setInputProcessor(manager);
    }

    public void setEnabled(boolean enabled) {
        if (enabled)
            Gdx.input.setInputProcessor(manager);
        else
            Gdx.input.setInputProcessor(null);
    }

    // http://en.wikipedia.org/wiki/Command_pattern#Java
    public void tapCommand(Point2i point2i) {
        Gdx.app.log("command", "tap " + point2i.toString());
        commandQueue.add(new TapCommand(point2i));
    }

    public Command popCommand() {
        if (commandQueue.size() == 0)
            return null;
        Gdx.app.log("command", "pop - command");
        return commandQueue.pop();

    }
}
