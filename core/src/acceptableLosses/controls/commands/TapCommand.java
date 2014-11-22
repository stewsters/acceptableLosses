package acceptableLosses.controls.commands;


import com.stewsters.util.math.Point2i;

public class TapCommand implements Command {

    public Point2i point2i;

    public TapCommand(Point2i point2i) {
        this.point2i = point2i;

    }
}
