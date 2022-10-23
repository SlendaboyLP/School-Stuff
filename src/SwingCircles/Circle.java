package SwingCircles;

import java.awt.*;

class Circle {
    int x, y , radius;
    int r,g,b;

    public Circle(int x, int y, int radius, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.r = r;
        this.b = b;
        this.g = g;
    }

    @Deprecated
    static void drawCircle(Graphics g, Circle c1){
        g.setColor(new Color(c1.r, c1.g, c1.b));
        g.drawArc(
            c1.x - c1.radius,
            c1.y - c1.radius,
            c1.radius * 2,
            c1.radius * 2,
            0,
            360
        );
    }

    static void drawCircle(Graphics2D g, Circle c1){
        g.setColor(new Color(c1.r, c1.g, c1.b));
        g.fillOval(
            c1.x - c1.radius,
            c1.y - c1.radius,
            c1.radius * 2,
            c1.radius * 2
        );
    }
}
