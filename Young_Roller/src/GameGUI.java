import java.awt.*;
import javax.swing.*;
import java.util.Stack;

public class GameGUI {
    protected JFrame frame;
    private SplashGUI splashPage;
    private ChooseCharacterGUI chooseCharacterPage;
    private ChooseWeaponGUI chooseWeaponPage;
    private GamePanelGUI gamePanel;
    private Player player;
    private Stack<JPanel> panelHistory; // Stack to keep track of panel history

    public GameGUI() {
        frame = new JFrame("Young_Roller. Author: Mason Liao");
        player = new Player(); // Initialize the Player object
        panelHistory = new Stack<>();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(880, 760));

        initializeSplashPage(); // Initialize and display the splash screen initially

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeSplashPage() {
        if (splashPage == null) {
            splashPage = new SplashGUI(this);
        }
        displayPanel(splashPage);
    }

    public void displayChooseCharacterPage() {
        if (chooseCharacterPage == null) {
            chooseCharacterPage = new ChooseCharacterGUI(this, player);
        }
        displayPanel(chooseCharacterPage);
    }

    public void displayChooseWeaponPage() {
        if (chooseWeaponPage == null) {
            chooseWeaponPage = new ChooseWeaponGUI(this, player);
        }
        displayPanel(chooseWeaponPage);
    }

    public void displayGamePanel() {
        if (gamePanel == null) {
            gamePanel = new GamePanelGUI(this, player);
        }
        displayPanel(gamePanel);
    }

    public void displayPanel(JPanel panel) {
        // Save the current panel to the stack before removing it
        if (frame.getContentPane().getComponentCount() > 0) {
            panelHistory.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.repaint();
        frame.validate();
    }

    public void goBack() {
        if (!panelHistory.isEmpty()) {
            JPanel previousPanel = panelHistory.pop();
            displayPanel(previousPanel);
        }
    }

    // ... Other methods ...
}
