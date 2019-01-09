package main.api;

import dto.Commands;
import dto.GetContainer;
import dto.PostContainer;
import dto.objects.Stat;
import dto.objects.Word;
import main.Application;

import java.util.List;

public class StatAPI {
    public static void replaceStat(Stat stat){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.REPLACE);
        message.setTable(Stat.TABLE);
        message.addItem(stat);
        Application.getInstance().client.postToServer(message);
    }

    public static List<Stat> getStats(String lang, String user){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.SELECT);
        message.setTable(Stat.TABLE);
        message.addItem("lang", lang);
        message.addItem("user", user);
        List items = Application.getInstance().client.getToServer(message);
        return items;
    }


}
