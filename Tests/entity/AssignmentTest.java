package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AssignmentTest {

    @Test
    void getId() {
        Assignment assignment = new Assignment("A1", "Assignment 1", LocalDateTime.now(), "C1");
        assertEquals("A1", assignment.getId());
    }

    @Test
    void getName() {
        Assignment assignment = new Assignment("A1", "Assignment 1", LocalDateTime.now(), "C1");
        assertEquals("Assignment 1", assignment.getName());
    }

    @Test
    void getDueDate() {
        LocalDateTime dueDate = LocalDateTime.now();
        Assignment assignment = new Assignment("A1", "Assignment 1", dueDate, "C1");
        assertEquals(dueDate, assignment.getDueDate());
    }

    @Test
    void getCourseId() {
        Assignment assignment = new Assignment("A1", "Assignment 1", LocalDateTime.now(), "C1");
        assertEquals("C1", assignment.getCourseId());
    }
}
