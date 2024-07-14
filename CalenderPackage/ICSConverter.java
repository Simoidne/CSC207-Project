package CalenderPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ICSConverter {

    private static final DateTimeFormatter ICS_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

    public static void convertToICS(Calendar calendar, String filePath) {
        StringBuilder icsContent = new StringBuilder();
        icsContent.append("BEGIN:VCALENDAR\n");
        icsContent.append("VERSION:2.0\n");
        icsContent.append("PRODID:-//Your Organization//Your Product//EN\n");

        List<WeeklyPlanner> weeks = calendar.getWeeks();
        for (WeeklyPlanner week : weeks) {
            List<PlanItem> planItems = week.generateWeeklyPlan();
            for (PlanItem item : planItems) {
                icsContent.append("BEGIN:VEVENT\n");
                icsContent.append("UID:").append(item.getTitle().hashCode()).append("@yourdomain.com\n");
                icsContent.append("SUMMARY:").append(item.getTitle()).append("\n");
                icsContent.append("DTSTART:").append(item.getStartTime().format(ICS_DATE_FORMATTER)).append("Z\n");
                icsContent.append("DTEND:").append(item.getEndTime().format(ICS_DATE_FORMATTER)).append("Z\n");
                icsContent.append("DESCRIPTION:").append(item.getType()).append("\n");
                icsContent.append("END:VEVENT\n");
            }
        }

        icsContent.append("END:VCALENDAR");

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(icsContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
