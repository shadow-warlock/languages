package main.api;

import dto.Commands;
import dto.GetContainer;
import dto.PostContainer;
import dto.objects.Category;
import dto.objects.User;
import dto.objects.Word;
import main.Application;

import java.util.List;

public class UserAPI {
    public static List<User> getAllUsersWithLoginAndPassword(String login, String password){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.SELECT);
        message.setTable(User.TABLE);
        message.addItem("login", login);
        message.addItem("password", password);
        PostContainer request = Application.getInstance().client.getToServer(message);
        if(request == null){
            System.err.println("Ошибка при получении users");
        }
        List items = request.getItems();
        return items;
    }

    public static void replaceUser(User word){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.REPLACE);
        message.setTable(User.TABLE);
        message.addItem(word);
        Application.getInstance().client.postToServer(message);
    }
}
