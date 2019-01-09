package main.api;

import dto.Commands;
import dto.GetContainer;
import dto.PostContainer;
import dto.objects.Category;
import dto.objects.Word;
import dto.objects.WordLesson;
import main.Application;

import java.util.List;

public class WordLessonAPI {
    public static List<WordLesson> getAllLessonsByLang(String lang){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.SELECT);
        message.setTable(WordLesson.TABLE);
        message.addItem("lang", lang);
        PostContainer request = Application.getInstance().client.getToServer(message);
        if(request == null){
            System.err.println("Ошибка при получении");
        }
        List items = request.getItems();
        return items;
    }
    public static void deleteLesson(WordLesson word){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.DELETE);
        message.setTable(WordLesson.TABLE);
        message.addItem(word);
        Application.getInstance().client.postToServer(message);
    }
    public static void replaceLesson(WordLesson word){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.REPLACE);
        message.setTable(WordLesson.TABLE);
        message.addItem(word);
        Application.getInstance().client.postToServer(message);
    }
}
