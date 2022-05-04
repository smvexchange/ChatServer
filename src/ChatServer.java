import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer implements Runnable {
    ServerSocket server;
    ArrayList<Client> clients = new ArrayList<>();

    public ChatServer() throws IOException {
        server = new ServerSocket(1234);
    }

    void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Waiting...");
            try {
                Socket socket = server.accept();
                clients.add(new Client(socket, this));
                System.out.println("Client connected!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
