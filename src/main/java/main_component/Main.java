package main_component;

import interface_adaptor.ICSFileHandler;
import entity.Assignment;
import src.main.java.entity.Course;
import entity.Event;
import interface_adaptor.ICSConverter;
import entity.Calendar;
import entity.WeeklyPlanner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create initial courses, assignments, events, and add them to the calendar
        Course course = new Course("CS101", "Computer Science 101");
        Assignment assignment = new Assignment("A1", "Assignment 1", LocalDateTime.now().plusDays(1), "CS101");
        Event event = new Event("Lecture", "Lecture", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(1), "CS101");

        course.addAssignment(assignment);

        List<Course> courses = new ArrayList<>();
        courses.add(course);

        List<Assignment> assignments = new ArrayList<>();
        assignments.add(assignment);

        List<Event> events = new ArrayList<>();
        events.add(event);

        WeeklyPlanner weeklyPlanner = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), courses, assignments, events);
        Calendar calendar = new Calendar();
        calendar.addWeek(weeklyPlanner);

        // File path for the ICS file
        String filePath = "path/to/your/file.ics";

        // Step 2: Convert to ICS file and save initial content
        ICSConverter.convertToICS(calendar, filePath);
        System.out.println("Initial ICS file created and saved.");

        // Step 3: Load existing ICS file content using ICSFileHandler
        try {
            String icsContent = ICSFileHandler.loadICSFile(filePath);
            System.out.println("Loaded ICS File Content:");
            System.out.println(icsContent);
        } catch (IOException e) {
            System.out.println("No existing ICS file found, creating a new file.");
        }

        // Step 4: Make changes to the calendar (e.g., add a new event)
        Event newEvent = new Event("Seminar", "Seminar", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(1), "CS101");
        events.add(newEvent);

        WeeklyPlanner updatedWeeklyPlanner = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), courses, assignments, events);
        calendar = new Calendar();
        calendar.addWeek(updatedWeeklyPlanner);

        // Step 5: Convert to ICS file and save updated content
        ICSConverter.convertToICS(calendar, "calendar.ics");
        System.out.println("Updated ICS file content saved.");
    }
}

