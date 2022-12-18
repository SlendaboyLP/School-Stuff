package PingPongGame;

import javax.swing.*;

class PongFrame extends JFrame {
    PongFrame(){
        super(Math.random() > 0.5 ? "Ping Pong" : "Pong Ping");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setContentPane(new PongPane(this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new PongFrame();
    }

}
