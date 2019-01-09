package main.api;

import dto.Commands;
import dto.GetContainer;
import dto.PostContainer;
import dto.objects.Category;
import dto.objects.Word;
import main.Application;

import java.util.List;

public class WordAPI {
    public static void replaceWord(Word word){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.REPLACE);
        message.setTable(Word.TABLE);
        message.addItem(word);
        Application.getInstance().client.postToServer(message);
    }

    public static void deleteWord(Word word){
        PostContainer message = new PostContainer();
        message.setCommand(Commands.DELETE);
        message.setTable(Word.TABLE);
        message.addItem(word);
        Application.getInstance().client.postToServer(message);
    }
    public static List<Word> getWordsByCategory(String lang, String user, String category){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.SELECT);
        message.setTable(Word.TABLE);
        message.addItem("lang", lang);
        message.addItem("user", user);
        message.addItem("category", category);

        List items =  Application.getInstance().client.getToServer(message);
        return items;
    }
    public static List<Word> getWords(String lang, String user){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.SELECT);
        message.setTable(Word.TABLE);
        message.addItem("lang", lang);
        message.addItem("user", user);
        List items =  Application.getInstance().client.getToServer(message);

        return items;
    }

    public static List<Word> getWordsByLesson(String lang, String lesson){
        GetContainer message = new GetContainer();
        message.setCommand(Commands.WORDS_CONNECTED);
        message.setTable(Word.TABLE);
        message.addItem("lang", lang);
        message.addItem("name", lesson);
        List items =  Application.getInstance().client.getToServer(message);
        return items;
    }

}
