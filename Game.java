// File: Game.java
// Course: CS2212A - Fall 2025
// Project: Group Adventure Game
// Description:
//     This file contains the main controller used to run the
//     adventure game. The class is responsible for building the
//     entire world (rooms, puzzles, items, NPCs), handling the
//     player's actions, and maintaining the overall flow of play.
//     It also keeps track of turn count, win conditions, and
//     communication with the GUI when one is attached.
//
//     The Game class acts as the central hub of the project.
//     All major gameplay behaviour—movement, dialogue, item use,
//     puzzle interaction, and game termination—originates here.
//     Other classes (Player, Room, Npc, Puzzle, Inventory) are
//     connected through this controller.
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




import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    // list of every room in the game world, filled when the world is created
    private List<Room> rooms = new ArrayList<>();

    // the single player object used for movement, inventory, etc.
    private Player player;

    // tracks if the main loop can run or if the game shouldn't process commands
    private boolean isRunning = false;

    // reference to GUI so the game can call UI updates when needed
    private GameGUI gui;

    // holds all puzzles that exist in the world (door locks, locker puzzle, etc.)
    private List<Puzzle> puzzles = new ArrayList<>();

    // how many actions the player has performed so far
    private int turnCount = 0;

    // optional turn limit; left null if unlimited
    private Integer maxTurns = null;

    // becomes true when the game ends, either by victory or quitting
    private boolean gameOver = false;

    // stores the message displayed when gameOver becomes true
    private String gameOverMessage = "";

    // references to specific puzzles we need later
    private Puzzle lockerPuzzle;
    private Puzzle mainDoorPuzzle;

    // sets up the entire world and places the player in the starting room
    public void start() {
        initializeWorld(); // builds rooms, items, puzzles, etc.

        // look for the one starting room marked in the world
        Room startingRoom = findStartingRoom();
        if (startingRoom == null && !rooms.isEmpty()) {
            // fallback to the first room if no starting room was tagged
            startingRoom = rooms.get(0);
        }

        // create the player standing in the starting room
        this.player = new Player("Player", startingRoom);

        // reset game state so a fresh playthrough works correctly
        isRunning = true;
        gameOver = false;
        turnCount = 0;
        gameOverMessage = "";
    }

    // allows other classes (like GUI) to access the player when needed
    public Player getPlayer(){
        return player;
    }

    // connects the GUI component to the game so dialogue and world updates show visually
    public void setGUI(GameGUI gui) {
        this.gui = gui;
    }

    // main text-based gameplay loop; reads input and processes commands
    public void run() {
        if (!isRunning || player == null) {
            // user tried to run the game before calling start()
            System.out.println("Game has not been started. Call start() first.");
            return;
        }

        // scanner closes automatically at end of block
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Adventure Game!");
            System.out.println("Type 'help' for a list of commands.\n");

            // loop continues until the player quits or wins
            while (isRunning && !gameOver) {

                System.out.print("\n> ");
                String line = scanner.nextLine().trim();

                // ignore empty lines so nothing weird happens
                if (line.isEmpty()) {
                    continue;
                }

                // processes the user's command
                handleCommand(line);

                // check if the player is standing in the ending room
                if (checkVictory()) {
                    endGame();
                } 
                // check turn limit if one exists
                else if (maxTurns != null && turnCount >= maxTurns) {
                    gameOver = true;
                    gameOverMessage = "You ran out of turns!";
                }
            }

            // only print an ending message if one was set
            if (!gameOverMessage.isEmpty()) {
                System.out.println("\n" + gameOverMessage);
            }
        }

        System.out.println("Thanks for playing!");
    }

    // returns true if the current room is marked as the final room
    public boolean checkVictory() {
        Room current = player.getCurrentRoom();
        return current != null && current.isEndingRoom();
    }

    // triggers the win condition and stops the run loop
    public void endGame() {
        gameOver = true;
        gameOverMessage = "Congratulations! You reached the end!";
        isRunning = false;
    }

    // exits the game at the player's request
    public void quitGame(boolean save) {
        if (save) {
            // saving is not implemented yet, but the method supports a future feature
            System.out.println("Saving game... (not implemented)");
        }
        isRunning = false;
        gameOver = true;
        gameOverMessage = "You quit the game.";
    }

    // builds all rooms, their connections, NPCs, items, and puzzles
    private void initializeWorld() {
        // clear everything so restarting the game creates a clean world
        rooms.clear();
        puzzles.clear();

        // create every room with name, description, and its image path
        Room schoolDoor = new Room("School Door", "You are inside the school's main door area. The exit is locked by coloured locks.", "images/schoolDoor.png");
        schoolDoor.setStartingRoom(true);
        schoolDoor.setEndingRoom(false);

        Room hallwayA = new Room("Hallway A", "A broad hallway which leads to many other rooms.", "images/hallwayA.png");
        Room hallwayB = new Room("Hallway B", "A broad hallway near the back of the school building.", "images/hallwayB.png");

        Room library = new Room("Library", "Rows of books and a librarian who watches closely.", "images/library.png");
        Room lockerRoom = new Room("Locker Room", "Student lockers; one locker looks suspicious.", "images/lockerRoom.png");
        Room computerLab = new Room("Computer Lab", "Rows of computers; one computer is logged out.", "images/computerLab.png");
        Room chemistryLab = new Room("Chemistry Lab", "Lab benches and a chemistry teacher here.", "images/chemLab.png");
        Room cafeteria = new Room("Cafeteria", "Food counters and trays; some food looks rotten.", "images/cafeteria.png");
        Room gym = new Room("Gym", "The gym is noisy and reeks of sweat.", "images/gym.png");
        Room fitnessRoom = new Room("Fitness Room", "Weights and equipment everywhere.", "images/fitnessRoom.png");

        Room exitRoom = new Room("Exit", "You step outside into sunlight.", "images/exitRoom.png");
        exitRoom.setEndingRoom(true);

        // set up directional links between rooms; these act like graph edges
        schoolDoor.addConnection("down", hallwayA);
        hallwayA.addConnection("up", schoolDoor);

        schoolDoor.addConnection("left", gym);
        gym.addConnection("right", schoolDoor);

        gym.addConnection("down", fitnessRoom);
        fitnessRoom.addConnection("up", gym);

        hallwayA.addConnection("right", cafeteria);
        cafeteria.addConnection("left", hallwayA);

        hallwayA.addConnection("left", chemistryLab);
        chemistryLab.addConnection("right", hallwayA);

        hallwayA.addConnection("down", hallwayB);
        hallwayB.addConnection("up", hallwayA);

        hallwayB.addConnection("right", library);
        library.addConnection("left", hallwayB);

        hallwayB.addConnection("down", lockerRoom);
        lockerRoom.addConnection("up", hallwayB);

        hallwayB.addConnection("left", computerLab);
        computerLab.addConnection("right", hallwayB);

        // final exit link (locked behind puzzle)
        schoolDoor.addConnection("up", exitRoom);

        // create all items that will appear somewhere in the game
        Item lockpick = new Item("Lockpick", "A thin set of picks useful for opening lockers.");
        Item passwordNote = new Item("Password Note", "A crumpled note with a computer login code.");
        Item book = new Item("Rare Book", "An old book requested by the librarian.");
        Item examCopy = new Item("Chemistry Exam Copy", "Copy of the exam.");
        Item apple = new Item("Apple", "A fresh apple.");
        Item sandwich = new Item("Sandwich", "A packaged sandwich.");
        Item dumbbell = new Item("Dumbbell", "A heavy dumbbell.");
        dumbbell.addAttribute("non-pickable"); // tagged to avoid picking up

        // place items into the appropriate rooms
        fitnessRoom.addItem(dumbbell);
        fitnessRoom.addItem(lockpick);

        chemistryLab.addItem(passwordNote);

        cafeteria.addItem(apple);
        cafeteria.addItem(sandwich);

        // NPCs that the player can talk to and give items to
        Npc librarian = new Npc("Librarian", "An attentive librarian", "Library");
        librarian.addDialogue("default", "Please find the rare book for me.");
        librarian.addDialogue("thanks", "Thank you! Here is the key.");
        librarian.addAcceptedItem("Rare Book"); // NPC will only accept this one
        library.addNpc(librarian);

        Npc jock = new Npc("Jock", "A rowdy student lifting weights", "Fitness Room");
        jock.addDialogue("default", "Bring me the password note and I’ll give you the key.");
        jock.addDialogue("thanks", "Nice! Here is the key.");
        jock.addAcceptedItem("Password Note");
        fitnessRoom.addNpc(jock);

        Npc chemTeacher = new Npc("Chemistry Teacher", "A hungry chem teacher", "Chemistry Lab");
        chemTeacher.addDialogue("default", "Bring me food.");
        chemTeacher.addDialogue("thanks", "Good. Here is your key.");
        chemTeacher.addAcceptedItem("Apple");
        chemTeacher.addAcceptedItem("Sandwich");
        chemistryLab.addNpc(chemTeacher);

        // create puzzles and attach them to specific rooms
        lockerPuzzle = new Puzzle("locker1","Locked Student Locker","A locker requiring a lockpick.",lockerRoom);
        lockerPuzzle.getReqItems().add("Lockpick");
        puzzles.add(lockerPuzzle);
        lockerRoom.addPuzzle(lockerPuzzle);

        mainDoorPuzzle = new Puzzle("mainDoor","Main Exit Locks","Three locks block the exit.", schoolDoor);
        mainDoorPuzzle.getReqItems().add("Red Key");
        mainDoorPuzzle.getReqItems().add("Blue Key");
        mainDoorPuzzle.getReqItems().add("Green Key");
        puzzles.add(mainDoorPuzzle);
        schoolDoor.addPuzzle(mainDoorPuzzle);

        // add all rooms to the world list so the game can reference them quickly
        rooms.add(schoolDoor);
        rooms.add(hallwayA);
        rooms.add(hallwayB);
        rooms.add(library);
        rooms.add(lockerRoom);
        rooms.add(computerLab);
        rooms.add(chemistryLab);
        rooms.add(cafeteria);
        rooms.add(gym);
        rooms.add(fitnessRoom);
        rooms.add(exitRoom);
    }

    // loops through rooms and returns whichever one was marked as the start
    private Room findStartingRoom() {
        for (Room r : rooms) {
            if (r.isStartingRoom()) return r;
        }
        return null;
    }

    // splits the user’s command, identifies the action, and dispatches to the right handler
    private void handleCommand(String input) {
        String[] parts = input.split("\\s+");
        String cmd = parts[0].toLowerCase();

        switch (cmd) {
            case "go":
            case "move":
                if (parts.length < 2) {
                    System.out.println("Go where?");
                } else handleMove(parts[1]);
                break;

            case "examine":
            case "look":
                if (parts.length < 2) System.out.println("Examine what?");
                else handleExamine(joinRest(parts, 1));
                break;

            case "pickup":
            case "take":
                if (parts.length < 2) System.out.println("Pick up what?");
                else handlePickup(joinRest(parts, 1));
                break;

            case "give":
                handleGive(input);
                break;

            case "talk":
                if (parts.length < 2) System.out.println("Talk to who?");
                else handleTalk(joinRest(parts, 1));
                break;

            case "use":
                if (parts.length < 2) System.out.println("Use what?");
                else handleUse(joinRest(parts, 1));
                break;
        }
    }

    // tries to move the player; if no connection exists, prints an error
    public void handleMove(String direction) {
        Room before = player.getCurrentRoom();
        player.move(direction); // actual movement happens inside Player

        Room after = player.getCurrentRoom();

        if (before == after) System.out.println("You cannot go that way.");
        else {
            turnCount++;
            System.out.println("You move to " + after.getName() + ".");
        }
    }

    // examines either an item or NPC in the room or inventory
    public String handleExamine(String name) {
        Item item = findItemInCurrentRoomOrInventory(name);

        if (item != null) {
            System.out.println(item.getItemDescription());
            turnCount++;
            return item.getItemDescription();
        }

        Npc npc = findNpcInCurrentRoom(name);
        if (npc != null) {
            System.out.println(npc.getName() + " is here.");
            turnCount++;
            return npc.getName();
        }

        System.out.println("Nothing interesting.");
        return "Nothing.";
    }

    // attempts to take an item from the room and add it to the inventory
    public void handlePickup(String name) {
        Item item = findItemInRoom(player.getCurrentRoom(), name);

        if (item == null) {
            System.out.println("That item is not here.");
            return;
        }

        if (player.pickUp(item)) {
            System.out.println("Picked up " + item.getItemName());
            turnCount++;
        } else {
            System.out.println("You cannot pick that up.");
        }
    }

    // player talks to an NPC; GUI version or console version depending on setup
    public void handleTalk(String npcName) {
        Npc npc = findNpcInCurrentRoom(npcName);

        if (npc == null) {
            System.out.println("Nobody with that name.");
            return;
        }

        String d = npc.getDialogue();

        if (gui != null) gui.showDialogue(d);
        else System.out.println(d);

        turnCount++;
    }

    // attempts to use an item in some meaningful way depending on the room
    public void handleUse(String name) {
        Item item = player.getInventory().get(name);
        Room room = player.getCurrentRoom();

        if (item == null) {
            System.out.println("You don't have that.");
            return;
        }

        // specific logic for unlocking the locker using the lockpick
        if (name.equalsIgnoreCase("Lockpick") && room.getName().equals("Locker Room")) {
            Puzzle p = findPuzzleInRoom("Locked Student Locker", room);

            if (p == null) {
                System.out.println("Nothing to lockpick.");
                return;
            }
            if (p.isSolved()) {
                System.out.println("Already unlocked.");
                return;
            }

            // solves puzzle instantly
            p.markSolvedDirectly();

            // places the reward inside the room
            Item book = new Item("Rare Book","A very old book.");
            room.addItem(book);

            System.out.println("You unlocked the locker and found a book!");

            if (gui != null) gui.refreshUI();
            return;
        }

        System.out.println("Nothing happens.");
        turnCount++;
    }

    // handles giving an item from the player's inventory to an NPC
    public void handleGive(String full) {
        String lower = full.toLowerCase();
        int idx = lower.indexOf(" to ");

        if (idx == -1) {
            System.out.println("Format: give <item> to <npc>");
            return;
        }

        // extracts the item name portion between "give " and " to"
        String itemName = full.substring(5, idx).trim();
        String npcName = full.substring(idx + 4).trim();

        Item item = player.getInventory().get(itemName);
        if (item == null) {
            System.out.println("You don't have that.");
            return;
        }

        Npc npc = findNpcInCurrentRoom(npcName);
        if (npc == null) {
            System.out.println("No such person here.");
            return;
        }

        boolean accepted = npc.receiveItem(item);
        if (!accepted) {
            System.out.println(npc.getName() + " does not want this.");
            return;
        }

        // remove item from inventory
        player.getInventory().giveItem(item);
        System.out.println(npc.getName() + " accepts " + item.getItemName() + ".");

        // generates the correct colored key based on NPC identity
        Item key = null;
        String n = npc.getName().toLowerCase();

        if (n.contains("librarian")) key = new Item("Blue Key","A key.");
        if (n.contains("jock")) key = new Item("Green Key","A key.");
        if (n.contains("chemistry")) key = new Item("Red Key","A key.");

        // drops key into the current room if one exists
        if (key != null) {
            player.getCurrentRoom().addItem(key);
            System.out.println(npc.getName() + " drops a key on the ground.");
        }

        String thanks = npc.talk("thanks");
        if (thanks != null) System.out.println(npc.getName() + ": " + thanks);

        turnCount++;
    }

    // searches through puzzles attached to a room
    private Puzzle findPuzzleInRoom(String name, Room room) {
        for (Puzzle p : room.getPuzzles()) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    // looks for an item in the room only
    private Item findItemInRoom(Room room, String name) {
        for (Item i : room.getItems()) {
            if (i.getItemName().equalsIgnoreCase(name)) return i;
        }
        return null;
    }

    // looks in the room first, then the player’s inventory
    private Item findItemInCurrentRoomOrInventory(String name) {
        Item i = findItemInRoom(player.getCurrentRoom(), name);
        if (i != null) return i;
        return player.getInventory().get(name);
    }

    // returns the NPC in the room with matching name
    private Npc findNpcInCurrentRoom(String name) {
        for (Npc n : player.getCurrentRoom().getNpcs()) {
            if (n.getName().equalsIgnoreCase(name)) return n;
        }
        return null;
    }

    // joins all words in a command after the first word into a single string
    private String joinRest(String[] arr, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < arr.length; i++) {
            if (i > start) sb.append(" ");
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}

