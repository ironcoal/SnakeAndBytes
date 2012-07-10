import java.util.HashSet;

public class Bytes {
    HashSet<Point> bytes;

    public Bytes(GameArea game_area) {
        bytes = new HashSet<Point>();
        bytes.add(randPoint(game_area));
        bytes.add(randPoint(game_area));
        bytes.add(randPoint(game_area));
    }

    private Point randPoint(GameArea game_area) {
        return new Point((int) Math.random() * game_area.getHeight(), 
            (int) Math.random() * game_area.getWidth());
    }
}