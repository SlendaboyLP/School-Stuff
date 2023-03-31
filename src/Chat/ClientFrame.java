package Chat;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class ClientPane extends JPanel {
    int width;
    int height;
    ClientFrame frame;

    Client client;

    JButton connect;
    JButton disconnect;
    JTextField ipInput;

    public ClientPane(ClientFrame frame) {
        this.frame = frame;
        this.width = frame.width;
        this.height = frame.height;
        this.setSize(width,height);
        this.setLayout(null);



        JTextField field = new JTextField();
        field.setBounds(10,10,100,30);
        this.add(field);

        JButton btn = new JButton("send");
        btn.setBounds(10,50,70,30);
        btn.addActionListener(e -> client.writeToServer(field.getText()));
        this.add(btn);



        this.ipInput = new JTextField();
        ipInput.setBounds(this.getWidth()-120,10,100,50);
        this.add(ipInput);

        this.connect = new JButton("Connect");
        connect.setBounds(this.getWidth()- 120, 70,100,50);
        connect.addActionListener(e -> {
            String pathAndIp = ipInput.getText();
            if(pathAndIp.equals("")) return;

            String path = pathAndIp.split(":")[0];
            String ip = pathAndIp.split(":")[1];

            this.client = new Client(path, Integer.parseInt(ip));
            this.frame.setTitle("Connected to: " + pathAndIp);
        });
        this.add(connect);

        this.disconnect = new JButton("Disconnect");
        disconnect.setBounds(this.getWidth()-120, 130,100,50);
        disconnect.addActionListener(e -> {
            this.client.closeClient();
            this.frame.setTitle("Client");
        });
        this.add(disconnect);

    }
}

class ClientFrame extends JFrame{
    int width=800;
    int height=600;

    ClientPane pane;

    ClientFrame(){
        super("Client");
        this.pane = new ClientPane(this);
        this.setContentPane(pane);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                pane.client.closeClient();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
    public static void main(String[] args) {
        new ClientFrame();
    }
}