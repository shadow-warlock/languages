package server;

import dto.objects.*;
import dto.Container;
import dto.GetContainer;
import dto.PostContainer;
import server.Loaders.Loader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;


public class Connection extends Thread {

    private boolean isRunning = true;
    private Socket client;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Loader loader;

    public Connection(Socket client){
        try {
            this.client = client;
            loader = new Loader();
            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(client.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                Container container = (Container) inputStream.readObject();
                System.out.println(container instanceof PostContainer);
                System.out.println(container instanceof GetContainer);
                System.out.println(container.getComs());
                System.out.println(container.getCommand());
                System.out.println(container.getTable());
                System.out.println(((GetContainer)container).getItems());

                GetContainer get;
                PostContainer post;
                switch (container.getCommand()){
                    case SELECT:
                        get = (GetContainer)container;
                        ResultSet categories = loader.getAllWhere(container.getTable(),get.getItems());
                        outputStream.writeObject(categories);
                        outputStream.flush();
                        break;
                    case REPLACE:
                        post= (PostContainer)container;
                        post.getItems();
                        loader.replase(container.getTable(), ((DTO)post.getItems().get(0)).getMap());
                        break;
                    case DELETE:
                        post= (PostContainer)container;
                        post.getItems();
                        loader.delete(container.getTable(), ((DTO)post.getItems().get(0)).getMap());
                        break;
                    case WORDS_CONNECTED:
                        get = (GetContainer)container;
                        ResultSet words = loader.getUnicallConnected(container.getTable(),get.getItems());
                        outputStream.writeObject(words);
                        outputStream.flush();
                        break;

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void serverStop(){
        isRunning = false;
    }
}
