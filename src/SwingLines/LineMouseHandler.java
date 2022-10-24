package SwingLines;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class LineMouseHandler implements MouseListener, MouseMotionListener {
    int x,y;
    int prevX, prevY;
    LinePanel panel;
    LineMouseHandler(LinePanel panel){
        this.panel = panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.prevX = x;
        this.prevY = y;

        this.x = e.getX();
        this.y = e.getY();
        panel.repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        this.prevX = e.getX();
//        this.prevY = e.getX();
        this.x = e.getX();
        this.y = e.getY();
    }
}
