package Chat;

import javax.swing.*;

public class ServerFrame extends JFrame {
    int width=800;
    int height=600;
    ServerFrame(){
        super("Server");
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new ServerPane(this));
        this.setVisible(true);
    }


    public static void main(String[] args) {
        new ServerFrame();
    }

}


class ServerPane extends JPanel{
    ServerFrame frame;
    JTextField portInput;
    JButton startServer;
    JButton stopServer;

    Server server;
    public ServerPane(ServerFrame frame) {
        this.frame = frame;
        this.setSize(frame.width,frame.height);
        this.setLayout(null);
        this.setVisible(true);

        portInput = new JTextField();
        portInput.setBounds(this.getWidth()-120,10,100,50);
        this.add(portInput);

        startServer = new JButton("Start");
        startServer.setBounds(this.getWidth()-120,70,100,50);
        startServer.addActionListener(e -> {
            server = new Server(Integer.parseInt(portInput.getText()));
            frame.setTitle("running on port: "+ portInput.getText());
        });
        this.add(startServer);

        stopServer = new JButton("Stop");
        stopServer.setBounds(this.getWidth()-120,130,100,50);
        stopServer.addActionListener(e -> {
            server.closeServer();
            frame.setTitle("Server");
        });
        this.add(stopServer);


    }
}