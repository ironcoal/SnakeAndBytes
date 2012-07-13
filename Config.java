import java.awt.Color;
import java.awt.event.KeyEvent;

public class Config {
    public static final int
        BOARD_HEIGHT = 30,
        BOARD_WIDTH = 40,
        SCALE = 20,
        /* move frequency in ms */
        SPEED_START = 110,
        SNAKE_DEFAULT_LENGTH = 5,

        BYTE_DEFAULT = 1,
        BYTE_BLUE = 2,
        BYTE_RED = 3,

        DURATION_SPECIAL_MODE = 30,

        /* arrow keys */
        KEY_UP_1 = KeyEvent.VK_UP,
        KEY_DOWN_1 = KeyEvent.VK_DOWN,
        KEY_LEFT_1 = KeyEvent.VK_LEFT,
        KEY_RIGHT_1 = KeyEvent.VK_RIGHT,

        KEY_UP_2 = KeyEvent.VK_W,
        KEY_DOWN_2 = KeyEvent.VK_S,
        KEY_LEFT_2 = KeyEvent.VK_A,
        KEY_RIGHT_2 = KeyEvent.VK_D;


    public static final double

        FREQUENCY_BLUE = 0.2,
        FREQUENCY_RED = 0.3,
        FREQUENCY_DEFAULT = 0.5,

        /* byte ocurrency as percentage of all fields */
        OCCURENCY_BYTES = 0.005;
    
    public static final Color
        COLOR_SNAKE_HEAD = new Color(20, 130, 26),
        COLOR_SNAKE_SPECIAL = new Color(24, 224, 38),
        COLOR_BACKGROUND = new Color(20, 20, 20),
        COLOR_WALLS = new Color(240, 240, 240),
        COLOR_BYTE_BLUE = new Color(30, 59, 234),
        COLOR_BYTE_RED = new Color(203, 21, 34),
        COLOR_BYTE_DEFAULT = new Color(100, 100, 100);
    
    public static final Color[]
        COLOR_SNAKE_BODY = {new Color(22, 162, 29), new Color(52, 107, 49), new Color(52, 107, 49)};
    /* vector of arrow-keys */
    public static final Point
        UP = new Point(0, -1),
        DOWN = new Point(0, 1),
        RIGHT = new Point(1, 0),
        LEFT = new Point(-1, 0);
}


