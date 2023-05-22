package FileTransfer;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

 class Client {
    Socket socket;

    Client(String ip, int port){
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendFile(){
        JFileChooser chooser = new JFileChooser();
        int accepted = chooser.showDialog(null, "choose");
        if(accepted == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            sendData(file);
        }
    }

    public void sendData(File file){
        try {
            OutputStream os = socket.getOutputStream();
            String fileName = file.getName()+"\n";
            String fileData = "";
            FileInputStream fis = new FileInputStream(file);
            int bytes_read;
            while((bytes_read = fis.read()) != -1){
                fileData += (char) bytes_read;
            }

            if(fileData.charAt(fileData.length()-1) != '\n'){
                fileData+="\n";
            }

            os.write(fileName.getBytes());
            os.write(fileData.getBytes());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            os.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {

    }
}