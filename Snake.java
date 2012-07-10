import java.util.ArrayList;
import java.util.Iterator;

public class Snake implements Iterable<Point> {

    private int length;
    ArrayList<Point> body = new ArrayList<Point>();
    private Point direction;
    GameArea game_area;
    Bytes bytes;

    public Snake(GameArea game_area) {
        this.game_area = game_area;

        body.add(new Point(0, 0));
        body.add(new Point(0, 1));
        body.add(new Point(0, 2));

        direction = new Point(0, 1);
        length = 5;
    }

    public boolean move() {
        Point next_field = this.getHead().add(direction);
        /* Wenn sich neuer Punkt im Spielfeld befindet,
        *  fuege den Punkt der Schlange hinzu und ueberpruefe
        *  anschliessend, ob Schlange zu lang ist. Wenn ja, loesche
        *  letzten Punkt */
        if (isPossible(next_field)) {
            if (bytes.containsByte(next_field)) {
                grow();
                bytes.removeByte(next_field);
                bytes.addByte();
            }
            body.add(next_field);
            if (body.size() > length)
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
        return (game_area.isIn(point) && !body.contains(point));
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

    public void grow() {
        length++;
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
}