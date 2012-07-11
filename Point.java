import java.util.ArrayList;

public class Point {

    private int x, y;
    
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

    public Point substract(Point point) {
        return new Point(this.x - point.getX(), this.y - point.getY());
    }

    public ArrayList<Point> lineTo(Point to) {
        Point difference = to.substract(this);
        ArrayList<Point> ar_line = new ArrayList<Point>();
        
        if (difference.getX() != 0 && difference.getY() != 0) {
            System.out.println("Only straight walls allowed!");
            return null;
        } else if (difference.getX() != 0) {
            for (int i = 0; i <= difference.getX(); i++)
                ar_line.add(new Point(getX() + i, getY()));
        } else if (difference.getY() != 0) {
            for (int i = 0; i <= difference.getY(); i++)
                ar_line.add(new Point(getX(), getY() + i));
        } else {
            ar_line.add(this);
        }
        return ar_line;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (obj.getClass() != this.getClass()))
             return false;
        return (this.x == ((Point) obj).getX()) && (this.y == ((Point) obj).getY());
    }

    @Override
    public int hashCode() {
        return x * 10000 + y;
    }

    public String toString() {
        return "Punkt: [" + x + "," + y + "]";
    }
}