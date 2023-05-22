package Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.GregorianCalendar;

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
        setResizable(false);
    }


    public static void main(String[] args) {
        new ServerFrame();
    }

}


class ServerPane extends JPanel{
    ServerFrame frame;
    JTextField portInput;
    JButton startServer;
    JButton saveLog;

    Server server;
    JTextArea log;
    public ServerPane(ServerFrame frame) {
        this.frame = frame;
        this.setSize(frame.width,frame.height);
        this.setLayout(null);
        this.setVisible(true);


        JTextField field = new JTextField();
        field.setBounds(10,10,250,30);
        this.add(field);

        JButton btn = new JButton("send");
        btn.setBounds(270,10,70,30);
        btn.addActionListener(e -> {
            server.writeToClient(field.getText());
            log.append("Server: " + field.getText() + "\n");
            field.setText("");

        });
        this.add(btn);

        log = new JTextArea();
        log.setBounds(10,50,this.getWidth()-140, this.getHeight()-100);
        log.setEditable(false);
        this.add(log);


        portInput = new JTextField();
        portInput.setBounds(this.getWidth()-120,10,100,50);
        portInput.setFont(new Font("Arial", Font.PLAIN, 12));
        portInput.setText("PORT");
        portInput.setForeground(new Color(100,100,100));
        portInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                portInput.setText("");
                portInput.setForeground(new Color(0,0,0));

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(!portInput.getText().equals("")) return;

                portInput.setText("PORT");
                portInput.setForeground(new Color(100,100,100));

            }
        });
        this.add(portInput);

        startServer = new JButton("Start");
        startServer.setBounds(this.getWidth()-120,70,100,50);
        startServer.addActionListener(e -> {
            server = new Server(Integer.parseInt(portInput.getText()), this);
            frame.setTitle("Server - running on port: "+ portInput.getText());
        });
        this.add(startServer);

        saveLog = new JButton("Save");
        saveLog.setBounds(this.getWidth()-120,130,100,50);
        saveLog.addActionListener(e -> {
            saveToFile();
        });
        this.add(saveLog);

        this.setFocusable(true);
        this.requestFocus();


    }

    private void saveToFile(){
        GregorianCalendar cal = new GregorianCalendar();
        JFileChooser chooser = new JFileChooser("C:\\Users\\alexz\\IdeaProjects\\School-Stuff\\src\\Chat\\logs");

        FileWriter fw;
        try {

            chooser.showOpenDialog(null);

            fw = new FileWriter(chooser.getSelectedFile());

            fw.write(log.getText());

            fw.close();
        }catch (IOException ie){}
    }
}