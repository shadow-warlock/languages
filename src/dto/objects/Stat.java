package dto.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stat extends DTO {

    public static final String TABLE = "stats";

    private String id;
    private String word;
    private String lang;
    private String user;
    private String result;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("lang", lang);
        map.put("id", id);
        map.put("result", result);
        map.put("user", user);
        map.put("word", word);
        return map;
    }

    public static List getListByResultSet(ResultSet resultSet){
        List<Stat> words = new ArrayList<>();
        try{
            while (resultSet.next()){
                Stat word = new Stat();
                word.setLang(resultSet.getString("lang"));
                word.setUser(resultSet.getString("user"));
                word.setWord(resultSet.getString("word"));
                word.setResult(resultSet.getString("result"));

                word.setId(resultSet.getString("id"));

                words.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  words;
    }
}
