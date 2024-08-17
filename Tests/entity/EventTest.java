package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class EventTest {

    @Test
    void getId() {
        Event event = new Event("E1", "Exam", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "csc207");
        assertEquals("E1", event.getId());
    }

    @Test
    void getName() {
        Event event = new Event("E1", "Exam", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "csc207");
        assertEquals("Exam", event.getName());
    }

    @Test
    void getStartTime() {
        LocalDateTime startTime = LocalDateTime.now();
        Event event = new Event("E1", "Exam", startTime, startTime.plusHours(2), "csc207");
        assertEquals(startTime, event.getStartTime());
    }

    @Test
    void getEndTime() {
        LocalDateTime endTime = LocalDateTime.now().plusHours(2);
        Event event = new Event("E1", "Exam", LocalDateTime.now(), endTime, "csc207");
        assertEquals(endTime, event.getEndTime());
    }

    @Test
    void getCourseId() {
        Event event = new Event("E1", "Exam", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "csc207");
        assertEquals("csc207", event.getCourseId());
    }
}
