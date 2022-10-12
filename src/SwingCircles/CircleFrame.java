package SwingCircles;

import javax.swing.*;

public class CircleFrame extends JFrame {

    CirclePanel c1;

    CircleFrame(){
        super("Swing Circle");
        c1 = new CirclePanel();
        this.setSize(1280,720);
        this.setLocationRelativeTo(null);

        this.setContentPane(c1);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
