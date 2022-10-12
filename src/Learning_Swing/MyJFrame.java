package Learning_Swing;

import javax.swing.JFrame;

public class MyJFrame extends JFrame{
    public MyJFrame() {

        this.setSize(400, 200);
        this.setLocation(800, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new MyJPanel(this));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        new MyJFrame();

    }

}