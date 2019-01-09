package main.api;

import dto.Commands;
import dto.GetContainer;
import dto.PostContainer;
import dto.objects.GrammarLesson;
import dto.objects.Word;
import dto.objects.WordLesson;
import main.Application;

import java.util.List;

public class GrammarLessonAPI {
    public static List<GrammarLesson> getAllLessonsByLang(String lang){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.SELECT);
        message.setTable(GrammarLesson.TABLE);
        message.addItem("lang", lang);
        PostContainer request = Application.getInstance().client.getToServer(message);
        if(request == null){
            System.err.println("Ошибка при получении");
        }
        List items = request.getItems();
        return items;
    }

    public static void replaceLesson(GrammarLesson word){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.REPLACE);
        message.setTable(GrammarLesson.TABLE);
        message.addItem(word);
        Application.getInstance().client.postToServer(message);
    }
    public static void deleteLesson(GrammarLesson word){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.DELETE);
        message.setTable(GrammarLesson.TABLE);
        message.addItem(word);
        Application.getInstance().client.postToServer(message);
    }
}
