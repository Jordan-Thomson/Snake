import javax.swing.*;
import java.awt.*;

/**
 * Main class to run the game
 */
public class Main extends JFrame {

    /**
     * Constructor, adds the game panel and sets frame settings
     */
    public Main() {
        add(new GamePanel());
        setResizable(false);
        pack();
        setTitle("Snakey");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Main method to execute
     * @param args N/A
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame jFrame = new Main();
            jFrame.setVisible(true);
        });
    }
}
