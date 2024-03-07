import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.HashMap;
import java.util.Map;

public class ChooseWeaponGUI extends JPanel {
    private final JButton nextButton = new JButton("Next");
    private final JButton backButton = new JButton("Back");
    private final JButton confirmButton = new JButton("Confirm");
    private JLabel playerNameLabel;
    private final JTextArea armorySelectionArea = new JTextArea(12,20);
    private final JLabel imageLabel = new JLabel();
    private final JList<String> armoryList = new JList<>(new String[]{"Sword", "Shield"});
    private final ImageIcon swordImage = new ImageIcon(getClass().getResource("/Sword.jpg"));
    private final ImageIcon shieldImage = new ImageIcon(getClass().getResource("/Shield.jpg"));
    private final ImageIcon defaultImage = new ImageIcon(getClass().getResource("/Default.jpg"));
    private Map<String, ImageIcon> armoryImages = new HashMap<>();
    private String selectedArmory = "";
    private GameGUI gameGUI;
    private Player player;




    public ChooseWeaponGUI(GameGUI gameGUI, Player player) {

        this.gameGUI = gameGUI;
        this.player = player;
        setLayout(new BorderLayout());


        armoryImages.put("Sword", swordImage);
        armoryImages.put("Shield", shieldImage);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.WEST);

        JPanel namePanel = new JPanel();
        playerNameLabel = new JLabel("Player: " + player.getName());


        namePanel.add(playerNameLabel);


        topPanel.add(namePanel, BorderLayout.CENTER);



        topPanel.add(nextButton, BorderLayout.EAST);

        armoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        armoryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selection = armoryList.getSelectedValue();
                updateArmoryImage(selection);
                selectedArmory = selection;
                confirmButton.setEnabled(true);
            }
        });

        JScrollPane listScrollPane = new JScrollPane(armoryList);
        listScrollPane.setBorder(new TitledBorder("Armory List"));
        listScrollPane.setPreferredSize(new Dimension(150, 200));

        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        updateArmoryImage("Armory");

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, imageLabel);
        splitPane.setDividerLocation(150);
        splitPane.setResizeWeight(0.0);

        confirmButton.setEnabled(false);
        armorySelectionArea.setEditable(false);
        JScrollPane armorySelectionScrollPane = new JScrollPane(armorySelectionArea);
        armorySelectionScrollPane.setBorder(new TitledBorder("Game Info"));
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(confirmButton, BorderLayout.SOUTH);
        bottomPanel.add(armorySelectionScrollPane, BorderLayout.CENTER);

        confirmButton.addActionListener(e -> {
            selectWeapon(selectedArmory);
            nextButton.setEnabled(true);
        });

        backButton.addActionListener(e -> gameGUI.displayChooseCharacterPage());

        nextButton.addActionListener(e -> {
            if (!selectedArmory.isEmpty() && !playerNameLabel.getText().trim().isEmpty()) {
                gameGUI.displayGamePanel();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an armory.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            }
        });

        nextButton.setEnabled(false);

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateArmoryImage(String armoryName) {
        ImageIcon image = armoryImages.getOrDefault(armoryName, defaultImage); // Use defaultImage if armoryName is not found
        imageLabel.setIcon(image);
    }

    private void selectWeapon(String weaponType) {
        Armory armoryItem = null;
        if ("Sword".equals(weaponType)) {
            armoryItem = new Sword(); // Assuming Sword constructor is similar to Shield
            // Assuming the character class has a method to set armory
            player.getCharacter().setArmory(armoryItem);
        } else if ("Shield".equals(weaponType)) {
            armoryItem = new Shield();
            player.getCharacter().setArmory(armoryItem);
        }

        if (armoryItem != null) {
            updateWeaponSelectionArea(armoryItem);
        }
    }


    private void updateWeaponSelectionArea(Armory armoryItem) {
        String characterName = player.getCharacter() != null ? player.getCharacter().getName() : "";
        String playerName = player.getName();
        String selectedWeapon = armoryItem.getName();

        armorySelectionArea.setText(String.format("%s %s has chosen a %s: .\n%s's Attack: %d\n%s's Defense: %d",
                characterName, playerName,  selectedWeapon,
                selectedWeapon, armoryItem.getAttack(), selectedWeapon, armoryItem.getDefence()));
    }
}