package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PlanItemTest {

    @Test
    void getTitle() {
        PlanItem planItem = new PlanItem("Study Session", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Study");
        assertEquals("Study Session", planItem.getTitle());
    }

    @Test
    void getStartTime() {
        LocalDateTime startTime = LocalDateTime.now();
        PlanItem planItem = new PlanItem("Study Session", startTime, startTime.plusHours(2), "Study");
        assertEquals(startTime, planItem.getStartTime());
    }

    @Test
    void getEndTime() {
        LocalDateTime endTime = LocalDateTime.now().plusHours(2);
        PlanItem planItem = new PlanItem("Study Session", LocalDateTime.now(), endTime, "Study");
        assertEquals(endTime, planItem.getEndTime());
    }

    @Test
    void getType() {
        PlanItem planItem = new PlanItem("Study Session", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Study");
        assertEquals("Study", planItem.getType());
    }

    @Test
    void getDuration() {
        PlanItem planItem = new PlanItem("Study Session", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Study");
        assertEquals(2, planItem.getDuration().toHours());
    }
}
