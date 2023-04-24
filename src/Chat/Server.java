package Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;

    Socket clientSocket;

    boolean isConnected = true;

    ServerPane pane;
    Thread t = new Thread(() -> {
        while(isConnected){
            try {

                InputStream in = clientSocket.getInputStream();

                int c;

                this.pane.log.append("Client: ");
                while ((c = in.read()) != -1) {
                    this.pane.log.append(""+(char)c);
                } this.pane.log.append(""+(char)c);


                in.close();
                clientSocket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    });


    public Server(int port, ServerPane pane) {

        this.pane = pane;
        try {

            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thread t1 = new Thread(() -> {
            try {
                clientSocket = serverSocket.accept();
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                int c;

                while ((c = in.read()) != -1) {
                    this.pane.log.append(""+(char)c);
//                    System.out.print((char)c);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        t1.start();






    }

    public void writeToClient(String text){
        try {
            OutputStream out = clientSocket.getOutputStream();
            PrintWriter pr = new PrintWriter(out, true);
            pr.println("Server: " + text);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeServer(){

        try {
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
