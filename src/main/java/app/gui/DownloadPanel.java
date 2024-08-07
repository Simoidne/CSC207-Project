package app.gui;

import interface_adaptor.ICSConverter; // Import your ICSConverter class
import app.CalendarController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloadPanel extends JPanel {
    private JButton downloadButton;
    private CalendarController controller;

    public DownloadPanel(CalendarController controller) {
        this.controller = controller;
        setLayout(new FlowLayout()); // Or any layout you prefer

        JLabel messageLabel = new JLabel("Your calendar is ready! Click below to download:");
        add(messageLabel);

        downloadButton = new JButton("Download Calendar (.ics)");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDownload();
            }
        });
        add(downloadButton);
    }

    private void handleDownload() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Calendar File");
        fileChooser.setSelectedFile(new File("calendar.ics"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Copy the already saved file to the user's chosen location:
            try {
                // Use Files.copy() to copy "calendar.ics" to the selected location
                Files.copy(Paths.get("calendar.ics"), fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

                JOptionPane.showMessageDialog(this, "Calendar saved to: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                // Handle potential exceptions during file copy
                JOptionPane.showMessageDialog(this, "Error saving calendar file: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Download cancelled.");
        }
    }
}