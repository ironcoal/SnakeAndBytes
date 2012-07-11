import java.util.ArrayList;
import java.util.Iterator;

public class Snake implements Iterable<Point> {

    private int length;
    private ArrayList<Point> body = new ArrayList<Point>();
    private Point direction;
    private GameArea game_area;
    private Bytes bytes;
    private Walls walls;
    private boolean special_mode;
    private int count_sm;
    private int speed;

    public Snake(GameArea game_area, Walls walls) {
        this.game_area = game_area;
        this.walls = walls;

        body.add(new Point(5, 5));
        count_sm = 0;
        /* substitute for no direction */
        direction = new Point(0,0);

        speed = Configuration.SPEED_START;
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
                int byte_type = bytes.getType(next_field);
                if (byte_type == Configuration.BYTE_BLUE) {
                    count_sm = Configuration.DURATION_SPECIAL_MODE;
                    special_mode = true;
                } else if (byte_type == Configuration.BYTE_RED) {
                    if (isSpecialMode()) {
                        changeLength(5);
                    } else {
                        changeLength(-5);
                        accelerate(-5);
                    }
                } else {
                    changeLength(1);
                }
                if (length < 1)
                    return false;
                bytes.removeByte(next_field);
                body.add(next_field);
                bytes.addByte();
            } else {
                body.add(next_field);
            }
            if (count_sm > 0)
                count_sm--;
            else
                special_mode = false;
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

    public void changeLength(int change) {
        length += change;
    }

    public void accelerate(int change) {
        speed += change;
    }

    public void setBytes(Bytes bytes) {
        this.bytes = bytes;
    }

    public int getLength() {
        return length;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean containsBody(Point b) {
        return body.contains(b);
    }

    public boolean isSpecialMode() {
        return special_mode;
    }

    public int getModeCount() {
        return count_sm;
    }

    public Iterator<Point> iterator() {
        return body.iterator();
    }

    public String toString() {
        return "Snake: " + body.toString();
    }
}