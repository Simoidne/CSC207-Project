package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import entity.PlanItem;
import entity.WeeklyPlanner;

/**
 * Represents a calendar that contains multiple weeks of plans.
 */
public class Calendar {
    private List<WeeklyPlanner> weeks;

    public Calendar() {
        this.weeks = new ArrayList<>();
    }

    public void addWeek(WeeklyPlanner week) {
        this.weeks.add(week);
    }


    public List<WeeklyPlanner> getWeeks() {
        return java.util.Collections.unmodifiableList(weeks);
    }

    public void displayCalendar() {
        for (WeeklyPlanner week : weeks) {
            System.out.println("Week Start Date: " + week.getWeekStartDate());
            List<PlanItem> planItems = week.generateWeeklyPlan();
            for (PlanItem item : planItems) {
                System.out.println(item.getTitle() + ": " + item.getStartTime() + " - " + item.getEndTime());
            }
        }
    }

    // Add method to delete an event by title across all weeks
    public boolean deleteEvent(String title) {
        boolean deleted = false;
        for (WeeklyPlanner week : weeks) {
            if (week.deleteEvent(title)) {
                deleted = true;
            }
        }
        return deleted;
    }
}

