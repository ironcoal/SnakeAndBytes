import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class SnakeGUI extends JFrame implements ActionListener {
        
    //private JButton b_new;
    private GameArea game_area;

    public SnakeGUI() {
        super("Snake & Bytes");
        
        game_area = new GameArea();

        add(game_area);

        ScoreDialog sd = new ScoreDialog(this);
        snake.addObserver(sd);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        game_area.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {}
}
