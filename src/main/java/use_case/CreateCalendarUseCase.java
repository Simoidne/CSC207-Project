package use_case;

import entity.Calendar;
import entity.WeeklyPlanner;

public class CreateCalendarUseCase {

    public Calendar createCalendar(WeeklyPlanner weeklyPlanner) {
        if (weeklyPlanner == null) {
            throw new IllegalArgumentException("WeeklyPlanner cannot be null.");
        }

        Calendar calendar = new Calendar();

        if (weeklyPlanner.getWeekStartDate() == null || weeklyPlanner.getWeekEndDate() == null) {
            throw new IllegalStateException("WeeklyPlanner must have valid start and end dates.");
        }

        calendar.addWeek(weeklyPlanner);

        return calendar;
    }
}
