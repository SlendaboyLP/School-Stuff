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

            int width, height;
            height = img.getHeight();
            width = img.getWidth();
            int[] rgbArr = new int[width * height];
            rgbArr = img.getRGB(
                0,
                0,
                width,
                height,
                rgbArr,
                0,
                width
            );

            for (int i = 0; i < rgbArr.length; i++) {
                int pixel = rgbArr[i];

                int alpha = (pixel >> 24) & 0x00_00_00_FF;
                int red = (pixel >> 16) & 0x00_00_00_FF;
                int green = (pixel >> 8) & 0x00_00_00_FF;
                int blue = (pixel >> 0) & 0x00_00_00_FF;

                red = 0xFF - red;
                green = 0xFF - green;
                blue = 0xFF - blue;

                pixel = alpha;
                pixel = pixel << 8;
                pixel = pixel | red;

                pixel = pixel << 8;
                pixel = pixel | green;

                pixel = pixel << 8;
                pixel = pixel | blue;

                rgbArr[i] = pixel;

            }

            img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            img.setRGB(0,0, width, height, rgbArr, 0, width);
            this.repaint();


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
