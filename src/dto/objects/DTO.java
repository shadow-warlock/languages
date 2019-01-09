package dto.objects;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public abstract class DTO implements Serializable {

    public abstract HashMap<String,String> getMap();

//    public abstract List getListByResultSet(ResultSet resultSet);
}
