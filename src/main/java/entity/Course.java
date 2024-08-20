package entity;

import entity.Assignment;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in quercus with an ID, name and list of assignments.
 */
public class Course {
    private String id;
    private String name;
    private List<Assignment> assignments;
    private boolean syllabusFound;

    /**
     * Constructs a new Course object.
     *
     * @param id    The unique identifier for the course.
     * @param name  The name of the course.
     */
    public Course(String id, String name) {
        this.id = id;
        this.name = name;
        this.assignments = new ArrayList<>();
        this.syllabusFound = false;
    }

    /**
     * Returns the unique identifier of the course.
     *
     * @return The course ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the course.
     *
     * @return The course name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of assignments for the course.
     *
     * @return The list of assignments.
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    public boolean isSyllabusFound() {
        return syllabusFound;
    }

    /**
     * Adds an assignment to the course.
     *
     * @param assignment The assignment to be added.
     */
    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setSyllabusFound(boolean syllabusFound) {
        this.syllabusFound = syllabusFound;
    }

    @Override
    public String toString() {
        return this.name;  // Or any other string representation you want
    }
}
