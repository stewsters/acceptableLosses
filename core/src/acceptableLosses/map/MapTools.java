package acceptableLosses.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.stewsters.util.math.Point2i;

import java.util.LinkedList;

public class MapTools {


    public static Vector2 getDirectionVector(int x1, int y1, int x2, int y2) {
        Vector2 cell1 = world2window(x1, y1);
        Vector2 cell2 = world2window(x2, y2);
        return new Vector2(cell2.x - cell1.x, cell2.y - cell1.y);
    }

    public static int manhattanDistance(int x, int y, int x1, int y1) {

        return Math.abs(x - x1) + Math.abs(y - y1);
    }

    public static LinkedList<Point2i> getNeighbors(int x, int y, int range) {

        LinkedList<Point2i> results = new LinkedList<Point2i>();
        for (int x1 = x - range; x1 < x + range; x1++) {
            for (int y1 = y - range; y1 < y + range; y1++) {
                if (x1 == x || y1 == y)
                    continue;
                if (manhattanDistance(x, y, x1, y1) <= range) {
                    results.add(new Point2i(x1, y1));
                }

            }
        }

        return results;
    }

    public static LinkedList<Point2i> getNeighbors(int x, int y) {

        return getNeighbors(x, y, 1);
    }

    public static Point2i window2world(int x, int y, OrthographicCamera camera) {
        Vector3 pos = new Vector3(x, y, 0);
        camera.unproject(pos);

        return new Point2i((int) ((pos.x)), (int) ((pos.y)));
    }

    public static Vector2 world2window(float x, float y) {


        return new Vector2(x, y);
    }


    public static Point2i libgdx2world(float x, float y) {
        return new Point2i((int) (x), (int) (y));
    }
}

