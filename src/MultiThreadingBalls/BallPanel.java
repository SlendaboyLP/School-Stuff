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

    Thread calculatePos = new Thread(() -> {
        while (true){
            ball.move();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    });
    BallPanel(BallFrame frame)  {

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.frame = frame;

        addFileChooser();
        setDefaultBall();

        calculatePos.start();
    }

    // default Image for Ball
    private void setDefaultBall(){
        try {
            BufferedImage img = ImageIO.read(new File("./src/MultiThreadingBalls/ball.png"));
            this.ball = new Ball(
                img,
                img.getWidth() / 8,
                img.getHeight() / 8,
                100,
                100
            );
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    //allows User to use image from their computer as the image for the ball
    private void addFileChooser(){
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(
            new FileNameExtensionFilter("JPG & PNG Images", "jpg", "gif", "jpeg")
        );
        this.fileChooserBtn = new JButton("Choose Image for Ball (1x1)");
        this.fileChooserBtn.addActionListener(e -> {
            if (this.fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                try {
                    this.ball.setImg(
                        ImageIO.read(new File(this.fileChooser.getSelectedFile().toURI()))
                    );
                    System.out.println(this.ball);
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
