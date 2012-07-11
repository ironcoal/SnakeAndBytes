import java.awt.Color;

public class Configuration {
    public static final int
        HEIGHT = 20,
        WIDTH = 50,
        SCALE = 20,
        /* move frequency in ms */
        SPEED = 100,
        SNAKE_DEFAULT_LENGTH = 5,

        BYTE_DEFAULT = 1,
        BYTE_GROW_MORE = 2,
        BYTE_SHORTEN = -1;
    
    public static final double
        /* byte ocurrency as percentage of all fields */
        BYTES_NUMBER = 0.005;
    
    public static final Color
        COLOR_SNAKE_BODY = new Color(200, 200, 200),
        COLOR_SNAKE_HEAD = new Color(150, 150, 150),
        COLOR_BACKGROUND = new Color(20, 20, 20),
        COLOR_WALLS = new Color(240, 240, 240),
        COLOR_BYTE_GROW_MORE = new Color(30, 59, 234),
        COLOR_BYTE_SHORTEN = new Color(203, 21, 34),
        COLOR_BYTE_DEFAULT = new Color(100, 100, 100);
    
    /* vector of arrow-keys */
    public static final Point
        UP = new Point(0, -1),
        DOWN = new Point(0, 1),
        RIGHT = new Point(1, 0),
        LEFT = new Point(-1, 0);
}


