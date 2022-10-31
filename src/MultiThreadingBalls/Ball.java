package MultiThreadingBalls;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Ball {
    BufferedImage img;
    int width, height;
    double curX, curY;
    double prevX, prevY;
    double force = 1.05;
    double velX = 1.05, velY = 1.0;
    //positive = rigth, negative = left
    int direction = 1;



    public Ball(BufferedImage img, int width, int height, int curX, int curY) {
        this.img = img;
        this.width = width;
        this.height = height;
        this.curX = curX;
        this.curY = curY;
        this.prevX = curX;
        this.prevY = curY;
    }

    public void move(){
        this.curX = this.prevX + velX;
        this.curY = this.prevY + velY;
        this.prevX = this.curX;
        this.prevY = this.curY;
    }

//    public void calcVel(int direction){
//
//        this.velX = velY * force;
//        this.velY = velY * force;
//
//    }

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
