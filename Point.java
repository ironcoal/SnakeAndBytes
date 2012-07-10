import java.awt.*;

public class Point {

    protected int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Point add(Point point) {
        return new Point(this.x + point.getX(), this.y + point.getY());
    }
    @Override
    public boolean equals(Object o) {
        return this.x == ((Point) o).getX() && this.y == ((Point) o).getY();
    }
    public String toString() {
        return "Punkt: [" + x + "," + y + "]";
    }

}