package Chat;

public class Main {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> new ClientFrame());
        Thread t2 = new Thread(() -> new ServerFrame());

        t2.start();
        t1.start();

    }
}
