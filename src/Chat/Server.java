package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;

    Socket clientSocket;

    boolean isConnected = true;

    public Server() {

        try {
            this.serverSocket = new ServerSocket(21);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thread t = new Thread(() -> {
            while(isConnected){
                try {
                    this.clientSocket = serverSocket.accept();

                    System.out.println("Verbindung hergestellt: " + clientSocket.toString());
                    InputStream in = clientSocket.getInputStream();
                    OutputStream out = clientSocket.getOutputStream();

                    int c;
                    while ((c = in.read()) != -1) {
                        System.out.print((char)c);
                    }  out.write((char)c);


                    System.out.println("Verbindung beenden");
                    in.close();
                    out.close();
                    clientSocket.close();
//                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            });

            t.start();



    }

    public static void main(String[] args) {
        new Server();
    }
}
