package MultiThreadingBalls;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Ball {
    BufferedImage img;
    int width, height;
    double curX, curY;
    double prevX, prevY;

    double velX = 1, velY = 1;
    BallPanel panel;




    public Ball(BufferedImage img, int width, int height, int curX, int curY, BallPanel panel) {
        this.img = img;
        this.width = width;
        this.height = height;
        this.curX = curX;
        this.curY = curY;
        this.prevX = curX;
        this.prevY = curY;
        this.panel = panel;
    }

    public void move(){
        this.curX = this.prevX + velX;
        this.curY = this.prevY + velY;
        this.prevX = this.curX;
        this.prevY = this.curY;

        checkBounds();
    }

    public void applyForce(double force) {
        this.velX *= force;
        this.velY *= force;

    }

    private boolean checkBounds(){
        if (this.curX + this.width >= this.panel.getWidth() || this.curX <= 0){
            this.velX *= -1;
            return true;
        }
        if (this.curY + this.height >= this.panel.getHeight() || this.curY <= 0){
            this.velY *= -1;
            return true;
        }
        return false;
    }


    public void setImg(BufferedImage img) {
        this.img = img;
        if(img == null) return;
        this.width = this.img.getWidth();
        this.height = this.img.getHeight();

        while (this.width > 200) {
            this.width /= 2;
            this.height /= 2;
        }
    }

    public void draw(Graphics g){
        g.drawImage(
            this.img,
            (int) this.curX,
            (int) this.curY,
            this.width,
            this.height,
            null
        );
    }

    @Override
    public String toString() {
        return "Ball{" +
            "img=" + img + "\n" +
            ", width=" + width + "\n" +
            ", height=" + height + "\n" +
            ", curX=" + curX + "\n" +
            ", curY=" + curY + "\n" +
            '}';
    }
}
