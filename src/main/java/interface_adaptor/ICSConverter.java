package src.main.java.interface_adaptor;

import src.main.java.use_case.Calendar;
import src.main.java.entity.PlanItem;
import src.main.java.use_case.WeeklyPlanner;
import src.main.java.entity.Event;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ICSConverter {
    private static final Logger logger = Logger.getLogger(ICSConverter.class.getName());
    private static final DateTimeFormatter ICS_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

    public static void convertToICS(Calendar calendar, String filePath) {
        StringBuilder icsContent = new StringBuilder();

        if (Files.exists(Paths.get(filePath))) {
            try {
                icsContent.append(ICSFileHandler.loadICSFile(filePath));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to load existing ICS file", e);
                return;
            }
        } else {
            icsContent.append("BEGIN:VCALENDAR\n");
            icsContent.append("VERSION:2.0\n");
            icsContent.append("PRODID:-//Your Organization//Your Product//EN\n");
        }

        List<WeeklyPlanner> weeks;
        weeks = calendar.getWeeks();
        for (WeeklyPlanner week : weeks) {
            List<PlanItem> planItems = week.generateWeeklyPlan();
            for (PlanItem item : planItems) {
                ICSFileHandler.appendPlanItem(icsContent, item, ICS_DATE_FORMATTER);
            }
        }

        icsContent.append("END:VCALENDAR");

        try {
            ICSFileHandler.saveICSFile(filePath, icsContent.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save ICS file", e);
        }
    }
    public static Calendar convertICSToCalendar(String icsContent) {
        Calendar calendar = new Calendar();
        List<Event> events = new ArrayList<>();
        WeeklyPlanner weeklyPlanner;

        String[] lines = icsContent.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.startsWith("BEGIN:VEVENT")) {
                String title = "";
                String start = "";
                String end = "";
                String uid = "";
                String courseId = "CS101"; // Assuming default course ID for simplicity

                while (!(line = lines[++i].trim()).startsWith("END:VEVENT")) {
                    if (line.startsWith("SUMMARY:")) {
                        title = line.substring(8);
                    } else if (line.startsWith("DTSTART:")) {
                        start = line.substring(8);
                    } else if (line.startsWith("DTEND:")) {
                        end = line.substring(6);
                    } else if (line.startsWith("UID:")) {
                        uid = line.substring(4);
                    }
                }

                // Assuming a method to convert date strings to LocalDateTime exists
                Event event = new Event(uid, title, parseDate(start), parseDate(end), courseId);
                events.add(event);
            }
        }

        if (!events.isEmpty()) {
            LocalDateTime weekStart = events.getFirst().getStartTime();
            LocalDateTime weekEnd = weekStart.plusDays(7);
            weeklyPlanner = new WeeklyPlanner(weekStart, weekEnd, new ArrayList<>(), new ArrayList<>(), events);
            calendar.addWeek(weeklyPlanner);
        }

        return calendar;
    }

    private static LocalDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        return LocalDateTime.parse(dateString, formatter);
    }
}

