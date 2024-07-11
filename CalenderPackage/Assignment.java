package CalenderPackage;

import java.time.LocalDateTime;

public class Assignment {
    private String id;
    private String name;
    private LocalDateTime dueDate;
    private String courseId;

    public Assignment(String id, String name, LocalDateTime dueDate, String courseId) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.courseId = courseId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getCourseId() {
        return courseId;
    }
}

