package src.Entities;

import java.time.LocalDateTime;

/**
 * The Assignment class is responsible for a quercus assignment with an id,
 * its name, due date and the course id.
 */
public class Assignment {
    private String id;
    private String name;
    private LocalDateTime dueDate;
    private String courseId;
    private int weight;

    /**
     * Constructs new Assignment object with:
     *
     * @param id          The unique identifier of the assignment.
     * @param name        The name of the assignment.
     * @param dueDate     The due date and time of the assignment.
     * @param courseId    The ID of the course that the assignment belongs to.
     */
    public Assignment(String id,
                      String name,
                      LocalDateTime dueDate,
                      String courseId) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.courseId = courseId;
        this.weight = 0;
    }

    /**
     * Returns the unique identifier of the assignment.
     *
     * @return The Id of the assignment.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the name of the assignment.
     *
     * @return The name of the assignment.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the due date and time of the assignment.
     *
     * @return The due date and time of the format @code LocalDateTime object.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**Returns the ID of the course that the assignment belongs to
     *
     * @return The course ID.
     */
    public String getCourseId() {
        return courseId;
    }
}

