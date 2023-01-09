package PingPongGame;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PongPane extends JPanel {
    PongFrame frame;
    Entity playerOne;
    Entity playerTwo;
    Ball ball;

    boolean isPlayable = true;
    String winner = "";

    Clip clip;

    final int SCORE_TO_WIN = 3;

    final double acceleration = 1.05;

    MovementHandler handler;

    Thread repaintFrame = new Thread(() -> {
        while (true){

            //small time delay, needed for swing to work properly, dont ask
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(isPlayable) {

                ball.move();
                checkCollision();
                this.repaint();
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    });

    Thread backgroundMusic = new Thread(() -> {
        String soundName = "./src/PingPongGame/megalovania.wav";
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }

        FloatControl volumeGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeGainControl.setValue(-7.0f);

        clip.start();

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
        ball      = new Ball(( frame.getWidth() / 2 - 10), (frame.getHeight() / 2 - 10));

        handler = new MovementHandler(ball, this, playerOne, playerTwo);

        this.addKeyListener(handler);
        this.addMouseWheelListener(handler);
        this.setBackground(new Color(0,0,0));
        setVisible(true);
        backgroundMusic.start();
        repaintFrame.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Comic Sans MS",Font.PLAIN, 30));

        isPlayable = !(playerOne.getScore() == SCORE_TO_WIN || playerTwo.getScore() == SCORE_TO_WIN);

        //if the game hasnt ended yet it will redraw paddles and the ball at their correct postion
        if(isPlayable){
            playerOne.draw(g);
            playerTwo.draw(g);
            ball.draw(g);

            g.drawString(
                    playerOne.getScore() + " / " + playerTwo.getScore(),
                    frame.getWidth() / 2 - 50,
                    30
            );
            return;
        }

        //show the win screen if the score of one player got to the winning score
        showScore(g);
    }

    private void showScore(Graphics g) {
        clip.stop();

        String soundName;
        if(playerOne.getScore() == SCORE_TO_WIN){
            winner = "Player one";
            soundName = "./src/PingPongGame/sif.wav";

        } else {
            winner = "Player two";
            soundName = "./src/PingPongGame/subway_surfer.wav";
        }

        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }

        FloatControl volumeGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeGainControl.setValue(-5.0f);

        g.drawString(
                winner + " won!",
                frame.getWidth() / 2 - 150,
                frame.getHeight() / 2 - 30
        );

        g.drawString(
                "Press Space to restart",
                frame.getWidth() / 2 - 150,
                frame.getHeight() / 2
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

            //make the ball a little faster
            ball.velX *= acceleration;
            ball.velY *= acceleration;

            playAudio("./src/PingPongGame/clang.wav");
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

            //make the ball a little fast
            ball.velX *= acceleration;
            ball.velY *= acceleration;

            playAudio("./src/PingPongGame/clang.wav");

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
            resetBall();

        }
        //if ball hits right hand side wall
        if (ball.curX + ball.radius >= this.getWidth()){

            playerOne.setScore(
                    playerOne.getScore() + 1
            );
            resetBall();
        }

    }

    private void resetBall(){
        ball.setCurX((  frame.getWidth () / 2.0 - 10));
        ball.setCurY((  frame.getHeight() / 2.0 - 10));
        ball.setPrevX(( frame.getWidth () / 2.0 - 10));
        ball.setPrevY(( frame.getHeight() / 2.0 - 10));


        ball.setVelX(Math.random() > 0.5 ? ball.getORIGINAL_SPEED() : -ball.getORIGINAL_SPEED());
        ball.setVelY(Math.random() > 0.5 ? ball.getORIGINAL_SPEED() : -ball.getORIGINAL_SPEED());
    }


    private void playAudio (String soundName){

        Thread t1 = new Thread(() -> {
            AudioInputStream audioInputStream;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
            Clip clip;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            try {
                clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }

            FloatControl volumeGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeGainControl.setValue(-5.0f);

            clip.start();

        });

        t1.start();

    }
}
