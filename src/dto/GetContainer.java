package dto;

import java.io.Serializable;
import java.util.HashMap;

public class GetContainer extends Container implements Serializable {
    private HashMap<String, String> items = new HashMap<String, String>();

    public GetContainer(){
    }

    public HashMap<String, String> getItems() {
        return items;
    }

    public void addItem(String key, String value) {
        items.put(key, value);
    }

}
