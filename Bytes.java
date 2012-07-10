import java.util.ArrayList;
import java.util.Iterator;

public class Bytes implements Iterable<Point> {

    private ArrayList<Point> ar_bytes = new ArrayList<Point>();
    private GameArea game_area;
    private Snake snake;
    private Walls walls;

    public Bytes(GameArea game_area, Snake snake, Walls walls) {
        this.game_area = game_area;
        this.snake = snake;
        this.walls = walls;
        
        int number = (int) (game_area.getBoardHeight() * game_area.getBoardWidth() * Configuration.BYTES_NUMBER);
        
        for (int i = 0; i < number; i++)
            addByte();
    }

    public void addByte() {
        ar_bytes.add(randByte());
    }

    public void removeByte(Point b) {
        ar_bytes.remove(b);
    }

    public boolean containsByte(Point b) {
        return ar_bytes.contains(b);
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

    public Iterator<Point> iterator() {
        return ar_bytes.iterator();
    }
}