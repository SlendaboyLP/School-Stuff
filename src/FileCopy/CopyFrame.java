package FileCopy;

import javax.swing.*;

public class CopyFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 400;
    public CopyFrame() {
        setTitle("File Copy");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        this.setLocationRelativeTo(null);
        CopyPanel panel = new CopyPanel(this);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new CopyFrame();
    }



}
