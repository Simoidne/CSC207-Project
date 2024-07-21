package src.Use_Case_Interactor;

import java.time.Duration;
import java.time.LocalDateTime;

public class PlanItem {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String type; // "Assignment", "Event", or "Study Session"

    public PlanItem(String title, LocalDateTime startTime, LocalDateTime endTime, String type) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getType() {
        return type;
    }

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}


