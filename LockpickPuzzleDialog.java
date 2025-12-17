/ File: LockpickPuzzleDialog.java
// Course: CS2212A - Fall 2025
// Project: Group Adventure Game
//
// Description:
//     This class implements the lock-picking mini-puzzle used
//     within the graphical version of the adventure game. The
//     dialog presents three numeric pins that the player can
//     adjust using + and – buttons. Each pin cycles through a
//     range of values, and the puzzle is solved once the player
//     matches every pin to its hidden target value.
//
//     The dialog is modal and blocks the main GUI until the
//     puzzle is either solved or dismissed. When all pins match
//     their corresponding hidden target numbers, the puzzle will
//     notify the caller through the provided onSuccess callback.
//     This allows game logic to unlock rooms, reveal items, or
//     trigger other world changes tied to puzzle completion.
//
//     The class uses standard Swing components and does not rely
//     on any external libraries. All state (current pin values,
//     target values, and displayed labels) is maintained inside
//     the dialog instance, and no game data is modified directly.
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
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Simple lock pick puzzle dialog.
 * 
 * You have 3 pins, each with a value from 0 to 5.
 * Use + and - buttons to match the hidden target combination.
 * When all pins match, the puzzle succeeds and calls onSuccess.
 */
public class LockpickPuzzleDialog extends JDialog {

    private static final int PIN_COUNT = 3;
    private static final int PIN_MAX = 5;

    private final int[] currentValues = new int[PIN_COUNT];
    private final int[] targetValues = new int[PIN_COUNT];
    private final JLabel[] pinLabels = new JLabel[PIN_COUNT];

    private final Runnable onSuccess;

    public LockpickPuzzleDialog(Frame owner, Runnable onSuccess) {
        super(owner, "Lockpick Puzzle", true);
        this.onSuccess = onSuccess;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(owner);

        initTargets();
        initUI();
    }

    private void initTargets() {
        Random r = new Random();
        for (int i = 0; i < PIN_COUNT; i++) {
            currentValues[i] = r.nextInt(PIN_MAX + 1);
            targetValues[i] = r.nextInt(PIN_MAX + 1);
        }
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JLabel instructions = new JLabel(
                "<html>Lockpick the door.<br>" +
                "Use the + and - buttons to match all three pins.<br>" +
                "Each pin must show the correct number to unlock.</html>",
                SwingConstants.CENTER
        );
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(instructions, BorderLayout.NORTH);

        JPanel pinsPanel = new JPanel(new GridLayout(1, PIN_COUNT, 10, 10));
        pinsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (int i = 0; i < PIN_COUNT; i++) {
            final int index = i;
            JPanel pinPanel = new JPanel();
            pinPanel.setLayout(new BoxLayout(pinPanel, BoxLayout.Y_AXIS));

            JLabel pinTitle = new JLabel("Pin " + (i + 1), SwingConstants.CENTER);
            pinTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            pinLabels[i] = new JLabel(String.valueOf(currentValues[i]), SwingConstants.CENTER);
            pinLabels[i].setFont(new Font("Arial", Font.BOLD, 24));
            pinLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            pinLabels[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            pinLabels[i].setPreferredSize(new Dimension(50, 50));
            pinLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            pinLabels[i].setVerticalAlignment(SwingConstants.CENTER);

            JButton btnMinus = new JButton("-");
            btnMinus.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnMinus.addActionListener((ActionEvent e) -> {
                currentValues[index] = (currentValues[index] - 1 + (PIN_MAX + 1)) % (PIN_MAX + 1);
                updatePinLabel(index);
                checkSolved();
            });

            JButton btnPlus = new JButton("+");
            btnPlus.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnPlus.addActionListener((ActionEvent e) -> {
                currentValues[index] = (currentValues[index] + 1) % (PIN_MAX + 1);
                updatePinLabel(index);
                checkSolved();
            });

            pinPanel.add(pinTitle);
            pinPanel.add(Box.createVerticalStrut(5));
            pinPanel.add(pinLabels[i]);
            pinPanel.add(Box.createVerticalStrut(5));
            pinPanel.add(btnMinus);
            pinPanel.add(Box.createVerticalStrut(5));
            pinPanel.add(btnPlus);

            pinsPanel.add(pinPanel);
        }

        add(pinsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton giveUpButton = new JButton("Give Up");
        giveUpButton.addActionListener(e -> dispose());
        bottomPanel.add(giveUpButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updatePinLabel(int index) {
        pinLabels[index].setText(String.valueOf(currentValues[index]));
    }

    private void checkSolved() {
        for (int i = 0; i < PIN_COUNT; i++) {
            if (currentValues[i] != targetValues[i]) {
                return;
            }
        }
        // All pins match the hidden target
        JOptionPane.showMessageDialog(
                this,
                "You hear a soft click. The lock opens.",
                "Unlocked",
                JOptionPane.INFORMATION_MESSAGE
        );
        if (onSuccess != null) {
            onSuccess.run();
        }
        dispose();
    }

    /**
     * Helper to open the dialog from anywhere.
     */
    public static void showPuzzle(Frame owner, Runnable onSuccess) {
        LockpickPuzzleDialog dlg = new LockpickPuzzleDialog(owner, onSuccess);
        dlg.setVisible(true);
    }
}

