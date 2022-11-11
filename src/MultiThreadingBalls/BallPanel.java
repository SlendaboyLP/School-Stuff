package MultiThreadingBalls;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class BallPanel extends JPanel {
    BallFrame frame;
    JFileChooser fileChooser;
    JButton fileChooserBtn;
    Ball ball;
    BallKeyHandler handler;

    Thread calculatePos = new Thread(() -> {
        while (true){
            ball.move();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
            this.requestFocus();
        }
    });

    BallPanel(BallFrame frame)  {

        //sets the default look and feel depending on operating system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.handler = new BallKeyHandler(this);
        this.addKeyListener(this.handler);


        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.frame = frame;

        addFileChooser();
        setDefaultBall();

        repaint();

        calculatePos.start();

        this.requestFocus();
    }

    // default Image for Ball
    private void setDefaultBall(){
        try {
            BufferedImage img = ImageIO.read(new File("./src/MultiThreadingBalls/ball.png"));

            this.ball = new Ball(
                img,
                img.getWidth() / 8,
                img.getHeight() / 8,
                    this.frame.getWidth() / 2 - img.getWidth() / 16,
                    this.frame.getHeight() / 2 - img.getHeight() / 16,
                    this
            );


            this.ball.curX = this.ball.prevX = this.frame.getWidth()/2 -  this.ball.width/2;
            this.ball.curY = this.ball.prevY = this.frame.getHeight()/2 - this.ball.height/2;
            this.ball.velX = 1;
            this.ball.velY = 1;
            this.ball.applyForce(1);


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    //allows User to use image from their computer as the image for the ball
    private void addFileChooser(){
        this.fileChooser = new JFileChooser("\\\\sz-ybbs.ac.at\\shares\\homes\\a.zeitlhofer\\Pictures\\InfoTag");

        this.fileChooser.setFileFilter(
            new FileNameExtensionFilter("JPG & PNG Images", "jpg", "gif", "jpeg", "png")
        );
        this.fileChooserBtn = new JButton("Choose Image for Ball (1x1)");
        this.fileChooserBtn.addActionListener(e -> {
            if (this.fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                try {
                    this.ball.setImg(
                        ImageIO.read(new File(this.fileChooser.getSelectedFile().toURI()))
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                this.repaint();
                this.requestFocus();
            }
        });
        this.add(fileChooserBtn);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ball != null){
            ball.draw(g);
        }

    }
}
