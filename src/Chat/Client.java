package Chat;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    Boolean isConnected = true;
    ClientPane pane;

    public Client(String path, int port, ClientPane pane) {

        this.pane = pane;

        try {
            this.socket = new Socket(path, port);
            this.writer = new PrintWriter(socket.getOutputStream(),true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Thread t = new Thread(() -> {
            while(isConnected){
                try {

                    int c;
                    while ((c = reader.read()) != -1) {
                        this.pane.log.append(""+(char)c);
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t.start();

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
        this.writer.println("Client: " + text);
    }


}
