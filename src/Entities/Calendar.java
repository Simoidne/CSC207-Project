package src.Entities;

import src.Use_Case_Interactor.PlanItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a calendar that manages multiple weekly schedules.
 * <p>
 * Allows adding and removing weekly planners ({@link WeeklyPlanner})
 * and provides functionality to display the calendar content.
 */
public class Calendar {
    private List<WeeklyPlanner> weeks;

    /**
     * Constructs an empty Calendar with empty list of weeks.
     */
    public Calendar() {
        this.weeks = new ArrayList<>();
    }

    /**
     * Add  weekly planner to this calendar.

     * @param week The weekly Planner to be added.
     */
    public void addWeek(WeeklyPlanner week) {
        weeks.add(week);
    }

    /**
     * Removes a weekly planner
     * @param weekStartDate The starting date and time of the week to be removed
     */
    public void removeWeek(LocalDateTime weekStartDate) {
        weeks.removeIf(week -> week.getWeekStartDate().equals(weekStartDate));
    }

    /**
     * Returns an unmodifiable list of the weekly planners.
     *
     * @return A list of WeeklyPlanner objects.
     */
    public List<WeeklyPlanner> getWeeks() {
        return weeks;
    }

    /**
     * Displays teh calendar content with the start date and the planned items
     */
    public void displayCalendar() {
        for (WeeklyPlanner week : weeks) {
            System.out.println("Week starting: " + week.getWeekStartDate());
            List<PlanItem> planItems = week.generateWeeklyPlan();
            for (PlanItem item : planItems) {
                System.out.println(item.getTitle() + " (" + item.getType() + "): " + item.getStartTime() + " - " + item.getEndTime());
            }
        }
    }
}
