package FileTransfer;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

class Server {
    ServerSocket serverSocket;
    Socket socket;
    String directory;




    Server(int ip){
        try {
            serverSocket = new ServerSocket(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Thread t1 = new Thread(() -> {
        try {
            socket = serverSocket.accept();
            System.out.println("connection established");

            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int c;

            ArrayList<String> lines = new ArrayList<>();
            String line = "";


            while ((line = br.readLine()) != null) {
                lines.add(line+"\n");
            }

            lines.set(0, lines.get(0).trim());
            System.out.println(directory + "\\" + lines.get(0));
            FileOutputStream fos = new FileOutputStream(directory + "\\" + lines.get(0));
            System.out.println();
            for (int i = 1; i<lines.size(); i++){
                fos.write(lines.get(i).getBytes());
            }

            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    public void chooseDir(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            directory = String.valueOf(chooser.getSelectedFile());
        }
    }




}
