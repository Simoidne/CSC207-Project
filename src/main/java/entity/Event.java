package entity;

import java.time.LocalDateTime;

/**
 * Represents a scheduled event with an ID, name, start time, end time and
 * a course Id.
 */
public class Event {
    private String id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String courseId;

    /**
     * Constructs a new Event object.
     * @param id The id for the event.
     * @param name The name of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @param courseId The ID of the course associated with this event.
     */
    public Event(String id, String name, LocalDateTime startTime, LocalDateTime endTime, String courseId) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseId = courseId;
    }

    /**
     * Returns the id of the event.
     *
     * @return The id of the event.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the event.
     *
     * @return The event name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The event's start time as a (@code LocalDateTime) object.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }
    /**
     * Returns the end date and time of the event.
     *
     * @return The event's end time as a (@code LocalDateTime) object.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns the Id of the course associated with this event.
     *
     * @return The course Id.
     */
    public String getCourseId() {
        return courseId;
    }
}


