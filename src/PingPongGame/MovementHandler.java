package PingPongGame;

import java.awt.event.*;

class MovementHandler implements KeyListener {
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

        if(e.getKeyCode() == KeyEvent.VK_UP){
            entities[1].setY(
                    entities[1].getY() - entities[1].getSpeed()
            );

        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            entities[1].setY(
                    entities[1].getY() + entities[1].getSpeed()
            );
        }


        checkBounds();

        panel.repaint();
    }

    private void checkBounds() {
        for (Entity entity : entities) {
            if(entity.getY() <= 10){
                entity.setY(10);
            }

            if(entity.getY() + entity.height + 10>= panel.getHeight()){
                entity.setY(panel.getHeight() - entity.height - 10);
            }
        }




    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
