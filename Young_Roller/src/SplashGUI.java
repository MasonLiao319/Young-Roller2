import java.awt.*;
import javax.swing.*;

public class SplashGUI extends JPanel {
    JButton nextButton = new JButton("Choose your career");

    // Constructor accepts a GameGUI instance
    SplashGUI(GameGUI gameGUI) {
        setLayout(new BorderLayout());

        // Load the image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Splash1.jpg"));

        JLabel imageLabel = new JLabel(imageIcon);

        // Add image label to the cente
        add(imageLabel, BorderLayout.CENTER);
        // Add welcome text (optional: above the image or somewhere else)
        add(new JLabel("Welcome to the Game!", SwingConstants.CENTER), BorderLayout.NORTH);

        // Add next button
        add(nextButton, BorderLayout.SOUTH);

        // Add ActionListener to the button
        nextButton.addActionListener(e -> gameGUI.displayChooseCharacterPage());
    }
}
