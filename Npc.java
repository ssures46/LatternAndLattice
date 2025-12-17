/*


how to use class

private static void waitForEnter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("click enter to continue...");
        sc.nextLine();
    }

Item sword = new Item("iron sword", "A old and dull sword");

        Inventory playerInventory = new Inventory();
        playerInventory.addItem(sword);

        NPC garrick = new NPC(
            "Garrick",
            "A strong blacksmith with years of work",
            "Forge"
        );

        garrick.addAcceptedItem("Iron Sword");

        garrick.addDialouge("default", "Greetings traveler, need your blade sharpened?");
        garrick.addDialouge("thanks", "This sword has great potential, let me refine it for you.");
        garrick.addDialouge("completed", "There is nothing more I can do for you.");

        System.out.println("You talk to Garrick:");
        System.out.println(garrick.talk("default"));

        waitForEnter();

        boolean gaveItem = garrick.giveItem(sword);
        if (gaveItem) {
            System.out.println("you give Garrick the iron sword.");
            System.out.println(garrick.talk("thanks"));
            garrick.completeQuest();
        } else {
            System.out.println("graick refuses the item.");
        }

        waitForEnter();

        System.out.println("you talk to Garrick again:");
        System.out.println(garrick.talk("anything"));

        waitForEnter();

        System.out.println("player inventory:");
        for (Item item : playerInventory.getItems()) {
            System.out.println("- " + item.getName());
        }

        waitForEnter();


*/



import java.util.ArrayList;
import java.util.List;

public class Npc {

    // Attributes

    
    private String name;

    
    private String description;

    
    private String currRoom;

    
    private List<Item> inventory;

    // all the items the npc can take
    private List<String> acceptedItems;

    //what it has taken
    private List<String> itemsRecieved;

    
    private boolean isInteractable;

    // map storing all dialogue options, given a specific action from player
    // key is action, value is dialogue line
    private List<DialogueEntry> dialogue;

    // helper class to store action and dialog text together
    private static class DialogueEntry {
        String action;
        String text;

        DialogueEntry(String action, String text) {
            this.action = action;
            this.text = text;
        }
    }

    // Constructor

    public Npc(
        String name,
        String description,
        String currRoom,
        boolean isInteractable) {

        this.name = name;
        this.description = description;
        this.currRoom = currRoom;
        this.isInteractable = isInteractable;

        this.inventory = new ArrayList<>();
        this.acceptedItems = new ArrayList<>();
        this.itemsRecieved = new ArrayList<>();
        this.dialogue = new ArrayList<>();
    }

    
    public Npc(String name, String description, String currRoom) {
        this(name, description, currRoom, true);
    }

    // methods

    // helper to find a dialogue line by action
    private String getDialogueFor(String action) {
        for (DialogueEntry entry : dialogue) {
            if (entry.action.equals(action)) {
                return entry.text;
            }
        }
        return null;
    }

    // helper to check if an action exists
    private boolean hasDialogueFor(String action) {
        return getDialogueFor(action) != null;
    }

    
    public String talk(String str) {
        if (!isInteractable) {
            //when not interactable
            if (hasDialogueFor("completed")) {
                return getDialogueFor("completed");
            }
            return name + " has nothing more to say.";
        }

        String line = getDialogueFor(str);
        if (line != null) {
            return line;
        }

        //normal dialogue
        String defaultLine = getDialogueFor("default");
        if (defaultLine != null) {
            return defaultLine;
        }

        return name + " stays silent.";
    }

    

    //action: for which dialog to enter
    //dialog: what should be said
    public void addDialogue(String action, String dialog) {
        // replace existing line for this action if it exists
        for (DialogueEntry entry : dialogue) {
            if (entry.action.equals(action)) {
                entry.text = dialog;
                return;
            }
        }
        // otherwise add a new one
        dialogue.add(new DialogueEntry(action, dialog));
    }

    

    //item item, a item of class item to get
    //true if can accpet, false if cant
    public boolean giveItem(Item item) {
        if (!isInteractable || item == null) {
            return false;
        }

        String itemName = item.getItemName();

       
        if (!acceptedItems.contains(itemName)) {
            return false;
        }

        
        itemsRecieved.add(itemName);

        // if we want npc to keep item
        // inventory.add(item);

        return true;
    }

   
    public boolean receiveItem(Item item) {
        if (!isInteractable || item == null) {
            return false;
        }

        String itemName = item.getItemName();

        if (!acceptedItems.contains(itemName)) {
            return false;
        }
    
        itemsRecieved.add(itemName);
        
        return true;
    }

    
    public boolean hasItem(Item item) {
        if (item == null) {
            return false;
        }
        return inventory.contains(item);
    }

    
    public List<Item> displayInventory() {
        
        return new ArrayList<>(inventory);
    }

    
    public void completeQuest() {
        isInteractable = false;

        
        if (!hasDialogueFor("done")) {
            addDialogue("done", name + " has already finished their task");
        }
    }

    

    public void addToInventory(Item item) {
        if (item != null) {
            inventory.add(item);
        }
    }

    public void addAcceptedItem(String itemName) {
        if (itemName != null && !itemName.isEmpty()) {
            acceptedItems.add(itemName);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrRoom() {
        return currRoom;
    }

    public boolean getIsInteractable() {
        return isInteractable;
    }

    public String getDialogue() {
        // Call the existing talk method with the conventional "default" action.
         return talk("default");
    }
}
