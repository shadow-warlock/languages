package dto.objects;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User implements DTO, Serializable {
    private String login;
    private String password;
    private String type;

    public static final String TABLE = "users";

    public static final String TEACHER = "Учитель";
    public static final String SCHOLAR = "Ученик";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static List<User> getListByResultSet(ResultSet resultSet){
        List<User> users = new ArrayList<>();
        try{
            while (resultSet.next()){
                User user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setType(resultSet.getString("type"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  users;
    }

    @Override
    public HashMap<String, String> getMap() {
        return null;
    }
}
