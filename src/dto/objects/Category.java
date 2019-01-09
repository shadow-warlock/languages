package dto.objects;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Category implements Serializable, DTO {

    public static final String TABLE = "categories";

    private String user;
    private String title;
    private String lang;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public static List<Category> getListByResultSet(ResultSet resultSet){
        List<Category> categories = new ArrayList<>();
        try{
            while (resultSet.next()){
                Category category = new Category();
                category.setLang(resultSet.getString("lang"));
                category.setTitle(resultSet.getString("title"));
                category.setUser(resultSet.getString("user"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  categories;
    }

    @Override
    public HashMap<String, String> getMap() {
        return null;
    }
}
