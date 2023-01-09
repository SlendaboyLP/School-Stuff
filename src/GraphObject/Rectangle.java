package GraphObject;

import java.awt.*;

public class Rectangle extends GraphObject{

    int w, h;

    public Rectangle(int x, int y, int w, int h) {
        super(x, y);
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw() {
        GraphFrame frame = new GraphFrame(this);
    }

    @Override
    public boolean hit(int x, int y) {
        return false;
    }
}
