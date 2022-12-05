package PingPingGame;

import java.awt.event.*;

class MovementHandler implements KeyListener, MouseWheelListener, MouseMotionListener {
    Entity[] entities;
    PongPane panel;
    Ball ball;

    MovementHandler(Ball ball, PongPane panel, Entity... entities) {
        this.ball = ball;
        this.panel = panel;

        this.entities = entities;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("pressed");
        if(e.getKeyCode() == KeyEvent.VK_W){
            entities[0].setY(
                entities[0].getY() - entities[0].getSpeed()
            );
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            entities[0].setY(
                entities[0].getY() + entities[0].getSpeed()
            );
        }

        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        System.out.println("mouse wheel moved");

        if(e.getWheelRotation() == 1){
            entities[1].setY(
                entities[1].getY() - entities[1].getSpeed() / 2
            );

        } else {
            entities[1].setY(
                entities[1].getY() + entities[1].getSpeed() / 2
            );
        }
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouse moved");
    }
}
