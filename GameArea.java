import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class GameArea extends Canvas implements KeyListener {
    
    private int height, width, scale;
    /* How big is one Point in px? */
    private ArrayList<Point> board;
    
    private Snake snake;

    public GameArea(int height, int width, int scale) {
        this.height = height;
        this.width = width;
        this.scale = scale;
        board = new ArrayList<Point>();
        snake = new Snake();
        setBackground(new Color(20, 20, 20));
        this.addKeyListener(this);
        new SnakeMoveThread(this, snake);
        setVisible(true);
    }
    
    public void paint(Graphics g) {
        paintSnake(g);
    }
    public void paintSnake(Graphics g) {
        //if (snake.getBody().contains(snake.getTail())
        for (Point p: snake.getBody()) {
                g.setColor(new Color(200, 200, 200));
                g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
        }
        g.setColor(new Color(150, 150, 150));
        Point p = snake.getHead();
        g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
    }

    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.setDirection(new Point(0, -1));
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.setDirection(new Point(0, 1));
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.setDirection(new Point(1, 0));
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.setDirection(new Point(0, 1));
        }  
    }
    public boolean isIn(Point point) {
        return point.getX() < width && point.getX() >= 0 && 
        point.getY() < height && point.getY() >= 0;
    }
    public ArrayList<Point> getBoard() {
        return board;
    }
}