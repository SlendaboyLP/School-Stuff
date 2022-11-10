package SwingFilter;

import javax.swing.*;

class FilterFrame extends JFrame {
    FilterFrame(){
        setSize(1600,900);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(new FilterPane(this));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FilterFrame();

    }
}
