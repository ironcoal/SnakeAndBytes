import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Bytes implements Iterable<Entry<Point, Integer>> {

    private HashMap<Point,Integer> ar_bytes;
    private GameArea game_area;
    private Snake snake;
    private Walls walls;

    public Bytes(GameArea game_area, Snake snake, Walls walls) {
        this.game_area = game_area;
        this.snake = snake;
        this.walls = walls;
        
        int number = (int) (game_area.getBoardHeight() * game_area.getBoardWidth() * Configuration.OCCURENCY_BYTES);
        ar_bytes = new HashMap<Point,Integer>((int) number * 4 / 3);
        for (int i = 0; i < number; i++)
            addByte();
    }

    public void addByte() {
        ar_bytes.put(randByte(), new Integer(randType()));
    }

    private int randType() {
        int rand = (int) (Math.random() * 100);
        if (rand > 100 - 100 * Configuration.FREQUENCY_BLUE)
            return Configuration.BYTE_BLUE;
        else if (rand > 100 * Configuration.FREQUENCY_RED)
            return Configuration.BYTE_DEFAULT;
        else
            return Configuration.BYTE_RED;
    }

    public Color getTypeColor(int type) {
        if (type == Configuration.BYTE_BLUE)
            return Configuration.COLOR_BYTE_BLUE;
        if (type == Configuration.BYTE_RED)
            return Configuration.COLOR_BYTE_RED;
        else
            return Configuration.COLOR_BYTE_DEFAULT;
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
        do {
            rp = new Point((int) (Math.random() * (game_area.getBoardWidth() - 1)), 
                (int) (Math.random() * (game_area.getBoardHeight() - 1)));
        } while (snake.containsBody(rp) || containsByte(rp) || walls.containsWall(rp));
        return rp;
    }

    public Iterator<Entry<Point, Integer>> iterator() {
        return ar_bytes.entrySet().iterator();
    } 

    public String toString() {
        return "Bytes: " + ar_bytes.toString();
    }
}