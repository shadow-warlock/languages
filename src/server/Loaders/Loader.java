package server.Loaders;

import server.Database;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Loader {
    private Database db;

    public Loader() {
        this.db = new Database();
    }

    public ResultSet getAllWhere(String table, HashMap<String, String> args){
        String query = "SELECT * FROM " + table + " WHERE ";
        for (Map.Entry<String, String> entry : args.entrySet()) {
            if(entry.getValue() == null)
                continue;
            query += " " + entry.getKey() + " = '" + entry.getValue() + "' AND";
        }
        query = query.substring(0, query.length()-3);
        System.out.println(query);
        ResultSet rs = db.select(query);
        return rs;
    }

    public ResultSet getUnicallConnected(String table, HashMap<String, String> args){
        String query = "SELECT * FROM " + table + " WHERE word IN (SELECT word FROM lessons_words WHERE lesson IN (SELECT name FROM lessons WHERE ";
        for (Map.Entry<String, String> entry : args.entrySet()) {
            query += " " + entry.getKey() + " = '" + entry.getValue() + "' AND";
        }
        query = query.substring(0, query.length()-3);
        query += "))";
        ResultSet rs = db.select(query);
        return rs;
    }

    public void replase(String table, HashMap<String, String> args){
        String query = "REPLACE INTO " + table + " (";
        for (Map.Entry<String, String> entry : args.entrySet()) {
            if(entry.getValue() == null)
                continue;
            query += " " + entry.getKey() + ",";
        }
        query = query.substring(0, query.length()-1);
        query += ") VALUES(";
        for (Map.Entry<String, String> entry : args.entrySet()) {
            if(entry.getValue() == null)
                continue;
            query += " '" + entry.getValue() + "',";
        }
        query = query.substring(0, query.length()-1);
        query += ")";
        System.out.println(query);

        db.insert(query);
    }

    public void delete(String table, HashMap<String, String> args){
        String query = "DELETE FROM " + table + " WHERE ";
        for (Map.Entry<String, String> entry : args.entrySet()) {
            if(entry.getValue() == null)
                continue;
            query += " " + entry.getKey() + " = '" + entry.getValue() + "'AND";
        }
        query = query.substring(0, query.length()-3);
        db.insert(query);
    }
}
