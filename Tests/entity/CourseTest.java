package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class CourseTest {

    @Test
    void getId() {
        Course course = new Course("C1", "Software Engineering");
        assertEquals("C1", course.getId());
    }

    @Test
    void getName() {
        Course course = new Course("C1", "Software Engineering");
        assertEquals("Software Engineering", course.getName());
    }

    @Test
    void getAssignments() {
        Course course = new Course("C1", "Software Engineering");
        assertNotNull(course.getAssignments());
    }

    @Test
    void isSyllabusFound() {
        Course course = new Course("C1", "Software Engineering");
        assertFalse(course.isSyllabusFound());
    }

    @Test
    void addAssignment() {
        Course course = new Course("C1", "Software Engineering");
        Assignment assignment = new Assignment("A1", "Assignment 1", LocalDateTime.now(), "C1");
        course.addAssignment(assignment);
        assertTrue(course.getAssignments().contains(assignment));
    }

    @Test
    void setAssignments() {
        Course course = new Course("C1", "Software Engineering");
        List<Assignment> assignments = new ArrayList<>();
        course.setAssignments(assignments);
        assertEquals(assignments, course.getAssignments());
    }

    @Test
    void setSyllabusFound() {
        Course course = new Course("C1", "Software Engineering");
        course.setSyllabusFound(true);
        assertTrue(course.isSyllabusFound());
    }
}
