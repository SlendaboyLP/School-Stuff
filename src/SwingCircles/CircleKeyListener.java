package SwingCircles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class CircleKeyListener implements KeyListener {

    private final CirclePanel panel;
    private boolean escape;

    public boolean isEscape() {
        return escape;
    }

    public CircleKeyListener(CirclePanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        escape = e.getKeyCode() == 27;
        panel.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        escape = false;
    }
}
