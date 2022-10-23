package SwingCircles;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class CircleMouseListener implements MouseListener, MouseMotionListener {

    private int oriX, oriY;
    private int curX, curY;
    private double rad;
    private boolean isDragging;
    private final CirclePanel panel;
    CircleMouseListener(CirclePanel panel){
        this.panel = panel;
    }

    public boolean isDragging() {
        return isDragging;
    }
    public double getRad() {
        return rad;
    }

    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    public int getOriX() {
        return oriX;
    }

    public int getOriY() {
        return oriY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.oriX = e.getX();
        this.oriY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int dx = Math.abs(this.oriX - e.getX());
        int dy = Math.abs(this.oriY - e.getY());
        this.rad = Math.sqrt((dx * dx) + (dy * dy));

        this.isDragging = false;
        panel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.curX = e.getX();
        this.curY = e.getY();

        this.isDragging = true;
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
