import java.util.ArrayList;

public class Item{
    private String description;
    private String itemName;
    private ArrayList<String> attributes;
    private ArrayList<Item> containedItems;

    //Constructor for an Item in
    public Item(String itemName, String description){
        this.description = description;
        this.itemName = itemName;
        this.attributes = new ArrayList<>();
        this.containedItems = new ArrayList<>();

    }
    
    //getter returns items name
    public String getItemName(){
        return itemName;
    }

    //returns items name
    public String getItemDescription(){
        return description;
    }

    //temp method to add attribute to the array list will be changed later
    public void addAttribute(String attr) {
        attributes.add(attr);
    }

    //checks if attribute is in the attributes list for the current item
    public boolean hasAttribute(String attr){
        return attributes.contains(attr);
    }

    //returns the list of atributes
    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public ArrayList<Item> getItems() {
        return containedItems;
    }

    public void addItem(Item item) {
        if (item != null) {
            containedItems.add(item);
        }
    }

}
