package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static final int PORT = 9966;
    private ServerSocket guestSocet;
    public boolean serverRunning = true;

    private Server(){
        try {
            guestSocet = new ServerSocket(PORT);
            while (serverRunning){
                Connection client = new Connection(guestSocet.accept());
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
