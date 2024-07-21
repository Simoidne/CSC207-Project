package src.Entities;

import src.Use_Case_Interactor.PlanItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<WeeklyPlanner> weeks;

    public Calendar() {
        this.weeks = new ArrayList<>();
    }

    public void addWeek(WeeklyPlanner week) {
        weeks.add(week);
    }

    public void removeWeek(LocalDateTime weekStartDate) {
        weeks.removeIf(week -> week.getWeekStartDate().equals(weekStartDate));
    }

    public List<WeeklyPlanner> getWeeks() {
        return weeks;
    }

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
