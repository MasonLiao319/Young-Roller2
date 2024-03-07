import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Armory> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Armory item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void clear() {
        items.clear();
    }

    public List<Armory> getItems() {
        return new ArrayList<>(items); // Returns a copy of the list to prevent external modification
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Armory item : items) {
            if (item != null) {
                sb.append(item.getName()).append("\n");
            }
        }
        return sb.toString();
    }
}
