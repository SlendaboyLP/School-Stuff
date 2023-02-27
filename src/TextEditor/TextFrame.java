package TextEditor;

import javax.swing.*;

public class TextFrame extends JFrame {
    public TextFrame() {
        setTitle("Text Editor");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TextPanel panel = new TextPanel(this);
        this.setContentPane(panel);
        TextMenu menu = new TextMenu(panel);
        this.setJMenuBar(menu);

        setVisible(true);
    }

    public static void main(String[] args) {
        TextFrame frame = new TextFrame();
    }
}
