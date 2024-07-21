package use_case;

import interface_adaptor.ICSConverter;
import interface_adaptor.ICSFileHandler;
import entity.Calendar;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteEventUseCase {
    private static final Logger logger = Logger.getLogger(DeleteEventUseCase.class.getName());

    public static void main(String[] args) {
        // Step 1: Get the project root directory
        String projectPath = System.getProperty("user.dir");
        logger.info("Project path: " + projectPath);

        // Step 2: Specify the file path for the ICS file within the project root directory
        String filePath = projectPath + File.separator + "sample3.ics";
        logger.info("File path: " + filePath);

        // Step 3: Load existing calendar from ICS file
        Calendar calendar = new Calendar();
        try {
            String icsContent = ICSFileHandler.loadICSFile(filePath);
            // Assuming a method to parse ICS content and populate calendar exists
            // parseICSContentToCalendar(icsContent, calendar);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load ICS file", e);
        }

        // Step 4: Display calendar before deletion
        logger.info("Calendar before deletion:");
        calendar.displayCalendar();

        // Step 5: Delete an event and save the updated calendar
        boolean eventDeleted = calendar.deleteEvent("Lecture");
        if (eventDeleted) {
            logger.info("Event 'Lecture' deleted successfully.");

            // Save the updated calendar to the ICS file
            ICSConverter.convertToICS(calendar, filePath);
            logger.info("Updated ICS file saved as " + filePath);
        } else {
            logger.warning("Event 'Lecture' not found.");
        }

        // Step 6: Display calendar after deletion
        logger.info("Calendar after deletion:");
        calendar.displayCalendar();
    }
}
