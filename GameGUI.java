// File: GameGUI.java
// Course: CS2212A - Fall 2025
// Project: Group Adventure Game
//
// Description:
//     This class provides the full graphical user interface for
//     the adventure game. It is responsible for rendering rooms,
//     displaying item sprites on top of the background image,
//     handling player interactions through buttons, and updating
//     the interface whenever the game state changes.
//
//     The GUI communicates directly with the Game class for all
//     gameplay actions such as movement, item pickup, dialogue,
//     NPC interaction, puzzle solving, and inventory updates.
//     The interface is built using Swing components, and all
//     rendering of room images and hotspots is performed by a
//     custom BackgroundPanel defined inside this file.
//
//     This class also manages:
//        - Drawing item hotspots on top of the background
//        - Selecting items by clicking on them
//        - Displaying contextual room descriptions
//        - Showing dialogue overlays for NPC conversations
//        - Hosting the inventory popup window
//        - Managing sprite loading for all items in the game
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


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class GameGUI extends JFrame {

    private Game game;

    private JLabel roomTitle;
    private BackgroundPanel centerPanel;
    private JTextArea roomDescription;

    private JPanel dialoguePanel;
    private JTextArea dialogueText;

    private JDialog inventoryDialog;
    private DefaultListModel<String> inventoryModel;

    private final java.util.List<Hotspot> hotspots = new ArrayList<>();
    private Hotspot hoveredHotspot = null;
    private Hotspot selectedHotspot = null;

    int imageX = 0;
    int imageY = 0;
    int imageW = 1;
    int imageH = 1;

    private Map<String, Image> itemImages = new HashMap<>();

    public GameGUI(Game game) {
        this.game = game;
        this.game.setGUI(this);

        setTitle("CS2212 Adventure Game");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loadItemImages();

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.DARK_GRAY);

        roomTitle = new JLabel("", SwingConstants.CENTER);
        roomTitle.setForeground(Color.WHITE);
        roomTitle.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(Color.DARK_GRAY);

        JButton settingsButton = new JButton("Settings");
        JButton inventoryButton = new JButton("Inventory");

        settingsButton.addActionListener(e -> openSettings());
        inventoryButton.addActionListener(e -> openInventory());

        rightPanel.add(settingsButton);
        rightPanel.add(inventoryButton);

        titlePanel.add(roomTitle, BorderLayout.CENTER);
        titlePanel.add(rightPanel, BorderLayout.EAST);

        add(titlePanel, BorderLayout.NORTH);

        centerPanel = new BackgroundPanel();
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setLayout(new BorderLayout());

        dialoguePanel = new JPanel(new BorderLayout());
        dialoguePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        dialoguePanel.setBackground(new Color(50, 50, 50, 200));
        dialoguePanel.setPreferredSize(new Dimension(getWidth(), 120));
        dialoguePanel.setVisible(false);

        dialogueText = new JTextArea();
        dialogueText.setEditable(false);
        dialogueText.setForeground(Color.WHITE);
        dialogueText.setBackground(new Color(0, 0, 0, 0));
        dialogueText.setFont(new Font("Arial", Font.PLAIN, 18));
        dialogueText.setLineWrap(true);
        dialogueText.setWrapStyleWord(true);
        dialogueText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        dialoguePanel.add(dialogueText, BorderLayout.CENTER);
        centerPanel.add(dialoguePanel, BorderLayout.SOUTH);

        roomDescription = new JTextArea();
        roomDescription.setOpaque(false);
        roomDescription.setEditable(false);
        roomDescription.setForeground(Color.WHITE);
        roomDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        roomDescription.setLineWrap(true);
        roomDescription.setWrapStyleWord(true);

        JPanel descPanel = new JPanel(new BorderLayout());
        descPanel.setOpaque(false);
        descPanel.add(roomDescription, BorderLayout.SOUTH);

        centerPanel.add(descPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        JPanel cmdPanel = new JPanel(new GridLayout(1, 6));
        cmdPanel.setBackground(Color.DARK_GRAY);

        JButton btnGo = new JButton("Go");
        JButton btnPickUp = new JButton("Pick Up");
        JButton btnExamine = new JButton("Examine");
        JButton btnUse = new JButton("Use");
        JButton btnTalk = new JButton("Talk");
        JButton btnGive = new JButton("Give");

        cmdPanel.add(btnGo);
        cmdPanel.add(btnPickUp);
        cmdPanel.add(btnExamine);
        cmdPanel.add(btnUse);
        cmdPanel.add(btnTalk);
        cmdPanel.add(btnGive);

        add(cmdPanel, BorderLayout.SOUTH);

        btnGo.addActionListener(e -> moveDialog());
        btnPickUp.addActionListener(e -> pickUpSelected());
        btnExamine.addActionListener(e -> examineSelected());
        btnUse.addActionListener(e -> {
            String item = chooseItem();
            if (item != null) {
                game.handleUse(item);
                refreshUI();
            }
        });
        btnTalk.addActionListener(e -> {
            String npc = chooseNPC();
            if (npc != null) {
                game.handleTalk(npc);
                refreshUI();
            }
        });
        btnGive.addActionListener(e -> {
            String item = chooseInventoryItem();
            String npc = chooseNPC();
            if (item != null && npc != null) {
                game.handleGive("give " + item + " to " + npc);
                refreshUI();
            }
        });

        setupInventoryDialog();
        refreshUI();
        setVisible(true);
    }

    private void loadItemImages() {
        System.out.println("Loading item images from: " + System.getProperty("user.dir"));
        itemImages.put("Rare Book", loadImage("./images/book.png"));
        itemImages.put("Lockpick", loadImage("./images/lockpick.png"));
        itemImages.put("Chemistry Exam Copy", loadImage("./images/exam.png"));
        itemImages.put("Dumbbell", loadImage("./images/weight.png"));
        itemImages.put("Rotten Fish", loadImage("./images/rottenfish.png"));
        itemImages.put("Moldy Bread", loadImage("./images/moldybread.png"));
        itemImages.put("Wrappers", loadImage("./images/wrappers.png"));
        itemImages.put("Broken Bottle", loadImage("./images/brokenbottle.png"));
        itemImages.put("Password Note", loadImage("./images/password.png"));
        itemImages.put("Apple", loadImage("./images/apple.png"));
        itemImages.put("Sandwich", loadImage("./images/sandwhich.png"));

        itemImages.put("Blue Key", loadImage("./images/bluekey.png"));
        itemImages.put("Green Key", loadImage("./images/greenKey.png"));
        itemImages.put("Red Key", loadImage("./images/redkey.png"));
        
        System.out.println("Images loaded: " + itemImages.size() + " items");
    }

    private Image loadImage(String relativePath) {
        try {
            Image img = new ImageIcon(relativePath).getImage();
            
            int attempts = 0;
            while (img.getWidth(null) < 0 && attempts < 10) {
                Thread.sleep(100);
                attempts++;
            }
            
            if (img.getWidth(null) > 0) {
                System.out.println("  SUCCESS: " + relativePath);
                return img;
            } else {
                System.out.println("  FAILED: " + relativePath);
            }
        } catch (Exception e) {
            System.out.println("  ERROR loading " + relativePath);
        }
        
        BufferedImage placeholder = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = placeholder.createGraphics();
        g2d.setColor(new Color(200, 100, 100));
        g2d.fillRect(0, 0, 128, 128);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, 127, 127);
        g2d.dispose();
        return placeholder;
    }

    public void showDialogue(String message) {
        if (message != null && !message.trim().isEmpty()) {
            dialogueText.setText(message);
            dialoguePanel.setVisible(true);
        } else {
            dialoguePanel.setVisible(false);
            Room room = game.getPlayer().getCurrentRoom();
            roomDescription.setText(room.getDescription());
        }
        repaint();
    }

    public void openLockpickPuzzle(Runnable onSuccess) {
        LockpickPuzzleDialog.showPuzzle(this, onSuccess);
    }

    private void openSettings() {
        JOptionPane.showMessageDialog(this, "Settings coming soon!");
    }

    private void setupInventoryDialog() {
        inventoryDialog = new JDialog(this, "Inventory", false);
        inventoryDialog.setSize(300, 400);
        inventoryDialog.setLayout(new BorderLayout());

        inventoryModel = new DefaultListModel<>();
        JList<String> list = new JList<>(inventoryModel);
        inventoryDialog.add(new JScrollPane(list), BorderLayout.CENTER);

        JButton dropButton = new JButton("Drop Item");
        dropButton.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null) {
                Item item = game.getPlayer().getInventory().get(selected);
                if (item != null) {
                    game.getPlayer().getInventory().dropItem(item);
                    game.getPlayer().getCurrentRoom().addItem(item);
                    refreshInventory();
                    refreshUI();
                    JOptionPane.showMessageDialog(inventoryDialog, "Dropped " + selected);
                }
            } else {
                JOptionPane.showMessageDialog(inventoryDialog, "Please select an item to drop.");
            }
        });
        inventoryDialog.add(dropButton, BorderLayout.SOUTH);
    }

    private void openInventory() {
        refreshInventory();
        inventoryDialog.setVisible(true);
    }

    private void refreshInventory() {
        inventoryModel.clear();
        for (Item i : game.getPlayer().getInventory().display()) {
            inventoryModel.addElement(i.getItemName());
        }
    }

    private String getSelectedItemName() {
        return selectedHotspot != null ? selectedHotspot.itemName : null;
    }

    private void examineSelected() {
        String name = getSelectedItemName();
        if (name == null) {
            roomDescription.setText("Click an object first.");
            return;
        }
        String description = game.handleExamine(name);
        roomDescription.setText("You examine " + name + ": " + description);
        refreshUI();
    }

    private void pickUpSelected() {
        String name = getSelectedItemName();
        if (name == null) {
            roomDescription.setText("Click an object first.");
            return;
        }

        Inventory inv = game.getPlayer().getInventory();
        boolean hadBefore = (inv.get(name) != null);

        game.handlePickup(name);

        boolean hasAfter = (inv.get(name) != null);
        if (!hadBefore && hasAfter) {
            roomDescription.setText("You picked up " + name + ".");
        } else {
            roomDescription.setText("That item is not here.");
        }
        refreshUI();
    }

    private void buildHotspotsForRoom() {
        hotspots.clear();
        hoveredHotspot = null;
        selectedHotspot = null;

        Room room = game.getPlayer().getCurrentRoom();
        int itemCount = 0;

        for (Item item : room.getItems()) {
            String itemName = item.getItemName();
            double posX, posY;

            switch (itemName) {
                case "Lockpick":
                    posX = 0.60; posY = 0.70;
                    break;
                case "Dumbbell":
                    posX = 0.40; posY = 0.75;
                    break;
                case "Rare Book":
                    posX = 0.30; posY = 0.55;
                    break;
                case "Password Note":
                    posX = 0.50; posY = 0.60;
                    break;
                case "Chemistry Exam Copy":
                    posX = 0.45; posY = 0.60;
                    break;
                case "Apple":
                    posX = 0.18; posY = 0.78;
                    break;
                case "Sandwich":
                    posX = 0.19; posY = 0.37;
                    break;
                case "Rotten Fish":
                    posX = 0.60; posY = 0.57;
                    break;
                case "Moldy Bread":
                    posX = 0.70; posY = 0.79;
                    break;
                case "Wrappers":
                    posX = 0.78; posY = 0.58;
                    break;
                case "Blue Key":
                case "Green Key":
                case "Red Key":
                    posX = 0.50 + (itemCount * 0.15);
                    posY = 0.50;
                    break;
                default:
                    posX = 0.2 + (itemCount % 5) * 0.15;
                    posY = 0.3 + (itemCount / 5) * 0.2;
            }

            hotspots.add(new Hotspot(itemName, posX, posY, 0.12, 0.12));
            itemCount++;
        }
    }

    private Hotspot findHotspotAt(int x, int y) {
        for (Hotspot h : hotspots) {
            Rectangle r = h.getBounds(imageX, imageY, imageW, imageH);
            if (r.contains(x, y)) return h;
        }
        return null;
    }

    private static class Hotspot {
        String itemName;
        double relX, relY, relW, relH;

        Hotspot(String itemName, double relX, double relY, double relW, double relH) {
            this.itemName = itemName;
            this.relX = relX;
            this.relY = relY;
            this.relW = relW;
            this.relH = relH;
        }

        Rectangle getBounds(int imgX, int imgY, int imgW, int imgH) {
            int x = imgX + (int)(relX * imgW);
            int y = imgY + (int)(relY * imgH);
            int w = (int)(relW * imgW);
            int h = (int)(relH * imgH);

            return new Rectangle(x, y, w, h);
        }
    }

    class BackgroundPanel extends JPanel {
        private Image background;

        BackgroundPanel() {
            setLayout(new BorderLayout());

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    hoveredHotspot = findHotspotAt(e.getX(), e.getY());
                    repaint();
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (dialoguePanel.isVisible()) {
                        showDialogue(null);
                        return;
                    }

                    selectedHotspot = findHotspotAt(e.getX(), e.getY());
                    if (selectedHotspot != null) {
                        roomDescription.setText("Selected: " + selectedHotspot.itemName);
                    } else {
                        Room room = game.getPlayer().getCurrentRoom();
                        roomDescription.setText(room.getDescription());
                    }
                    repaint();
                }
            });
        }

        public void setBackgroundImage(Image img) {
            this.background = img;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (background != null) {
                int panelW = getWidth();
                int panelH = getHeight();

                int imgW0 = background.getWidth(null);
                int imgH0 = background.getHeight(null);

                if (imgW0 > 0 && imgH0 > 0) {
                    double scale = Math.min((double) panelW / imgW0, (double) panelH / imgH0);
                    imageW = (int)(imgW0 * scale);
                    imageH = (int)(imgH0 * scale);
                    imageX = (panelW - imageW) / 2;
                    imageY = (panelH - imageH) / 2;

                    g.drawImage(background, imageX, imageY, imageW, imageH, this);
                }
            }

            for (Hotspot h : hotspots) {
                Image sprite = itemImages.get(h.itemName);
                if (sprite != null) {
                    Rectangle r = h.getBounds(imageX, imageY, imageW, imageH);
                    g.drawImage(sprite, r.x, r.y, r.width, r.height, this);
                }
            }

            Graphics2D g2 = (Graphics2D) g;

            if (hoveredHotspot != null) {
                Rectangle r = hoveredHotspot.getBounds(imageX, imageY, imageW, imageH);
                g2.setColor(new Color(255, 255, 0, 80));
                g2.fill(r);
                g2.setColor(Color.YELLOW);
                g2.setStroke(new BasicStroke(2));
                g2.draw(r);
            }

            if (selectedHotspot != null) {
                Rectangle r = selectedHotspot.getBounds(imageX, imageY, imageW, imageH);
                g2.setColor(new Color(0, 255, 255, 70));
                g2.fill(r);
                g2.setColor(Color.CYAN);
                g2.setStroke(new BasicStroke(3));
                g2.draw(r);
            }
        }
    }

    private void moveDialog() {
        Map<String, Room> connections = game.getPlayer().getCurrentRoom().getConnections();
        if (connections.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No exits.");
            return;
        }

        String[] options = new String[connections.size()];
        String[] dirs = new String[connections.size()];
        int i = 0;

        for (Map.Entry<String, Room> e : connections.entrySet()) {
            options[i] = e.getValue().getName() + " (" + e.getKey() + ")";
            dirs[i] = e.getKey();
            i++;
        }

        String c = (String) JOptionPane.showInputDialog(
                this,
                "Choose direction:",
                "Move",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (c != null) {
            for (int j = 0; j < options.length; j++) {
                if (options[j].equals(c)) {
                    game.handleMove(dirs[j]);
                    refreshUI();
                    break;
                }
            }
        }
    }

    private String chooseItem() {
        DefaultListModel<String> m = new DefaultListModel<>();
        for (Item i : game.getPlayer().getCurrentRoom().getItems()) m.addElement(i.getItemName());
        for (Item i : game.getPlayer().getInventory().display()) if (!m.contains(i.getItemName())) m.addElement(i.getItemName());
        return selectionMenu("Select Item", m);
    }

    private String chooseInventoryItem() {
        DefaultListModel<String> m = new DefaultListModel<>();
        for (Item i : game.getPlayer().getInventory().display()) m.addElement(i.getItemName());
        return selectionMenu("Select from Inventory", m);
    }

    private String chooseNPC() {
        DefaultListModel<String> m = new DefaultListModel<>();
        for (Npc n : game.getPlayer().getCurrentRoom().getNpcs()) m.addElement(n.getName());
        return selectionMenu("Select NPC", m);
    }

    private String selectionMenu(String title, DefaultListModel<String> model) {
        if (model.isEmpty()) return null;
        JList<String> list = new JList<>(model);
        int r = JOptionPane.showConfirmDialog(this, new JScrollPane(list), title, JOptionPane.OK_CANCEL_OPTION);
        if (r == JOptionPane.OK_OPTION) return list.getSelectedValue();
        return null;
    }

    public void refreshUI() {
        Room room = game.getPlayer().getCurrentRoom();

        roomTitle.setText(room.getName());

        String path = room.getImagePath();
        ImageIcon raw = new ImageIcon(path);
        centerPanel.setBackgroundImage(raw.getImage());

        buildHotspotsForRoom();
        repaint();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.start();
        new GameGUI(g);
    }
}
