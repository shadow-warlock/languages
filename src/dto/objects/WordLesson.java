package dto.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordLesson extends DTO {
    private String name;
    private String author;
    private String lang;


    public static List<WordLesson> getListByResultSet(ResultSet resultSet){
        List<WordLesson> words = new ArrayList<>();
        try{
            while (resultSet.next()){
                WordLesson word = new WordLesson();
                word.setLang(resultSet.getString("lang"));
                word.setAuthor(resultSet.getString("author"));
                word.setName(resultSet.getString("name"));
                words.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  words;
    }

    public static final String TABLE = "lessons";

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
        map.put("name", name);
        map.put("author", author);
        map.put("lang", lang);
        return  map;
    }
}
