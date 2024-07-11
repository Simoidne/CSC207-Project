package CalenderPackage;

import java.time.LocalDateTime;

public class Event {
    private String id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String courseId;

    public Event(String id, String name, LocalDateTime startTime, LocalDateTime endTime, String courseId) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseId = courseId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getCourseId() {
        return courseId;
    }
}


