package FileTransfer;

import javax.swing.*;

class ClientFrame extends JFrame {
    ClientFrame(){
        super("Client");
        this.setContentPane(new ClientPane(this));
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ClientFrame();
    }
}

class ClientPane extends JPanel{

    ClientFrame frame;
    JButton connect;

    Client client;

    JButton sendFile;


    ClientPane(ClientFrame frame){
        this.frame = frame;
        this.setLayout(null);

        connect = new JButton("Connect");
        connect.setBounds(10,10,100,70);
        connect.addActionListener(e -> {
            client = new Client("localhost", 21);
        });
        this.add(connect);


        sendFile = new JButton("Choose File");
        sendFile.setBounds(120,10,100,70);
        sendFile.addActionListener(e -> {
            client.sendFile();
        });
        this.add(sendFile);

    }
}


