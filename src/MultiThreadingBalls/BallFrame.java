package MultiThreadingBalls;

import javax.swing.*;

class BallFrame extends JFrame {
    BallPanel panel;
    BallFrame(){
        this.panel = new BallPanel(this);
        this.setSize(1280,720);
        this.setContentPane(this.panel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
