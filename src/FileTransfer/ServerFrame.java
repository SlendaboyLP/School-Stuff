package FileTransfer;

import javax.swing.*;

class ServerFrame extends JFrame {
    ServerFrame(){
        super("Server");
        this.setContentPane(new ServerPane(this));
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ServerFrame();
    }
}

class ServerPane extends JPanel {
    ServerFrame frame;
    JButton connect;
    Server server;
    JButton chooseDirectory;

    ServerPane(ServerFrame frame){
        this.frame = frame;
        this.setLayout(null);

        connect = new JButton("Start");
        connect.setBounds(10,10,100,70);
        connect.addActionListener(e -> {
            server = new Server(21);
            server.t1.start();
        });
        this.add(connect);

        chooseDirectory = new JButton("Choose Dir");
        chooseDirectory.setBounds(120,10,100,70);
        chooseDirectory.addActionListener(e -> {
            server.chooseDir();
        });

        this.add(chooseDirectory);


    }
}

