package src.Entities;

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
     * @param id The unique identifier for the course.
     * @param name The name of the course.
     */
    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the course.
     *
     * @return ID of the course.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the course
     *
     * @return The course name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of assignments that belongs to the course.
     *
     * @return  A list of {@link Assignment} objects.
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Set the list of assignments for this course.
     *
     * @param assignments The list of assignments to be associated.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
