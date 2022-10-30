package fr.endide.bedwars.arena;

import java.util.ArrayList;
import java.util.List;

public class itemManager {
    private List<item> items = new ArrayList<>();
    public void addItem(item item) {
        items.add(item);
    }
    public List<item> getItems() {
        return items;
    }
}
