import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanelGUI extends JPanel {
    private JTextArea textArea;
    private JLabel playerInfoLabel, opponentInfoLabel, playerNameLabel, opponentNameLabel;
    private JButton rollDiceButton;
    private GameGUI gameGUI;
    private Player player;
    private Opponent opponent;

    // Constructor that accepts a GameGUI reference
    GamePanelGUI(GameGUI gameGUI, Player player) {
        this.gameGUI = gameGUI;
        this.player = player;
        this.opponent = new Dragon(); // Substitute with the actual opponent retrieval method.

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(1, 7, 10, 10)); // Including Exit button
        topPanel.add(createButton("BACK"));
        topPanel.add(createButton("RESTART"));
        topPanel.add(createButton("INVENTORY"));
        topPanel.add(createButton("SHOP"));
        topPanel.add(createButton("NEXT LEVEL"));
        topPanel.add(createButton("EXIT"));

        // Center panel with player and opponent info
        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JPanel playerPanel = createPlayerPanel();
        JPanel opponentPanel = createOpponentPanel();

        // VS label
        JLabel vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.add(playerPanel);
        mainPanel.add(vsLabel);
        mainPanel.add(opponentPanel);

        // Bottom panel with text area and roll button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        rollDiceButton = new JButton("ROLL DICE");
        rollDiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rollDiceAction();
            }
        });
        bottomPanel.add(rollDiceButton, BorderLayout.NORTH);
        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Game Process"));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Layout setup
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        // Add action listeners here
        return button;
    }

    private JPanel createPlayerPanel() {
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerNameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        playerInfoLabel = new JLabel("Player HP: " + player.getHealthPoints(), SwingConstants.CENTER);

        // Load the player image (assuming player.getCharacterImagePath() returns a valid path)
        ImageIcon playerImage = new ImageIcon(getClass().getResource(player.getCharacterImagePath()));
        JLabel playerImageLabel = new JLabel(playerImage);

        // Add components to the player panel
        playerPanel.add(playerNameLabel, BorderLayout.PAGE_END);
        playerPanel.add(playerInfoLabel, BorderLayout.PAGE_START);
        playerPanel.add(playerImageLabel, BorderLayout.CENTER);

        return playerPanel;
    }

    private JPanel createOpponentPanel() {
        JPanel opponentPanel = new JPanel(new BorderLayout());
        opponentNameLabel = new JLabel(opponent.getName(), SwingConstants.CENTER);
        opponentInfoLabel = new JLabel("Opponent HP: " + opponent.getHealthPoints(), SwingConstants.CENTER);


        ImageIcon opponentImage = new ImageIcon(getClass().getResource("/chinese-dragon1.jpg"));
        JLabel opponentImageLabel = new JLabel(opponentImage);

        // Add components to the opponent panel
        opponentPanel.add(opponentNameLabel, BorderLayout.PAGE_END);
        opponentPanel.add(opponentInfoLabel, BorderLayout.PAGE_START);
        opponentPanel.add(opponentImageLabel, BorderLayout.CENTER);

        return opponentPanel;
    }

    protected void rollDiceAction() {
        int playerRoll = rollDice();
        int opponentRoll = rollDice();

        updateGameText("Player rolls a dice: " + playerRoll);
        updateGameText("Opponent rolls a dice: " + opponentRoll);

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerRoll > opponentRoll) {
                    int damage = player.attack(opponent);
                    updateGameText(player.getCharacter().getName() + " " +player.getName() + " attacks and deals " + damage + " actual damage to Opponent!");
                } else if (opponentRoll > playerRoll) {
                    int damage = opponent.attack(player);
                    updateGameText("Opponent attacks and deals " + damage + " actual damage to Player!");
                } else {
                    updateGameText("The dice roll is a tie. No damage dealt.");
                }

                updateGameStatus();

                if (!player.isAlive() || !opponent.isAlive()) {
                    endGame();
                }
                ((Timer) e.getSource()).stop();
            }
        }).start();
    }

    private int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    private void updateGameText(String text) {
        textArea.append(text + "\n");
    }

    private void updateGameStatus() {
        playerInfoLabel.setText("Player HP: " + player.getHealthPoints());
        opponentInfoLabel.setText("Opponent HP: " + opponent.getHealthPoints());
    }

    private void endGame() {
        rollDiceButton.setEnabled(false);
        if (!player.isAlive()) {
            updateGameText("Player has been defeated! Opponent wins!");
        } else if (!opponent.isAlive()) {
            updateGameText("Opponent has been defeated! Player wins!");
        } else {
            updateGameText("The game has ended unexpectedly.");
        }

    }

}
