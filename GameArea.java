import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Map.Entry;

public class GameArea extends JPanel implements KeyListener {
    
    private int height = Config.BOARD_HEIGHT,
                width = Config.BOARD_WIDTH,
                scale = Config.SCALE,
                players,
                players_stop;

    private Snake[] snakes;
    private Bytes bytes;
    private Walls walls;
    private SnakeMoveThread move_thread;
    private Point[] last_direction;
    private boolean start;

    public GameArea(int players) {
        this.players = players;
        last_direction = new Point[players];

        setPreferredSize(new Dimension(width * scale, height * scale));
        setBackground(Config.COLOR_BACKGROUND);
        setDoubleBuffered(true);
        
        walls = new Walls();

        this.addKeyListener(this);
        setVisible(true);

        newGame();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        paintWalls(g);
        paintSnakes(g);
        paintBytes(g);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        g.setColor(Config.COLOR_BACKGROUND);
        /* Paint Score */
        if (players == 1) {
            g.drawString("Score: " + snakes[0].getLength(), 5, 15);
            /* Paint Special Mode */
            g.drawString("Special Mode: " + (snakes[0].isSpecialMode() ? "on for " + 
                snakes[0].getModeCount() + " fields": "off"), 5, height * scale - 6);
            /* Paint Speed */
            g.drawString("Speed: " + (int) (1000.0 / snakes[0].getSpeed() * scale) + " px/sec", 
                width * scale - 135, height * scale - 6);
        }
    }

    public void paintBytes(Graphics g) {
        
        for (Entry<Point, Integer> p: bytes) {
            g.setColor(bytes.getTypeColor(p.getValue()));
            g.fillRect(scale * p.getKey().getX(), scale * p.getKey().getY(), scale, scale);
        }
    }

    public void paintSnakes(Graphics g) {
        
        for (int i = 0; i < players; i++) {
            g.setColor(Config.COLOR_SNAKE_BODY[i]);
            for (Point p: snakes[i]) {
                    g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
            }
        }

        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, scale));

        for (int i = 0; i < players; i++) {
            if (snakes[i].isSpecialMode())
                g.setColor(Config.COLOR_SNAKE_SPECIAL);
            else
                g.setColor(Config.COLOR_SNAKE_HEAD);
            Point p = snakes[i].getHead();
            g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
            g.setColor(Config.COLOR_WALLS);
            g.drawString(Integer.toString(i), scale * p.getX() + 3, scale * (p.getY() + 1) - 2);
        }
    }

    public void paintWalls(Graphics g) {
        g.setColor(Config.COLOR_WALLS);
        for (Point p: walls) {
                g.fillRect(scale * p.getX(), scale * p.getY(), scale, scale);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Config.KEY_UP_1) {
            last_direction[0] = Config.UP;
        }
        if (e.getKeyCode() == Config.KEY_DOWN_1) {
            last_direction[0] = Config.DOWN;
        }
        if (e.getKeyCode() == Config.KEY_LEFT_1) {
            last_direction[0] = Config.LEFT;
        }
        if (e.getKeyCode() == Config.KEY_RIGHT_1) {
            last_direction[0] = Config.RIGHT;
        }
        if (e.getKeyCode() == Config.KEY_UP_2) {
            last_direction[1] = Config.UP;
        }
        if (e.getKeyCode() == Config.KEY_DOWN_2) {
            last_direction[1] = Config.DOWN;
        }
        if (e.getKeyCode() == Config.KEY_LEFT_2) {
            last_direction[1] = Config.LEFT;
        }
        if (e.getKeyCode() == Config.KEY_RIGHT_2) {
            last_direction[1] = Config.RIGHT;
        }  
        if (!start) {
            for (int i = 0; i < players; i++) 
                move_thread = new SnakeMoveThread(this, snakes[i], i);
            start = true;
        }

    }

    public void updateDirection() {
        for (int i = 0; i < players; i++)
            snakes[i].setDirection(last_direction[i]);
    }

    public boolean isIn(Point point) {
        return point.getX() < width && point.getX() >= 0 && 
            point.getY() < height && point.getY() >= 0;
    }

    public void gameOver(int player) {
        System.out.println(player);
        if (players == 1 || players_stop + 1 >= players) {
            int choice = JOptionPane.showConfirmDialog(this, ((players > 1) ? 
                ("Player " + player + " has won!\n") : ("Final Score: " + snakes[0].getLength())) + 
                "\nTry again?", "Game Over", JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                newGame();
            } else {
                System.exit(0);
            }
        } else {
            players_stop++;
        }
    }

    public void newGame() {
        snakes = new Snake[players];

        for (int i = 0; i < players; i++) {
            last_direction[i] = Config.UP;
            snakes[i] = new Snake(this, walls);
        }
        for (Snake s: snakes)
            s.linkSnakes(snakes);

        bytes = new Bytes(this, snakes, walls);
        
        for (int i = 0; i < players; i++)
            snakes[i].setBytes(bytes);

        start = false;
        players_stop = 0;
    }

    public int getBoardHeight() {
        return height;
    }
    public int getBoardWidth() {
        return width;
    }
}