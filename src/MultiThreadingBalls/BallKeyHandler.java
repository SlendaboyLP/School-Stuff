package MultiThreadingBalls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class BallKeyHandler implements KeyListener {

    BallPanel panel;

    public BallKeyHandler(BallPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (this.panel.ball.velX < 0) {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
                this.panel.ball.velX *= -1;
            }
        }

        if(this.panel.ball.velX > 0){
            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
                this.panel.ball.velX *= -1;
            }
        }

        if(this.panel.ball.velY < 0){
            if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
                this.panel.ball.velY *= -1;
            }
        }

        if (this.panel.ball.velY > 0){
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
                this.panel.ball.velY *= -1;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_PLUS){

            this.panel.ball.applyForce(1.05);
        }
        if(e.getKeyCode() == KeyEvent.VK_MINUS){

            this.panel.ball.applyForce(0.95);
        }


        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.panel.ball.curX = this.panel.ball.prevX = this.panel.getWidth()/2 - this.panel.ball.width/2;
            this.panel.ball.curY = this.panel.ball.prevY = this.panel.getHeight()/2 - this.panel.ball.height/2;
            this.panel.ball.velX = 1;
            this.panel.ball.velY = 1;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
