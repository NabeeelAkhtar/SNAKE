import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int Width = 600;
    public static final int Height = 600;
    private boolean running;
    private long timeset;
    private Thread thrd;
    private Graphics2D g;
    private BufferedImage img;
    public static final int SIZE = 20;
    private Snake HEAD;
    private String str1;
    private Snake prize;
    private ArrayList <Snake> snakes;
    public static int score;
    private boolean endgame;
    public static int vx, vy;
    public static boolean UP, DOWN, RIGHT, LEFT, START,QUIT;

    public GamePanel(){
        //key listener and mouse listener added
        keyboard r = new keyboard();
        MOUSEL l = new MOUSEL();
        setPreferredSize(new Dimension(Width,Height));
        setFocusable(true);
        requestFocus();
        addMouseListener(l);
        addKeyListener(r);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        thrd = new Thread(this);
        thrd.start();
    }

    private void setFrames(int fps){
        timeset = 1000 /fps;
    } // sets frames per second of the game.

    @Override
    public void run() { // run function allows implementation of runnable and manages thread, rendering and timing.
        if(running){return;}
        initialize();
        long timestarted, timepassed,pause;
        while (running){
            timestarted = System.nanoTime();
            update();
            processrender();
            timepassed = System.nanoTime() - timestarted;
            pause = timeset - timepassed / 1000000;
            if (pause > 0){
                try {Thread.sleep(pause);
                }catch (Exception e){e.printStackTrace();}
            }
        }
    }

    private void initialize(){ //initializes game.
        img = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
        g = img.createGraphics();
        running = true;
        setup();
       // endgame =false;
        //setFrames(10);
    }
    private void setup(){ //sets up game to initial state.
        snakes = new ArrayList<Snake>();
        HEAD = new Snake(SIZE);
        HEAD.setpos(Width/2,Height/2);
        snakes.add(HEAD);
        for(int i = 1 ; i < 3; i++){
            Snake snk = new Snake(SIZE);
            snk.setpos((HEAD.getX()+(i*SIZE)),HEAD.getY());
            snakes.add(snk);
        }
        prize = new Snake(SIZE);
        setPrize();
        score = 0;
        endgame = false;
        vx=vy=0;
        setFrames(10);
    }
    public void setPrize(){ //sets prize onto the screen
        int xp = (int)(Math.random()*(Width-50));
        xp = xp - (xp % SIZE);
        int yp = (int)(Math.random()*(Height-50));
        yp = yp -(yp%SIZE);
        prize.setpos(xp,yp);
    }
    private void processrender(){ // calls render method below.
        render(g);
        Graphics g2 = getGraphics();
        g2.drawImage(img,0,0,null);
        g2.dispose();
    }

    public int getScore() {
        return score;
    } // returns current score.

    private void update(){
        //handles game scenarios, snake movement and scoring.
        if(endgame){
            if(START){initialize();}
            return;
        }
        if(UP&&vy == 0){
            vy = -SIZE;
            vx = 0;
        }
        if(DOWN&&vy == 0){
            vy = SIZE;
            vx = 0;
        }
        if(RIGHT&&vx == 0&&vy!=0){
            vy = 0;
            vx = SIZE;
        }
        if(LEFT&&vx == 0){
            vy = 0;
            vx = -SIZE;
        }
        if(vx!=0||vy!=0){

        for(int i = snakes.size()-1;i>0;i--){
            snakes.get(i).setpos(snakes.get(i-1).getX(),snakes.get(i-1).getY());// each object follows the previous, creating a slither effect.
        }
        HEAD.move(vx,vy);

        }

        for(Snake s : snakes){
            if(s.collision(HEAD)){
                //if head collides with snake body initiate end-game scenarios, record score and time.
                endgame = true;
                ADD_TO_FILE.added();//records score and appends to scores.txt
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date nowthis = Calendar.getInstance().getTime();
                str1 = df.format(nowthis);
                break;
            }
        }

        if(prize.collision(HEAD)){
            //if head collides with snake, increment score, append new snake object to snake body.
            score++;
            setPrize();
            Snake snk = new Snake(SIZE);
            snk.setpos(-100,-100);
            snakes.add(snk);
        }

        if(HEAD.getX() < 0){HEAD.setX(Width);} //statements to handle snake going out of frame,
        if(HEAD.getX() > Width){HEAD.setX(0);} //will return snake from opposite side of screen.
        if(HEAD.getY() < 0){HEAD.setY(Height);}
        if(HEAD.getY() > Height){HEAD.setY(0);}
    }
    private void render(Graphics2D g){ //draws objects and writes onto graphic object.
        g.clearRect(0,0,Width,Height);
        for(Snake s: snakes){
            s.render(g,Color.CYAN);
        }
        prize.renderprix(g,Color.RED);

        g.setColor(Color.WHITE);

        g.drawString("SCORE: "+score,10,10);

        if(endgame){ //writing string outputs in end-game scenario.
            g.clearRect(0,0,Width,Height);
            g.setColor(Color.RED);


            g.drawString("GAME OVER !           SCORE:  " + score,0,20);
            g.setColor(Color.WHITE);
            g.drawString("      TOP 10 SCORES:    ",200,40);
            final int xl = 200;
            int yl = 40;

            HashMap<String,Integer> nmap1 = ADD_TO_LIST.LISTOSCORE(); //recieve map of scores form ADD_TO_LIST class.
            TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(new ValueComparator(nmap1));//sort recieved map using custom comparator.
            sorted.putAll(nmap1);


            for(int m = 0; m < sorted.size(); m++){  //gets rank.
                String[] keym = sorted.keySet().toArray(new String[sorted.size()]);
                String key = keym[m];
                if(key.equals(str1)){
                    int RANK = m+1;
                    g.drawString("RANK : "+RANK+" OUT OF "+sorted.size(),0,40);

                }
            }

            int count = 1;
            //output scores from sorted map
            for(Map.Entry<String,Integer>entry: sorted.entrySet()){
                if(count > 10){break;} // gets top 10
                int r =  entry.getValue();
                String q = entry.getKey();
                yl += 25;
                if (r == score && q.equals( str1)){g.setColor(Color.YELLOW);}else{g.setColor(Color.WHITE);}
                //if the current score is in the top 10, it will be highlighted
                //May cause score to not highlight , if computer is slow, it will cause delay.
                g.drawString(r+" , "+q,xl,yl);
                count++;
            }

            g.setColor(Color.WHITE);
            g.drawString("Press ENTER to restart, ESC to quit",180,500);
            if(QUIT){System.exit(0);} //close app on ESC.

        }

        if(vy == 0 && vx == 0){ //write to screen when level is set up
            g.drawString("Left click or Press WSAD / direction arrows to start",150,150);
        }
    }

}
