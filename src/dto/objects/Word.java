package dto.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Word extends DTO {
    private String user;
    private String lang;
    private String translate;
    private String transcription;
    private String img;
    private String example;
    private String category;
    private String word;

    public static final String TABLE = "words";

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static List getListByResultSet(ResultSet resultSet){
        List<Word> words = new ArrayList<>();
        try{
            while (resultSet.next()){
                Word word = new Word();
                word.setLang(resultSet.getString("lang"));
                word.setCategory(resultSet.getString("category"));
                word.setExample(resultSet.getString("example"));
                word.setImg(resultSet.getString("img"));
                word.setTranscription(resultSet.getString("transcription"));
                word.setTranslate(resultSet.getString("translate"));
                word.setUser(resultSet.getString("user"));
                word.setWord(resultSet.getString("word"));
                words.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  words;
    }

    public HashMap<String, String> getMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("lang", lang);
        map.put("category", category);
        map.put("example", example);
        map.put("img", img);
        map.put("transcription", transcription);
        map.put("translate", translate);
        map.put("user", user);
        map.put("word", word);
        return  map;
    }

}
