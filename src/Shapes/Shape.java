package Shapes;
import javax.swing.*;
import java.awt.*;

    public abstract class Shape extends JComponent
    {
        protected String name="shape";
        protected String color="black";
        public abstract void paintComponent(Graphics2D g);
    }


