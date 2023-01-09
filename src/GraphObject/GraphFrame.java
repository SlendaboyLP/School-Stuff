package GraphObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GraphFrame extends JFrame implements MouseMotionListener {
    final int width = 1280, height = 720;

    GraphObject object;

    public GraphFrame(GraphObject object){
        super("Graphs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        setLocationRelativeTo(null);
        this.setContentPane(new GraphPanel(this));
        this.setVisible(true);
        this.object = object;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
