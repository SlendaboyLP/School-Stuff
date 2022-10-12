package SwingCircles;

import java.awt.*;

public class Circle {
    int x, y , radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    static void drawCircle(Graphics g, Circle c1){
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
        g.drawArc(
            c1.x - c1.radius,
            c1.y - c1.radius,
            c1.radius * 2,
            c1.radius * 2,
            0,
            360
        );
    }
}
