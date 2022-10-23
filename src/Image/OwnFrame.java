package Image;

import javax.swing.*;

class OwnFrame extends JFrame {
    OwnPane panel;
    OwnFrame(){
        this.setSize(800,800);
        this.panel = new OwnPane(this);
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.panel.requestFocus();

    }
}
