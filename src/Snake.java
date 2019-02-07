
import Shapes.*;

import java.awt.*;

public class Snake {
    private int x,y,size;
    public Snake(int size){this.size = size;}

    public int getX() {return x;}

    public int getY() {return y;}

    public void setX(int x) {
        this.x = x;
    }//set x co-ordinate.

    public void setY(int y) {
        this.y = y;
    } //set y co-ordinate.

    public void setpos(int x, int y){this.x = x; this.y = y;} //set position of object

    public void move(int vx , int vy){ //move by horizontally and vertically be adding distance to axis.
        x+=vx;
        y+=vy;
    }


    public Rectangle getbound(){return new Rectangle(x,y,size,size);} //gets the parameters for enclosing rectangle of snake object

    public boolean collision(Snake s){ //checks for collisions between different snake objects.
                                       //essential for scoring.

        if(s==this)return false;
        return getbound().intersects(s.getbound());
    }

    public void render(Graphics2D g2,Color c){ // function for drawing snake

        SQUARE sq = new SQUARE(x+1,y+1,size-2,c);
        sq.paintComponent(g2);
    }

    public void renderprix(Graphics2D g2,Color c){ //function for drawing the prize
                                                    // (only called when object created is intended to be a prize object)

        CIRCLE sq = new CIRCLE(x+1,y+2,size,c);
        sq.paintComponent(g2);
    }
}
