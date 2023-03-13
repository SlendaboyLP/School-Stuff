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
    BufferedReader stdIn;

    public Client() {

        try {
            this.socket = new Socket("localhost", 21);
            this.writer = new PrintWriter(socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput = "Hello World";

            writer.println(userInput);

            writer.close();
            reader.close();
            stdIn.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
