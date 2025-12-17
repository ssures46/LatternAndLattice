import java.util.*;

public class Room{
    private String name;
    private String description;
    private String imagePath;
    private List<String> directions = new ArrayList<>();
    private List<Room> connectedRooms = new ArrayList<>();
    private List<Puzzle> roomPuzzles = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Npc> npcs = new ArrayList<>();

    private boolean startingRoom;
    private boolean endingRoom;


    public Room(String name, String description, String imagePath) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        startingRoom = false;
        endingRoom = false;
    }

    public String getName(){
        return name;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void setStartingRoom(boolean startingRoom) {
        this.startingRoom = startingRoom;
    }

    public boolean isStartingRoom() {
        return startingRoom;
    }

    public void setEndingRoom(boolean endingRoom) {
        this.endingRoom = endingRoom;
    }

    public boolean isEndingRoom() {
        return endingRoom;
    }

    public String getDescription(){
        return description;
    }
    
    public void addConnection(String direction, Room room) {
        direction = direction.toLowerCase();
        directions.add(direction);
        connectedRooms.add(room);
    }

    public Room getConnectedRoom(String direction) {
        direction = direction.toLowerCase();
        for (int i = 0; i < directions.size(); i++) {
            if (directions.get(i).equals(direction)) {
                return connectedRooms.get(i);
            }
        }
        return null;
    }

    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void addNpc(Npc npc) {
        if (npc != null) {
            npcs.add(npc);
        }
    }

    public List<Npc> getNpcs() {
        return npcs;
    }

    public Map<String, Room> getConnections() {
        Map<String, Room> map = new HashMap<>();
        for (int i = 0; i < directions.size(); i++) {
            map.put(directions.get(i), connectedRooms.get(i));
        }
        return map;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public List<Puzzle> getPuzzles() {
        return roomPuzzles;
    }
    
    /**
     * Helper method to add a puzzle to this room.
     */
    public void addPuzzle(Puzzle puzzle) {
        if (puzzle != null) {
            roomPuzzles.add(puzzle);
        }
    }
    
}
