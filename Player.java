// File: Player.java
// Course: CS2212A - Fall 2025
// Project: Group Adventure Game
//
// Description:
//     This class represents the player character within the
//     adventure game. Each Player object stores the player's
//     name, the room they are currently located in, and the
//     Inventory instance used to track all collected items.
//
//     The Player class provides core movement and interaction
//     behaviour. It supports:
//         - Moving between connected rooms
//         - Picking up items from the environment
//         - Using items stored in the inventory
//         - Querying or updating the player’s current room
//
//     The class does not contain any direct UI or puzzle logic;
//     instead, it acts as a data model that the Game controller
//     and GUI both rely on. All inventory operations are passed
//     through to the Inventory class, ensuring consistent state
//     management for collected items.
//
// Authors:
//     Muhammad Abdullah Asim
//     Megha Menikgama
//     Huniya Mughal
//     Samkit Jain
//     Sivaramchandar Suresh
//
// Last Modified:
//     12-02-2025


public class Player{
    private String name;
    private Room currentRoom;
    private Inventory inventory;

    //constructor for the player
    public Player(String name, Room start){
        this.name = name;
        this.currentRoom = start;
        this.inventory = new Inventory();

    }
    //return players name
    public String getName() {
        return name;
    }

    //return players current inventory
    public Inventory getInventory() {
        return inventory;
    }

    //change the players current room
    public void changeRoom(Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    //return the current room the player is in
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void move(String direction) {
        Room next = currentRoom.getConnectedRoom(direction);
        if (next != null) {
            currentRoom = next;
        }
    }

    public boolean pickUp(Item item) {
        if (item == null) {
            return false;
        }
        if (!currentRoom.getItems().contains(item)) {
            return false;
        }

        inventory.addItem(item);
        currentRoom.getItems().remove(item);
        return true;
    }

    public boolean use(Item item) {
        if (item == null) {
            return false;
        }

        if (!inventory.contains(item)) {
            return false;
        }

        inventory.useItem(item);
        return true;
    }
    

}
