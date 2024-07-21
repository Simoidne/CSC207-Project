package CalenderPackage;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Provides an example demonstrating the usage of Calendar, WeeklyPlanner,
 * Assignment, Event, and ICSConverter classes.
 *
 * This example creates sample courses, assignments, and events, builds a,
 * weekly schedule, and then exports the data to an .ics file.
 */
public class Main {
    public static void main(String[] args) {
        //create sample courses
        Course course1 = new Course("1", "Math 101");
        Course course2 = new Course("2", "History 101");

        //create sample assignments
        Assignment assignment1 = new Assignment("1", "Math Homework",
                LocalDateTime.of(
                        2024,
                        7,
                        5,
                        23,
                        59),
                "1");
        Assignment assignment2 = new Assignment("2",
                "History Essay",
                LocalDateTime.of(
                        2024,
                        7,
                        4,
                        23,
                        59),
                "2");

        //create sample events
        Event event1 = new Event("1", "Math Lecture", LocalDateTime.of(2024, 7, 3, 10, 0), LocalDateTime.of(2024, 7, 3, 11, 0), "1");
        Event event2 = new Event("2", "History Lecture", LocalDateTime.of(2024, 7, 2, 10, 0), LocalDateTime.of(2024, 7, 2, 11, 0), "2");

        //create a weekly planner with sample data
        WeeklyPlanner weeklyPlanner = new WeeklyPlanner(
                LocalDateTime.of(2024, 7, 1, 0, 0),
                LocalDateTime.of(2024, 7, 7, 23, 59),
                List.of(course1, course2),
                List.of(assignment1, assignment2),
                List.of(event1, event2)
        );

        //create a calendar and add the week
        Calendar calendar = new Calendar();
        calendar.addWeek(weeklyPlanner);

        //Display eth calendar
        calendar.displayCalendar();

        //Convert the calendar to .ics format and save to a file
        ICSConverter.convertToICS(calendar, "calendar.ics");
    }
}