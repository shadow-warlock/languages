package dto;

import dto.objects.*;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public class Container  implements Serializable {
    private String command;
    private String table;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Commands getCommand() {
        return Commands.valueOf(command);
    }

    public void setCommand(Commands command) {
        this.command = command.name();
    }

    public List ResultToDTO(ResultSet rs){
        switch (table){
            case Category.TABLE:
                return Category.getListByResultSet(rs);
            case Language.TABLE:
                return Language.getListByResultSet(rs);

            case Word.TABLE:
                return Word.getListByResultSet(rs);
            case WordLesson.TABLE:
                return WordLesson.getListByResultSet(rs);
            case Stat.TABLE:
                return Stat.getListByResultSet(rs);
            case User.TABLE:
                return User.getListByResultSet(rs);

            case GrammarLesson.TABLE:
                return GrammarLesson.getListByResultSet(rs);

        }
        return null;
    }

}
