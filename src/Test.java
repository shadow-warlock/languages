import java.sql.*;
import java.util.Arrays;

import static java.sql.DriverManager.getConnection;

public class Test {

    private static String url = "jdbc:mysql://zhuravlev.beget.tech/zhuravlev_langs";
    private static String login = "zhuravlev_langs";
    private static String password = "123456";
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = getConnection(url, login, password);
            // getting Statement object to execute query
            stmt = connection.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery("SELECT * FROM words");
            while (rs.next()) {
                int id = rs.getInt(1);
                String word = rs.getString(2);
                String translate = rs.getString(3);
                System.out.printf("id: %d, word: %s, translate: %s %n", id, word, translate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
