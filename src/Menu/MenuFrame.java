package Menu;

import javax.swing.*;

public class MenuFrame extends JFrame {
    public static void main(String[] args) {
        new MenuFrame();
    }
    MenuFrame() {
        setTitle("Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new OwnJMenuBar());
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

}
