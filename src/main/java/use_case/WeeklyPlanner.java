package src.main.java.use_case;

import src.main.java.entity.PlanItem;
import src.main.java.entity.Assignment;
import src.main.java.entity.Course;
import src.main.java.entity.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a weekly schedule which contains courses, assignments, and events
 * for a week.
 *
 * Provides functionality to generate a  list of plan items ({@link  PlanItem}).
 */
public class WeeklyPlanner {
    private LocalDateTime weekStartDate;
    private LocalDateTime weekEndDate;
    private List<Course> courses;
    private List<Assignment> assignments;
    private List<Event> events;

    /**
     * Constructs a new WeeklyPlanner object.
     *
     * @param weekStartDate The start date and time of the week.
     * @param weekEndDate The end date and time of the week.
     * @param courses The list of courses for this week.
     * @param assignments The list of assignments due this week.
     * @param events The list of events scheduled for this week.
     */
    public WeeklyPlanner(LocalDateTime weekStartDate,
                         LocalDateTime weekEndDate,
                         List<Course> courses,
                         List<Assignment> assignments,
                         List<Event> events) {
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.courses = courses;
        this.assignments = assignments;
        this.events = events;
    }

    /**
     * Returns teh starting date and time of the week.
     *
     * @return The week's start date and time.
     */
    public LocalDateTime getWeekStartDate() {
        return weekStartDate;
    }

    /**
     * Returns the ending date and time of the week.
     *
     * @return The week's end date and time.
     */
    public LocalDateTime getWeekEndDate() {
        return weekEndDate;
    }

    /**
     * Generates a list of (@link PlanItem) objects representing the assignments
     * and events scheduled for the week.
     *
     * @return A list of PlanItem objects for the week.
     */
    public List<PlanItem> generateWeeklyPlan() {
        List<PlanItem> planItems = new ArrayList<>();
        for (Assignment assignment : assignments) {
            if (assignment.getDueDate().isAfter(weekStartDate) && assignment.getDueDate().isBefore(weekEndDate)) {
                planItems.add(new PlanItem(assignment.getName(), assignment.getDueDate().minusDays(1), assignment.getDueDate(), "Assignment"));
            }
        }
        for (Event event : events) {
            if (event.getStartTime().isAfter(weekStartDate) && event.getEndTime().isBefore(weekEndDate)) {
                planItems.add(new PlanItem(event.getName(), event.getStartTime(), event.getEndTime(), "Event"));
            }
        }
        return planItems;
    }
}

