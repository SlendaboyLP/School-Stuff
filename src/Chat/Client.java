package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    public Client(String path, int port) {

        try {
            this.socket = new Socket(path, port);
            this.writer = new PrintWriter(socket.getOutputStream(),true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeClient(){
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToServer(String text){
        System.out.println(text);
        this.writer.println(text);
    }


}
