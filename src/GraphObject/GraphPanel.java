package GraphObject;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

    GraphFrame frame;
    public GraphPanel(GraphFrame frame) {
        this.frame = frame;
        this.setSize(frame.width, frame.height);
        this.setBackground(Color.WHITE);
    }
}
