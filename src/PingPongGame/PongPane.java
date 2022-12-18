package PingPongGame;
import javax.swing.*;
import java.awt.*;

public class PongPane extends JPanel {
    PongFrame frame;
    Entity playerOne;
    Entity playerTwo;
    Ball ball;


    MovementHandler handler;

    Thread repaintFrame = new Thread(() -> {
        while(true) {

            ball.move();
            checkCollision();
            this.repaint();
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public PongPane(PongFrame frame) {
        this.frame = frame;
        setSize(
            frame.getWidth(),
            frame.getHeight()
        );

        setFocusable(true);

        playerOne = new Entity(10, frame.getHeight() / 2 - 70);
        playerTwo = new Entity(frame.getWidth() - 45, frame.getHeight() / 2 - 70);
        ball      = new Ball(( frame.getWidth() / 2 - 10), (frame.getHeight() / 2 - 10), this);

        handler = new MovementHandler(ball, this, playerOne, playerTwo);


        this.addKeyListener(handler);



        setVisible(true);
        repaintFrame.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);


        playerOne.draw(g);
        playerTwo.draw(g);
        ball.draw(g);


        g.setFont(new Font("Comic Sans MS",Font.PLAIN, 30));

        g.drawString(
            playerOne.getScore() + " / " + playerTwo.getScore(),
            frame.getWidth() / 2 - 35,
            30
        );
    }

    private void checkCollision() {



        /* Deflect balls on the players */
        if(
                //if is on target x position
                ball.getCurX() <= (playerOne.getX() + playerOne.getWidth()) &&

                //if is on target y positon
                (
                    ball.getCurY() > playerOne.getY() &&
                    ball.getCurY() < (playerOne.getY() + playerOne.getHeight())
                )
        ){
            //deflect
            ball.velX *= -1;
        }

        if(
                //if is on target x postion
                (ball.getCurX() + ball.getRadius()) >= playerTwo.getX() &&

                //if is on target y postion
                (
                    ball.getCurY() > playerTwo.getY() &&
                    ball.getCurY() < (playerTwo.getY() + playerTwo.getHeight())
                )
        ){
            //deflect
            ball.velX *= -1;
        }

        //check if ball hits top or bottom, the switch direction
        if (ball.curY + ball.radius >= this.getHeight() || ball.curY <= 0){
            ball.velY *= -1;
        }

        //if ball hits left hand side wall
        if(ball.curX <= 0){
            playerTwo.setScore(
                    playerTwo.getScore() + 1
            );

            ball.setCurX(( frame.getWidth() / 2 - 10));
            ball.setCurY(( frame.getHeight() / 2 - 10));
            ball.setPrevX(( frame.getWidth() / 2 - 10));
            ball.setPrevY(( frame.getHeight() / 2 - 10));
            ball.setVelX(Math.random() > 0.5 ? ball.getORIGINAL_SPEED() : -ball.getORIGINAL_SPEED());
            ball.setVelY(Math.random() > 0.5 ? ball.getORIGINAL_SPEED() : -ball.getORIGINAL_SPEED());
        }

        //if ball hits right hand side wall
        if (ball.curX + ball.radius >= this.getWidth()){

            playerOne.setScore(
                    playerOne.getScore() + 1
            );

            ball.setCurX(( frame.getWidth() / 2 - 10));
            ball.setCurY(( frame.getHeight() / 2 - 10));
            ball.setPrevX(( frame.getWidth() / 2 - 10));
            ball.setPrevY(( frame.getHeight() / 2 - 10));
            ball.setVelX(Math.random() > 0.5 ? ball.getORIGINAL_SPEED() : -ball.getORIGINAL_SPEED());
            ball.setVelY(Math.random() > 0.5 ? ball.getORIGINAL_SPEED() : -ball.getORIGINAL_SPEED());
        }



    }
}
