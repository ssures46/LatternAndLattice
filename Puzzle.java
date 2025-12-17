import java.util.*;

public class Puzzle {

    private String id;
    private String name;
    private String description;
    private Room location;

    private boolean isSolved = false;

    private List<String> reqItems = new ArrayList<>();
    private List<String> itemsCollected = new ArrayList<>();

    public Puzzle(String id, String name, String description, Room location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public boolean attempt() {
        if (setSolved()) {
            isSolved = true;
            return true;
        }
        return false;
    }

    public boolean setSolved() {
        return itemsCollected.containsAll(reqItems);
    }

    public boolean addItem(Item item) {
        if (reqItems.contains(item.getItemName())) {
            itemsCollected.add(item.getItemName());
            return true;
        }
        return false;
    }

    public List<String> seeItems() {
        List<String> all = new ArrayList<>();
        all.addAll(reqItems);
        all.addAll(itemsCollected);
        return all;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public List<String> getReqItems() {
        return reqItems;
    }

    public Room getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void markSolvedDirectly() {
        this.isSolved = true;
    }

}
