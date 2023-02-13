package Clock;

import javax.swing.*;

public class ClockFrame extends JFrame {
    public static void main(String[] args) {
        ClockFrame clockFrame = new ClockFrame();
    }

    ClockFrame(){
        this.setSize(700,240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Clock");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(new ClockPane(this));
        this.setVisible(true);
    }
}
