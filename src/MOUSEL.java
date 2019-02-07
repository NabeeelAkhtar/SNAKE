import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MOUSEL implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) { // mouse can be used to initiate game.
        if(GamePanel.vx == 0 && GamePanel.vy == 0){
            GamePanel.UP = true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GamePanel.UP = false;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
