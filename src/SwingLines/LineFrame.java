package SwingLines;

import javax.swing.*;

class LineFrame extends JFrame {
    LinePanel panel;
    LineFrame(){
        super("SwingLines");
        this.panel = new LinePanel(this);
        this.setSize(1280,720);
        this.setLocationRelativeTo(null);
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
}
