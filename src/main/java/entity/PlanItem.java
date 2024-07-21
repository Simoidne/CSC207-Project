package src.main.java.entity;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents a generic item to be planned within a calendar.
 */
public class PlanItem {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String type; // "Assignment", "Event", or "Study Session"

    /**
     * Constructs a new PlanItem.
     *
     * @param title The title of the plan item.
     * @param startTime The starting date and time of the plan item.
     * @param endTime The ending date and time of the plan item.
     * @param type The type of the plan item.
     */
    public PlanItem(String title,
                    LocalDateTime startTime,
                    LocalDateTime endTime,
                    String type) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    /**
     * Returns the title of the plan item
     *
     * @return The title of the plan item.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the starting date and time of the plan item.
     *
     * @return The start time of the plan item.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the ending date and time of the plan item.
     *
     * @return The end time of the plan item.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns the type of the plan item.
     *
     * @return The type of the plan item (e.g., "Assignment", "Event").
     */
    public String getType() {
        return type;
    }

    /**
     * Calculates and returns the duration of the plan item.
     *
     * @return The duration of the plan item, calculated as the difference
     *         between the end time and start time.
     */
    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}


