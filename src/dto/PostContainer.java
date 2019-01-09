package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostContainer extends Container {
    private List<Object> items = new ArrayList<Object>();

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public void addItem(Object item){
        items.add(item);
    }

}
