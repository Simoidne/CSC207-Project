package app.gui;

import ApiPackage.QuercusDB;
import ApiPackage.RawSyllabus;
import ApiPackage.SyllabusNotFoundException;
import ApiPackage.UserDB;
import entity.Course;
import app.CalendarController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class APITokenWindow extends JFrame {
    private JTextField apiTokenField;
    private JButton submitButton;
    private CalendarController controller;
    private CourseListWindow courseListWindow;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(APITokenWindow::new);
    }

    public APITokenWindow() {
        // Initialize controller
        this.controller = new CalendarController(this); // Pass 'this' (APITokenWindow)

        // Window setup
        setTitle("Enter Quercus API Token");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // API Token Input
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("API Token:");
        apiTokenField = new JTextField(30);
        inputPanel.add(label);
        inputPanel.add(apiTokenField);
        add(inputPanel, BorderLayout.NORTH);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        add(submitButton, BorderLayout.SOUTH);

        // Initialize CourseListWindow (hidden initially)
        courseListWindow = new CourseListWindow(controller);
        courseListWindow.setVisible(false);
        add(courseListWindow, BorderLayout.CENTER);

        // Final window setup
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleSubmit() {
        String apiToken = apiTokenField.getText();
        if (apiToken.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an API token.");
            return;
        }
        controller.setAPIToken(apiToken);
    }

    public void showCourseListPanel(List<Course> courses) {
        courseListWindow.updateCourseList(courses);

        // Transition to CourseListWindow
        this.setVisible(false);
        courseListWindow.setVisible(true);
    }
}