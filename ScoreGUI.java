import javax.swing.*;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

public class ScoreGUI extends JFrame implements Observer {

    private JLabel score;

    public ScoreGUI() {
        super("Score");
        setLayout(new GridLayout(0,1));
        add(new JLabel("Score (Länge der Schlange)"));
        score = new JLabel("0");
        add(score);

        setLocation(1000, 300);
        pack();
        setVisible(true);
    }

    @Override
    public void update(Observable obs, Object length) {
        score.setText(length.toString());
    }

}