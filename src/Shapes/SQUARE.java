package Shapes;

import java.awt.*;
import javax.swing.*;

public class SQUARE extends Shape {

    protected int side;
    protected int cornerX;
    protected int cornerY;
    protected Color col;

    public SQUARE(int cornerX, int cornerY,int side, Color col)

    {
        this.name="SQUARE";
        this.side= side;
        //only needs one side defined as all others are the same.
        this.cornerX=cornerX;
        this.cornerY = cornerY;
        this.col = col;
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.setColor(col);
        g.fillRect(cornerX,cornerY,side,side);

    }

}