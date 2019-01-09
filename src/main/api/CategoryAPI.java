package main.api;

import dto.objects.Category;
import dto.Commands;
import dto.GetContainer;
import dto.PostContainer;
import main.Application;
import java.util.List;

public class CategoryAPI {
    public static List<Category> getCategoriesByLangAndUser(String lang, String user){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.SELECT);
        message.setTable(Category.TABLE);
        message.addItem("lang", lang);
        message.addItem("user", user);
        List items =  Application.getInstance().client.getToServer(message);

        return items;
    }
    public static void replaceCategory(Category word){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.REPLACE);
        message.setTable(Category.TABLE);
        message.addItem(word);
        Application.getInstance().client.postToServer(message);
    }
}
