import java.util.HashSet;
import java.util.Iterator;

public class Walls implements Iterable<Point> {

    private HashSet<Point> ar_walls;
    
    public Walls() {
        ar_walls = new HashSet<Point>((int) (Configuration.HEIGHT * Configuration.WIDTH * 0.5));
        /* Default walls for border */
        addWall(new Point(0, 0), new Point(Configuration.WIDTH - 1, 0));
        addWall(new Point(0, 1), new Point(0, Configuration.HEIGHT - 2));
        addWall(new Point(0, Configuration.HEIGHT - 1), new Point(Configuration.WIDTH - 1, Configuration.HEIGHT - 1));
        addWall(new Point(Configuration.WIDTH - 1, 1), new Point(Configuration.WIDTH - 1, Configuration.HEIGHT - 2));
        /* Add custom walls here */
        addWall(new Point(((Configuration.WIDTH - 1) / 2), 3), 
                new Point(((Configuration.WIDTH - 1) / 2), Configuration.HEIGHT - 4));  
    }

    public void addWall(Point from, Point to) {
        HashSet<Point> wall = from.lineTo(to);
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