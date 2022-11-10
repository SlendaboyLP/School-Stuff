package SwingFilter;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

public class Main  {
    public static void main(String[] args) {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
//        try {
//            ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
    }


}
