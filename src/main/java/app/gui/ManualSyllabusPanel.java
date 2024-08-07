package app.gui;

import ApiPackage.RawSyllabus;
import app.CalendarController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManualSyllabusPanel extends JPanel {
    private JTextArea syllabusTextArea;
    private JButton submitButton;
    private CalendarController controller;
    private String courseId;

    public ManualSyllabusPanel(CalendarController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Instructions Label
        JLabel instructionsLabel = new JLabel("Syllabus Not Found. Please paste the syllabus text below:");
        add(instructionsLabel, BorderLayout.NORTH);

        // Syllabus Text Area (with Scrolling)
        syllabusTextArea = new JTextArea(15, 40);
        syllabusTextArea.setLineWrap(true); // Wrap lines
        syllabusTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(syllabusTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Submit Button
        submitButton = new JButton("Submit Syllabus");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        add(submitButton, BorderLayout.SOUTH);
    }

    // Method to handle syllabus submission
    private void handleSubmit() {
        String syllabusText = syllabusTextArea.getText();

        if (syllabusText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the syllabus text.");
            return;
        }

        // Create RawSyllabus and pass to the controller
        RawSyllabus rawSyllabus = new RawSyllabus("text", syllabusText, courseId, true);
        controller.processManualSyllabus(rawSyllabus);
    }

    // Method to set the associated course ID before displaying the panel
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}