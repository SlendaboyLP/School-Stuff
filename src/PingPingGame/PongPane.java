package PingPingGame;
import javax.swing.*;
import java.awt.*;

public class PongPane extends JPanel {
    PongFrame frame;
    Entity playerOne;
    Entity playerTwo;
    Ball ball;

    MovementHandler handler;

    public PongPane(PongFrame frame) {
        this.frame = frame;
        setSize(
            frame.getWidth(),
            frame.getHeight()
        );

        setFocusable(true);

        playerOne = new Entity(20, frame.getHeight() / 2 - 35);
        playerTwo = new Entity(frame.getWidth() - 40, frame.getHeight() / 2 - 35);
        ball      = new Ball(( frame.getWidth() / 2 - 5), (frame.getHeight() / 2 - 5));

        handler = new MovementHandler(ball, this, playerOne, playerTwo);

        this.addKeyListener(handler);
        this.addMouseWheelListener(handler);
        this.addMouseMotionListener(handler);

        setVisible(true);
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
}
