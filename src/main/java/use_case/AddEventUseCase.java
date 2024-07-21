package use_case;

import entity.Calendar;
import interface_adaptor.ICSConverter;
import entity.Event;
import interface_adaptor.ICSFileHandler;
import entity.WeeklyPlanner;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddEventUseCase {
    private static final Logger logger = Logger.getLogger(AddEventUseCase.class.getName());

    public static void main(String[] args) {
        // Step 1: Get the project root directory
        String projectPath = System.getProperty("user.dir");
        logger.info("Project path: " + projectPath);

        // Step 2: Specify the file path for the new ICS file within the project root directory
        String filePath = projectPath + File.separator + "sample2.ics";
        logger.info("File path: " + filePath);

        // Step 3: Load existing calendar from ICS file (if it exists)
        Calendar calendar = new Calendar();
        try {
            String icsContent = ICSFileHandler.loadICSFile(filePath);
            // Assuming a method to parse ICS content and populate calendar exists
            // parseICSContentToCalendar(icsContent, calendar);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load ICS file", e);
        }

        // Step 4: Add a new event
        Event newEvent = new Event("Workshop", "Workshop", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2), "CS102");
        if (calendar.getWeeks().isEmpty()) {
            // Create a new weekly planner and add it to the calendar if there are no weeks
            WeeklyPlanner weeklyPlanner = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            weeklyPlanner.getEvents().add(newEvent);
            calendar.addWeek(weeklyPlanner);
        } else {
            // Add the event to the existing weekly planner
            calendar.getWeeks().get(0).getEvents().add(newEvent);
        }

        // Step 5: Save the updated calendar to the new ICS file
        ICSConverter.convertToICS(calendar, filePath);
        logger.info("Updated ICS file saved as " + filePath);

        // Step 6: Display the updated calendar
        logger.info("Calendar after adding event:");
        calendar.displayCalendar();
    }
}
