package PingPongGame;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class PongPane extends JPanel {
    PongFrame frame;
    Entity playerOne;
    Entity playerTwo;
    Ball ball;

    boolean isPlayable = true;
    String winner = "";

    Clip clip;

    final int SCORE_TO_WIN = 3;

    MovementHandler handler;

    Thread repaintFrame = new Thread(() -> {
        while (true){
            System.out.print("");
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
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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
        ball      = new Ball(( frame.getWidth() / 2 - 10), (frame.getHeight() / 2 - 10), this);

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

        isPlayable = !(playerOne.getScore() == SCORE_TO_WIN || playerTwo.getScore() == SCORE_TO_WIN);

        g.setFont(new Font("Comic Sans MS",Font.PLAIN, 30));

        if(isPlayable){
            playerOne.draw(g);
            playerTwo.draw(g);
            ball.draw(g);

            g.drawString(
                    playerOne.getScore() + " / " + playerTwo.getScore(),
                    frame.getWidth() / 2 - 70,
                    30
            );

        } else {

            clip.stop();
            if(playerOne.getScore() == SCORE_TO_WIN){
                winner = "Player one";

                String soundName = "./src/PingPongGame/sif.wav";
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
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
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                FloatControl volumeGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeGainControl.setValue(-7.0f);
                clip.start();


            } else {
                winner = "Player two";

                String soundName = "./src/PingPongGame/subway_surfer.wav";
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
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
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                FloatControl volumeGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeGainControl.setValue(-5.0f);

                clip.start();
            }

            g.drawString(
                    winner + " won!",
                    frame.getWidth() / 2 - 100,
                    frame.getHeight() / 2 - 15
            );

            g.drawString(
                    "Press Space to restart",
                    frame.getWidth() / 2 - 100,
                    frame.getHeight() / 2 + 15
            );

        }



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

            //make the ball a little fast
            ball.velX *= 1.1;
            ball.velY *= 1.1;

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
            ball.velX *= 1.1;
            ball.velY *= 1.1;

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


    private void playAudio (String soundName){


        Thread t1 = new Thread(() -> {
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            try {
                clip.open(audioInputStream);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FloatControl volumeGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeGainControl.setValue(-5.0f);

            clip.start();

        });

        t1.start();

    }
}
