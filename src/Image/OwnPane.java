package Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

class OwnPane extends JPanel implements KeyListener {
    OwnFrame frame;
    BufferedImage img;

    OwnPane(OwnFrame frame){
        this.frame = frame;
        this.setSize(800,800);

        this.setLayout(null);
        this.addKeyListener(this);
        this.setBackground(Color.white);
        this.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            this.img = ImageIO.read(new File("./src/Image/borgir.jpeg"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.repaint();
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        if(img != null){
            g.drawImage(
                img,
                0,
                0,
                img.getWidth() / 4,
                img.getHeight() / 4,
                null
            );

            Color c = new Color(img.getRGB(0,0));
            print(
                "" + c.getRed(),
                "" + c.getGreen(),
                "" + c.getBlue(),
                "" + c.getAlpha()
            );
        }

    }

    public void print (String... args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    public void print(int... args){
        for (int arg : args) {
            System.out.println(arg);
        }
    }
}
