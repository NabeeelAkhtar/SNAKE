
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(){                                // creates JFrame.
        setTitle("SNAKE (REG_NO: 1702097)");
        setContentPane(new GamePanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(GamePanel.Width,GamePanel.Height));
        pack();
    }
}
