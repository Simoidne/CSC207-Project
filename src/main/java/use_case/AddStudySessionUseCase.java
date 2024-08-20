package use_case;

import use_case.ICSConverter;
import entity.Event;
import use_case.ICSFileHandler;
import entity.Calendar;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddStudySessionUseCase {
    private static final Logger logger = Logger.getLogger(AddStudySessionUseCase.class.getName());

    public static void main(String[] args) {
        //Get the project root directory
        String projectPath = System.getProperty("user.dir");
        logger.info("Project path: " + projectPath);

        //Specify the file path for the ICS file within the project root directory
        String filePath = projectPath + File.separator + "myCalendar.ics"; // Assuming a file named "myCalendar.ics"
        logger.info("File path: " + filePath);

        //Load existing calendar from ICS file
        Calendar calendar = new Calendar();
        try {
            String icsContent = ICSFileHandler.loadICSFile(filePath);
            // Assuming a method to parse ICS content and populate calendar
            // exists
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load ICS file", e);
            // Handle the case where the file is not found, e.g., create a new
            // calendar
        }
        // 1. User Input (replace with actual input prompts)
        String studySessionTitle = "Study for Math Exam";
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0);
        LocalDateTime endTime = startTime.plusHours(2);
        // 2. Create Study Session Event
        Event studySession = new Event(studySessionTitle, studySessionTitle, startTime, endTime, "Study");

        // 3. Add Event to Calendar (assuming you want to add to the first week)
        if (!calendar.getWeeks().isEmpty()) {
            calendar.getWeeks().getFirst().getEvents().add(studySession);
        }

        // 4. Save Calendar
        ICSConverter.convertToICS(calendar, filePath);
        logger.info("Updated ICS file saved as " + filePath);
    }
}