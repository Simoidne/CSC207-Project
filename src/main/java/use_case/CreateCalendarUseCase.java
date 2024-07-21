package use_case;

import entity.Calendar;
import interface_adaptor.ICSConverter;
import entity.Assignment;
import src.main.java.entity.Course;
import entity.Event;
import entity.WeeklyPlanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateCalendarUseCase {
    private static final Logger logger = Logger.getLogger(CreateCalendarUseCase.class.getName());

    public static void main(String[] args) {
        // Step 1: Get the project root directory
        String projectPath = System.getProperty("user.dir");

        // Step 2: Specify the file path for the ICS file within the project root directory
        String filePath = projectPath + File.separator + "sample1.ics";
        logger.info("File path: " + filePath);

        // Step 3: Create a course
        Course course = new Course("CS101", "Computer Science 101");

        // Step 4: Create an assignment
        Assignment assignment = new Assignment("A1", "Assignment 1", LocalDateTime.now().plusDays(1), "CS101");

        // Step 5: Add the assignment to the course
        course.addAssignment(assignment);

        // Step 6: Create a list of courses and add the course to the list
        List<Course> courses = new ArrayList<>();
        courses.add(course);

        // Step 7: Create a list of assignments and add the assignment to the list
        List<Assignment> assignments = new ArrayList<>();
        assignments.add(assignment);

        // Step 8: Create an event
        Event event = new Event("Lecture", "Lecture", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), "CS101");

        // Step 9: Create a list of events and add the event to the list
        List<Event> events = new ArrayList<>();
        events.add(event);

        // Step 10: Create a weekly planner and add the lists of courses, assignments, and events
        WeeklyPlanner weeklyPlanner = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), courses, assignments, events);

        // Step 11: Create a calendar and add the weekly planner to it
        Calendar calendar = new Calendar();
        calendar.addWeek(weeklyPlanner);

        // Step 12: Convert to ICS file and save initial content
        try (FileWriter writer = new FileWriter(filePath)) {
            StringBuilder icsContent = new StringBuilder();
            icsContent.append("BEGIN:VCALENDAR\n");
            icsContent.append("VERSION:2.0\n");
            icsContent.append("PRODID:-//Your Organization//Your Product//EN\n");
            icsContent.append("BEGIN:VEVENT\n");
            icsContent.append("UID:1@yourdomain.com\n");
            icsContent.append("SUMMARY:Example Event\n");
            icsContent.append("DTSTART:").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"))).append("Z\n");
            icsContent.append("DTEND:").append(LocalDateTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"))).append("Z\n");
            icsContent.append("DESCRIPTION:This is a test event\n");
            icsContent.append("END:VEVENT\n");
            icsContent.append("END:VCALENDAR");
            writer.write(icsContent.toString());
            logger.info("Sample ICS file created and saved as " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to create and save the ICS file", e);
        }
    }
}
