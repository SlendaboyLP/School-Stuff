package PingPongGame;

import java.awt.*;

class Ball {

    private PongPane panel;

    final int radius = 15;

    double curX, curY;
    double prevX, prevY;

    //randomly choose the direction the ball is going
    final int ORIGINAL_SPEED = 3;
    double velX = Math.random() > 0.5 ? ORIGINAL_SPEED : -ORIGINAL_SPEED;
    double velY = Math.random() > 0.5 ? ORIGINAL_SPEED : -ORIGINAL_SPEED;

    public Ball(int x, int y, PongPane panel) {
        this.curX = x;
        this.curY = y;
        prevX = x;
        prevY = y;
        this.panel = panel;
    }

    void draw(Graphics g){
        g.fillOval(
            (int) this.curX,
            (int) this.curY,
            this.radius,
            this.radius
        );
    }

    public int getRadius() {
        return radius;
    }

    public double getCurX() {
        return curX;
    }

    public double getCurY() {
        return curY;
    }

    void move(){

        this.curX = this.prevX + velX;
        this.curY = this.prevY + velY;
        this.prevX = this.curX;
        this.prevY = this.curY;



    }

    public int getORIGINAL_SPEED() {
        return ORIGINAL_SPEED;
    }

    public void setCurX(double curX) {
        this.curX = curX;
    }

    public void setCurY(double curY) {
        this.curY = curY;
    }

    public void setPrevX(double prevX) {
        this.prevX = prevX;
    }

    public void setPrevY(double prevY) {
        this.prevY = prevY;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}
