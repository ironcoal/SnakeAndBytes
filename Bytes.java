import java.util.ArrayList;

public class Bytes {
    ArrayList<Point> bytes;

    GameArea game_area;
    Snake snake;
    private static Bytes unique = null;

    public Bytes(GameArea game_area, Snake snake) {
        this.game_area = game_area;
        this.snake = snake;

        bytes = new ArrayList<Point>();
        int number = (int) (game_area.getBoardHeight() * game_area.getBoardWidth() * 0.002);
        for (int i = 0; i < number; i++)
            addByte();
        bytes.add(new Point(3,3));
    }
    public ArrayList<Point> getBytes() {
        return bytes;
    } 
    public void addByte() {
        bytes.add(randByte());
    }
    public void removeByte(Point b) {
        bytes.remove(b);
    }
    public boolean containsByte(Point b) {
        return bytes.contains(b);
    }

    private Point randByte() {
        Point rp;
        do {
            rp = new Point((int) (Math.random() * (game_area.getBoardWidth() - 1)), 
                (int) (Math.random() * (game_area.getBoardHeight() - 1)));
        } while (snake.getBody().contains(rp));
        return rp;
    }
}