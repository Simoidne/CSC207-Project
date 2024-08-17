package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class CalendarTest {

    @Test
    void addWeek() {
        Calendar calendar = new Calendar();
        WeeklyPlanner week = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        calendar.addWeek(week);
        assertTrue(calendar.getWeeks().contains(week));
    }

    @Test
    void getWeeks() {
        Calendar calendar = new Calendar();
        List<WeeklyPlanner> weeks = calendar.getWeeks();
        assertNotNull(weeks);
    }

}
