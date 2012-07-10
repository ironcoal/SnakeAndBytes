import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameArea extends JPanel implements KeyListener {
    
    private int height = Configuration.HEIGHT,
                width = Configuration.WIDTH,
                scale = Configuration.SCALE;

    private Snake snake;
    private Bytes bytes;
    private SnakeMoveThread move_thread;
    private Point last_direction = Configuration.DOWN;

    public GameArea() {

        setPreferredSize(new Dimension(width * scale, height * scale));
        setBackground(Configuration.COLOR_BACKGROUND);
        setDoubleBuffered(true);

        this.addKeyListener(this);
        setVisible(true);

        newGame();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        paintSnake(g);
        paintBytes(g);
    }

    public void paintBytes(Graphics g) {
        g.setColor(Configuration.COLOR_BYTES);
        for (Point p: bytes) {
            g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
        }
    }

    public void paintSnake(Graphics g) {
        for (Point p: snake) {
                g.setColor(Configuration.COLOR_SNAKE_BODY);
                g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
        }
        g.setColor(Configuration.COLOR_SNAKE_HEAD);
        Point p = snake.getHead();
        g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            last_direction = Configuration.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            last_direction = Configuration.DOWN;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            last_direction = Configuration.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            last_direction = Configuration.RIGHT;
        }  
    }

    public void updateDirection() {
        snake.setDirection(last_direction);
    }

    public boolean isIn(Point point) {
        return point.getX() < width && point.getX() >= 0 && 
            point.getY() < height && point.getY() >= 0;
    }

    public void gameOver() {
        int choice = JOptionPane.showConfirmDialog(this, "Game Over! Neuer Versuch?",
            "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            newGame();
        } else {
            System.exit(0);
        }
    }

    public void newGame() {
        snake = new Snake(this);
        bytes = new Bytes(this, snake);
        snake.setBytes(bytes);
        move_thread = new SnakeMoveThread(this, snake);        
    }

    public int getBoardHeight() {
        return height;
    }
    public int getBoardWidth() {
        return width;
    }
}