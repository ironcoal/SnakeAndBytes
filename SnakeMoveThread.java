
public class SnakeMoveThread extends Thread {

    private GameArea game_area;
    private Snake snake;
    private boolean stop;

    public SnakeMoveThread(GameArea game_area, Snake snake) {
        this.game_area = game_area;
        this.snake = snake;
        stop = false;
        start();
    }
    public void run() {
        while (!stop) {
            try {
                sleep(500);
            }
            catch (Exception e) {
                System.out.println("Sleep nicht moeglich");
            }
            stop = !snake.move(game_area);
            game_area.repaint();
        }
    }

}