
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
                sleep(Configuration.SPEED);
            }
            catch (Exception e) {
                System.out.println("Sleep nicht moeglich");
            }
            if (!snake.move()) {
                stop = true;
                game_area.gameOver();
            }
            game_area.validate();
            game_area.repaint();
        }
    }

}