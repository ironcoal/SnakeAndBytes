import java.util.ArrayList;
import java.util.Iterator;

public class Walls implements Iterable<Point> {

    private ArrayList<Point> ar_walls = new ArrayList<Point>();
    
    public Walls() {
        addWall(new Point(0, 0), new Point(Configuration.WIDTH - 1, 0));
        addWall(new Point(0, 1), new Point(0, Configuration.HEIGHT - 2));
        addWall(new Point(0, Configuration.HEIGHT - 1), new Point(Configuration.WIDTH - 1, Configuration.HEIGHT - 1));
        addWall(new Point(Configuration.WIDTH - 1, 1), new Point(Configuration.WIDTH - 1, Configuration.HEIGHT - 2));
        addWall(new Point(((Configuration.WIDTH - 1) / 2), 2), 
                new Point(((Configuration.WIDTH - 1) / 2), Configuration.HEIGHT - 3));  
    }

    public void addWall(Point from, Point to) {
        ArrayList<Point> wall = from.lineTo(to);
        if (wall != null)
            ar_walls.addAll(wall);
        else
            System.out.println("Wand konnte nicht erstellt werden");
    }

    public void removeWall(Point b) {
        ar_walls.remove(b);
    }

    public boolean containsWall(Point b) {
        return ar_walls.contains(b);
    }

    public Iterator<Point> iterator() {
        return ar_walls.iterator();
    }
}