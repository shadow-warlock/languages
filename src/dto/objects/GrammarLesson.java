package dto.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrammarLesson extends DTO {
    private String id;
    private String text;
    private String name;
    private String author;
    private String lang;


    public static List<GrammarLesson> getListByResultSet(ResultSet resultSet){
        List<GrammarLesson> words = new ArrayList<>();
        try{
            while (resultSet.next()){
                GrammarLesson word = new GrammarLesson();
                word.setLang(resultSet.getString("lang"));
                word.setAuthor(resultSet.getString("author"));
                word.setName(resultSet.getString("name"));
                word.setId(resultSet.getString("id"));
                word.setText(resultSet.getString("text"));
                words.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  words;
    }

    public static final String TABLE = "grammar_lessons";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("lang", lang);
        map.put("id", id);
        map.put("author", author);
        map.put("text", text);
        map.put("name", name);
        return  map;
    }
}
