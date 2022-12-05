package PingPingGame;

import java.awt.*;

class Entity {
    final int x;
    int y;
    int score;

    final int speed = 10;

    final int width = 20;
    final int height = 70;

    public int getSpeed() {
        return speed;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        this.score = 0;

    }

    public int getScore() {
        return score;
    }

    void draw(Graphics g){
        g.fillRect(
            this.x,
            this.y,
            this.width,
            this.height
        );
    }
}
