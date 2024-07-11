package CalenderPackage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeeklyPlanner {
    private LocalDateTime weekStartDate;
    private LocalDateTime weekEndDate;
    private List<Course> courses;
    private List<Assignment> assignments;
    private List<Event> events;

    public WeeklyPlanner(LocalDateTime weekStartDate, LocalDateTime weekEndDate, List<Course> courses, List<Assignment> assignments, List<Event> events) {
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.courses = courses;
        this.assignments = assignments;
        this.events = events;
    }

    public LocalDateTime getWeekStartDate() {
        return weekStartDate;
    }

    public LocalDateTime getWeekEndDate() {
        return weekEndDate;
    }

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

