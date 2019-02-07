import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboard implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override //key listener for handling all 4 direction movements and Escape and Enter keys
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if(k==KeyEvent.VK_UP ||k==KeyEvent.VK_W)GamePanel.UP=true;
        if(k==KeyEvent.VK_DOWN ||k==KeyEvent.VK_S)GamePanel.DOWN=true;
        if(k==KeyEvent.VK_RIGHT ||k==KeyEvent.VK_D)GamePanel.RIGHT=true;
        if(k==KeyEvent.VK_LEFT ||k==KeyEvent.VK_A)GamePanel.LEFT=true;
        if(k==KeyEvent.VK_ENTER)GamePanel.START=true;
        if(k==KeyEvent.VK_ESCAPE)GamePanel.QUIT=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if(k==KeyEvent.VK_UP ||k==KeyEvent.VK_W)GamePanel.UP=false;
        if(k==KeyEvent.VK_DOWN ||k==KeyEvent.VK_S)GamePanel.DOWN=false;
        if(k==KeyEvent.VK_RIGHT ||k==KeyEvent.VK_D)GamePanel.RIGHT=false;
        if(k==KeyEvent.VK_LEFT ||k==KeyEvent.VK_A)GamePanel.LEFT=false;
        if(k==KeyEvent.VK_ENTER)GamePanel.START=false;
        if(k==KeyEvent.VK_ESCAPE)GamePanel.QUIT=false;

    }
}
