package Clock;

import javax.swing.*;

public class ControlFrame extends JFrame {
    ClockPane pane;
    public ControlFrame(ClockPane pane){
        super("Control");
        this.pane = pane;
        this.setSize(300,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1400,300);
        this.setContentPane(new ControlPane(pane));
        this.setVisible(true);
    }
}
