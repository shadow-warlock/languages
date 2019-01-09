package dto.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Language implements DTO {
    public static final String TABLE = "langs";

    private String lang;
    private String user;

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

    public static List<Language> getListByResultSet(ResultSet resultSet){
        List<Language> languages = new ArrayList<>();
        try{
            while (resultSet.next()){
                Language lang = new Language();
                lang.setLang(resultSet.getString("lang"));
                lang.setUser(resultSet.getString("user"));
                languages.add(lang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  languages;
    }

    @Override
    public HashMap<String, String> getMap() {
        return null;
    }
}
