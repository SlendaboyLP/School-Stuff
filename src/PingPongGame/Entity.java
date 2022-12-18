package PingPongGame;

import java.awt.*;

class Entity {
    private final int x;
    private int y;
    private int score;

    final int speed = 10;

    final int width = 20;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    final int height = 140;

    public int getSpeed() {
        return speed;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setScore(int score) {
        this.score = score;
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
