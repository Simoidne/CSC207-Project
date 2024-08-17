package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class WeeklyPlannerTest {

    @Test
    void getWeekStartDate() {
        LocalDateTime startDate = LocalDateTime.of(2024, 8, 19, 0, 0);
        WeeklyPlanner planner = new WeeklyPlanner(startDate, startDate.plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertEquals(startDate, planner.getWeekStartDate());
    }

    @Test
    void getWeekEndDate() {
        LocalDateTime endDate = LocalDateTime.of(2024, 8, 26, 0, 0);
        WeeklyPlanner planner = new WeeklyPlanner(endDate.minusDays(7), endDate, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertEquals(endDate, planner.getWeekEndDate());
    }

    @Test
    void getCourses() {
        WeeklyPlanner planner = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertNotNull(planner.getCourses());
    }

    @Test
    void getAssignments() {
        WeeklyPlanner planner = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertNotNull(planner.getAssignments());
    }

    @Test
    void getEvents() {
        WeeklyPlanner planner = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertNotNull(planner.getEvents());
    }

    @Test
    void generateWeeklyPlan() {
        LocalDateTime weekStart = LocalDateTime.of(2024, 8, 19, 0, 0);
        LocalDateTime weekEnd = LocalDateTime.of(2024, 8, 25, 23, 59);

        List<Course> courses = new ArrayList<>();
        List<Assignment> assignments = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        Assignment assignment = new Assignment("A1", "Assignment 1", LocalDateTime.of(2024, 8, 20, 23, 59), "C1");
        assignments.add(assignment);

        Event event = new Event("E1", "Exam", LocalDateTime.of(2024, 8, 21, 9, 0), LocalDateTime.of(2024, 8, 21, 12, 0), "csc207");
        events.add(event);

        WeeklyPlanner planner = new WeeklyPlanner(weekStart, weekEnd, courses, assignments, events);
        List<PlanItem> planItems = planner.generateWeeklyPlan();

        assertEquals(2, planItems.size());
        assertTrue(planItems.stream().anyMatch(item -> item.getTitle().equals("Assignment 1")));
        assertTrue(planItems.stream().anyMatch(item -> item.getTitle().equals("Exam")));
    }

    @Test
    void deleteEvent() {
        LocalDateTime weekStart = LocalDateTime.of(2024, 8, 19, 0, 0);
        LocalDateTime weekEnd = LocalDateTime.of(2024, 8, 25, 23, 59);

        List<Course> courses = new ArrayList<>();
        List<Assignment> assignments = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        Event event = new Event("E1", "Exam", LocalDateTime.of(2024, 8, 21, 9, 0), LocalDateTime.of(2024, 8, 21, 12, 0), "csc207");
        events.add(event);

        WeeklyPlanner planner = new WeeklyPlanner(weekStart, weekEnd, courses, assignments, events);
        assertTrue(planner.deleteEvent("E1"));
        assertEquals(0, planner.getEvents().size());
    }
}
