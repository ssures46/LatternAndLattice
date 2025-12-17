import java.util.ArrayList;

/**
 * The Inventory class is a representation of the collection of Items currently carried
 * by the Player in the adventure game. The inventory supports adding, removing,
 * listing items, and placeholder methods for utilizing and giving items (as specified in design documentation).
 */
public class Inventory {

    /** List of the items currently in the player's inventory */
    private ArrayList<Item> items;

    /**
     * Creates an empty inventory.
     */
    public Inventory() {
        this.items = new ArrayList<>();
    }

    /**
     * A copy of the list of items in the inventory is returned.
     *
     * @return ArrayList<Item> list of items
     */
    public ArrayList<Item> display() {
        return new ArrayList<>(items);
    }

    /**
     * An item is added to the player's inventory.
     *
     * @param item Item to be added
     * @return message confirming the action
     */
    public String addItem(Item item) {
        if (item == null) {
            return "Cannot add a null item.";
        }

        items.add(item);
        return item.getItemName() + " added to your inventory.";
    }

    /**
     * An item is removed from the player's inventory.
     *
     * @param item Item to remove
     * @return message establishing the action
     */
    public String dropItem(Item item) {
        if (item == null) {
            return "Cannot drop a null item.";
        }

        if (items.remove(item)) {
            return item.getItemName() + " has been removed from your inventory.";
        } else {
            return item.getItemName() + " is not in your inventory.";
        }
    }

    /**
     * Whether the item already exists in the inventory is returned.
     *
     * @param item item to check
     * @return true if in inventory
     */
    public boolean contains(Item item) {
        return items.contains(item);
    }

    /**
     * Placeholder for utilizing an item.
     * Logic will be implemented later when puzzle/utilization rules are confirmed.
     *
     * @param item item being used
     * @return message for now
     */
    public String useItem(Item item) {
        if (!items.contains(item)) {
            return "You cannot use an item you don't have.";
        }
        return "You attempt to use " + item.getItemName() + "... (functionality coming later)";
    }

    /**
     * Placeholder for giving an item to an NPC.
     * Real logic will be implemented once NPC rules are finished.
     *
     * @param item item being given
     * @return message for now
     */
    public String giveItem(Item item) {
        if (!items.contains(item)) {
            return "You cannot give an item you don't have.";
        }
        items.remove(item);
        return "You attempt to give " + item.getItemName() + "... (functionality coming later)";
    }

    public Item get(String name) {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
