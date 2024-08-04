package use_case;

import entity.Calendar;
import entity.WeeklyPlanner;

public class CreateCalendarUseCase {

    public Calendar createCalendar(WeeklyPlanner weeklyPlanner) {
        Calendar calendar = new Calendar();
        calendar.addWeek(weeklyPlanner);
        return calendar;
    }
}
