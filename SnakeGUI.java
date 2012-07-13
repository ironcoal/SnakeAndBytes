import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class SnakeGUI extends JFrame implements ActionListener {
        
    //private JButton b_new;
    private GameArea game_area;

    public SnakeGUI() {
        super("Snake & Bytes");
        
        /*  Option for choosing number of players */

        game_area = new GameArea(1);

        add(game_area);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        game_area.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {}
}
