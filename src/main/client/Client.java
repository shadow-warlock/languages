package main.client;

import dto.GetContainer;
import dto.PostContainer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Socket client;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public static final int PORT = 9966;

    public Client(){
        try {
            client = new Socket("localhost", PORT);
            inputStream = new ObjectInputStream(client.getInputStream());
            outputStream = new ObjectOutputStream(client.getOutputStream());
//            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postToServer(PostContainer message){
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PostContainer getToServer(GetContainer message){
        try {
            outputStream.writeObject(message);
            outputStream.flush();
            return (PostContainer) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
