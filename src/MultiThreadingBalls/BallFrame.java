package MultiThreadingBalls;

import javax.swing.*;

class BallFrame extends JFrame {
    BallPanel panel;
    BallFrame(){

        this.setSize(1280,720);
        this.panel = new BallPanel(this);
        this.setContentPane(this.panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        this.setVisible(true);


    }
}
