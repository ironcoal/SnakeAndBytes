import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Bytes implements Iterable<Entry<Point, Integer>> {

    private HashMap<Point,Integer> ar_bytes;
    private GameArea game_area;
    private Snake[] snakes;
    private Walls walls;

    public Bytes(GameArea game_area, Snake[] snakes, Walls walls) {
        this.game_area = game_area;
        this.snakes = snakes;
        this.walls = walls;
        
        int occ = (int) (Config.BOARD_WIDTH * Config.BOARD_HEIGHT * Config.OCCURENCY_BYTES);
        ar_bytes = new HashMap<Point,Integer>((int) (occ * 4 / 3));
        for (int i = 0; i < occ; i++)
            addByte();
    }

    public void addByte() {
        ar_bytes.put(randByte(), new Integer(randType()));
    }

    private int randType() {
        int rand = (int) (Math.random() * 100);
        if (rand > 100 - 100 * Config.FREQUENCY_BLUE)
            return Config.BYTE_BLUE;
        else if (rand > 100 * Config.FREQUENCY_RED)
            return Config.BYTE_DEFAULT;
        else
            return Config.BYTE_RED;
    }

    public Color getTypeColor(int type) {
        if (type == Config.BYTE_BLUE)
            return Config.COLOR_BYTE_BLUE;
        if (type == Config.BYTE_RED)
            return Config.COLOR_BYTE_RED;
        else
            return Config.COLOR_BYTE_DEFAULT;
    }

    public void removeByte(Point b) {
        ar_bytes.remove(b);
    }

    public boolean containsByte(Point b) {
        return ar_bytes.containsKey(b);
    }

    public int getType(Point b) {
        return ar_bytes.get(b);
    }

    private Point randByte() {
        Point rp;
        /* Get random Point in GameArea and return, if it's not on snake-body */
        boolean free; 
        do {
            free = false;
            rp = Point.randomPoint(Config.BOARD_WIDTH, Config.BOARD_HEIGHT);

            for (int i = 0; i < snakes.length; i++) {
                if (snakes[i].containsBody(rp))
                    free = true;
            }
        } while (free || containsByte(rp) || walls.containsWall(rp));
        return rp;
    }

    public Iterator<Entry<Point, Integer>> iterator() {
        return ar_bytes.entrySet().iterator();
    } 

    public String toString() {
        return "Bytes: " + ar_bytes.toString();
    }
}