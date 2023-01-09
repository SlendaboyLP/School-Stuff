package PingPongGame;

import java.awt.event.*;

class MovementHandler implements KeyListener, MouseWheelListener {
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

        if(!panel.isPlayable){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                ball.setCurX(( panel.getWidth() / 2.0 - 10));
                ball.setCurY(( panel.getHeight() / 2.0 - 10));
                ball.setPrevX(( panel.getWidth() / 2.0 - 10));
                ball.setPrevY(( panel.getHeight() / 2.0 - 10));


                ball.setVelX(Math.random() > 0.5 ? ball.getORIGINAL_SPEED() : -ball.getORIGINAL_SPEED());
                ball.setVelY(Math.random() > 0.5 ? ball.getORIGINAL_SPEED() : -ball.getORIGINAL_SPEED());

                panel.playerOne.setScore(0);
                panel.playerTwo.setScore(0);

                panel.playerOne.setY(panel.getHeight() / 2 - 70);
                panel.playerTwo.setY(panel.getHeight() / 2 - 70);
                panel.isPlayable = true;
                panel.repaint();

            }
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


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() < 0){
            entities[1].setY(
                    entities[1].getY() - entities[1].getSpeed() / 3
            );

        } else {
            entities[1].setY(
                    entities[1].getY() + entities[1].getSpeed() / 3
            );
        }

        checkBounds();

        panel.repaint();

    }
}
