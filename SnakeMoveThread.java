
public class SnakeMoveThread extends Thread {

    private GameArea game_area;
    private Snake snake;
    private boolean stop;
    private int player;

    public SnakeMoveThread(GameArea game_area, Snake snake, int player) {
        this.game_area = game_area;
        this.snake = snake;
        this.player = player;
        stop = false;
        start();
    }

    public void run() {
        while (!stop) {
            try {
                sleep(snake.getSpeed());
            } catch (Exception e) {
                System.out.println("Sleep nicht moeglich");
            }
            if (!snake.move()) {
                stop = true;
                game_area.gameOver(player);
            }
            game_area.validate();
            game_area.repaint();
        }
    }

}