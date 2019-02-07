package Shapes;
import java.awt.*;

public class CIRCLE extends Shape

{
    protected int centerX;
    protected int centerY;
    protected int radius;
    protected Color col;


    public CIRCLE(int centerX,int centerY,int radius,Color  col)

    {
        this.name="circle";
        this.centerX=centerX;
        this.centerY=centerY;
        this.radius=radius;
        this.col = col;
    }


    public void paintComponent(Graphics2D g) {

        g.setColor(col);
        g.fillOval(centerX,centerY,radius,radius);


    }

}
