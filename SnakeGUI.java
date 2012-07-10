import java.awt.*;
import java.awt.event.*;

public class SnakeGUI extends Frame implements ActionListener {
        
    private Button b_new;
    private int  board_size_height = 50, 
        board_size_width = 80, 
        scale = 10;
    private GameArea game_area;


    public SnakeGUI() {
        super("Snake & Bytes");
        
        game_area = new GameArea(board_size_height, board_size_width, scale);
        game_area.setSize(board_size_width * scale, board_size_height * scale);

        setLayout(new BorderLayout());
        add(game_area, BorderLayout.CENTER);

        b_new = new Button("Neues Spiel");
        add(b_new, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        pack();
        setVisible(true);
        game_area.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
