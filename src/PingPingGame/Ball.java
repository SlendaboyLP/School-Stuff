package PingPingGame;

import java.awt.*;

class Ball {
    int x;
    int y;

    final int radius = 10;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void draw(Graphics g){
        g.fillOval(
            this.x,
            this.y,
            this.radius,
            this.radius
        );
    }
}
