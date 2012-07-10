import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameArea extends JPanel implements KeyListener {
    
    private int height, width, scale;    
    private Snake snake;
    private Bytes bytes;

    public GameArea() {
        height = 20; 
        width = 50;
        scale = 20;

        snake = new Snake(this);
        bytes = new Bytes(this, snake);
        snake.setBytes(bytes);

        setPreferredSize(new Dimension(width * scale, height * scale));
        setBackground(new Color(20, 20, 20));
        setDoubleBuffered(true);

        this.addKeyListener(this);
        setVisible(true);
        new SnakeMoveThread(this, snake);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        paintSnake(g);
        paintBytes(g);
    }

    public void paintBytes(Graphics g) {
        g.setColor(new Color(100, 100, 100));
        for (Point p: bytes.getBytes()) {
            g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
            //g.drawString("1", p.getX() * scale, p.getY() * scale);
        }
    }

    public void paintSnake(Graphics g) {
        for (Point p: snake.getBody()) {
                g.setColor(new Color(200, 200, 200));
                g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
        }
        g.setColor(new Color(150, 150, 150));
        Point p = snake.getHead();
        g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.setDirection(new Point(0, -1));
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.setDirection(new Point(0, 1));
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.setDirection(new Point(-1, 0));
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.setDirection(new Point(1, 0));
        }  
    }
    public boolean isIn(Point point) {
        return point.getX() < width && point.getX() >= 0 && 
            point.getY() < height && point.getY() >= 0;
    }
    public int getBoardHeight() {
        return height;
    }
    public int getBoardWidth() {
        return width;
    }
}