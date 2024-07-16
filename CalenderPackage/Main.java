package CalenderPackage;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Course course1 = new Course("1", "Math 101");
        Course course2 = new Course("2", "History 101");

        Assignment assignment1 = new Assignment("1", "Math Homework", LocalDateTime.of(2024, 7, 5, 23, 59), "1");
        Assignment assignment2 = new Assignment("2", "History Essay", LocalDateTime.of(2024, 7, 4, 23, 59), "2");

        Event event1 = new Event("1", "Math Lecture", LocalDateTime.of(2024, 7, 3, 10, 0), LocalDateTime.of(2024, 7, 3, 11, 0), "1");
        Event event2 = new Event("2", "History Lecture", LocalDateTime.of(2024, 7, 2, 10, 0), LocalDateTime.of(2024, 7, 2, 11, 0), "2");

        WeeklyPlanner weeklyPlanner = new WeeklyPlanner(
                LocalDateTime.of(2024, 7, 1, 0, 0),
                LocalDateTime.of(2024, 7, 7, 23, 59),
                List.of(course1, course2),
                List.of(assignment1, assignment2),
                List.of(event1, event2)
        );

        Calendar calendar = new Calendar();
        calendar.addWeek(weeklyPlanner);
        calendar.displayCalendar();

        ICSConverter.convertToICS(calendar, "calendar.ics");
    }
}