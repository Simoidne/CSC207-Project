package src.main.java.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in quercus with an ID, name and list of assignments.
 */
public class Course {
    private String id;
    private String name;
    private List<Assignment> assignments;

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
}
