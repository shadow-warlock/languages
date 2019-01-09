package main.api;

import dto.Commands;
import dto.GetContainer;
import dto.PostContainer;
import dto.objects.Language;
import dto.objects.Word;
import main.Application;

import java.util.List;

public class LanguageAPI {

    public static List<Language> getLanguages(String user){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.SELECT);
        message.setTable(Language.TABLE);
        message.addItem("user", user);
        List items = Application.getInstance().client.getToServer(message);
        return items;
    }

    public static void replaceLanguage(Language lang){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.REPLACE);
        message.setTable(Language.TABLE);
        message.addItem(lang);
        Application.getInstance().client.postToServer(message);
    }

}
