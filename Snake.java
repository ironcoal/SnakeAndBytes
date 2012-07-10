import java.util.ArrayList;

public class Snake {

    private int length;
    ArrayList<Point> body;
    private Point direction;

    public Snake() {
        body = new ArrayList<Point>();
        
        body.add(new Point(0, 0));
        body.add(new Point(0, 1));
        body.add(new Point(0, 2));

        direction = new Point(0, 1);
        length = 5;
    }
    public boolean move(GameArea game_area) {
        Point next_field = this.getHead().add(direction);
        /* Wenn sich neuer Punkt im Spielfeld befindet,
        *  fuege den Punkt der Schlange hinzu und ueberpruefe
        *  anschliessend, ob Schlange zu lang ist. Wenn ja, loesche
        *  letzten Punkt */
        if (isPossible(next_field, game_area)) {
            body.add(next_field);
            if (body.size() > length)
                body.remove(0);
            return true;
        } else {
            /* Game Over */
            return false;
        }
    }
    public boolean isPossible(Point point, GameArea game_area) {
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
    public void changeLength(int change) {
        length += change;
    }
    public ArrayList<Point> getBody() {
        return body;
    }
}