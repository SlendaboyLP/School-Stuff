package Chat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;

    Socket clientSocket;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(21);
            this.clientSocket = serverSocket.accept();

            System.out.println("Verbindung hergestellt: " + clientSocket.toString());
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            int c;
            while ((c = in.read()) != -1) {
                out.write((char)c);
                System.out.println((char)c);
            }
            System.out.println("Verbindung beenden");
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
