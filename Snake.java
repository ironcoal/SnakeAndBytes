import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class Snake extends Observable implements Iterable<Point> {

    private int length;
    private ArrayList<Point> body = new ArrayList<Point>();
    private Point direction;
    private GameArea game_area;
    private Bytes bytes;
    private Walls walls;

    public Snake(GameArea game_area, Walls walls) {
        this.game_area = game_area;
        this.walls = walls;

        body.add(new Point(5, 5));

        direction = Configuration.DOWN;
        length = Configuration.SNAKE_DEFAULT_LENGTH;
    }

    public boolean move() {
        game_area.updateDirection();
        Point next_field = getHead().add(direction);
        /* Wenn sich neuer Punkt im Spielfeld befindet,
         * fuege den Punkt der Schlange hinzu und ueberpruefe
         * anschliessend, ob Schlange zu lang ist. Wenn ja, loesche
         * letzten Punkt */
        if (isPossible(next_field)) {
            if (bytes.containsByte(next_field)) {
                changeLength(bytes.getType(next_field));
                if (length < 1)
                    return false;
                bytes.removeByte(next_field);
                body.add(next_field);
                bytes.addByte();
            } else {
                body.add(next_field);
            }
            while (body.size() > length)
                body.remove(0);
            return true;
        } else {
            /* Game Over */
            return false;
        }
    }

    public boolean isPossible(Point point) {
        /* Befindet sich der uebergebene Punkt ausserhalb
        *  des Spielfelds oder auf dem snake-body? */
        return (game_area.isIn(point) && !body.contains(point) && !walls.containsWall(point));
    }

    public void setDirection(Point new_direction) {
        if (direction.add(new_direction).equals(new Point(0,0)))
            System.out.println("Richtungsaenderung nicht moeglich!");
        else
            direction = new_direction;
    }

    public Point getHead() {
        return body.get(body.size() - 1);
    }

    public void changeLength(int type) {
        length += type;
        setChanged();
        notifyObservers();
    }

    public void setBytes(Bytes bytes) {
        this.bytes = bytes;
    }

    public boolean containsBody(Point b) {
        return body.contains(b);
    }

    public Iterator<Point> iterator() {
        return body.iterator();
    }

    public String toString() {
        return "Snake: " + body.toString();
    }
}