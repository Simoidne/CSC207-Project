package app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import app.CalendarController;

public class APITokenWindow extends JFrame {
    private JTextField apiTokenField;
    private JButton submitButton;
    private CalendarController controller; // Reference to your controller

    public static void main(String[] args) {
        // Run the APITokenWindow creation on the EDT
        SwingUtilities.invokeLater(() -> {
            CalendarController controller = new CalendarController(); // Create controller instance
            new APITokenWindow(controller).setVisible(true);
        });
    }

    public APITokenWindow(CalendarController controller) {
        this.controller = controller; // Inject the controller
        setTitle("Enter Quercus API Token");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // API Token Input
        JLabel label = new JLabel("API Token:");
        apiTokenField = new JTextField(30); // Adjust size as needed
        add(label);
        add(apiTokenField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        add(submitButton);

        pack(); // Automatically size the window
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void handleSubmit() {
        String apiToken = apiTokenField.getText();
        // Validation (optional - add more checks if necessary)
        if (apiToken.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an API token.");
            return;
        }

        // Pass the token to the controller
        controller.setAPIToken(apiToken);
    }
}