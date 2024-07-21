package src.main.java.interface_adaptor;

import src.main.java.interface_adaptor.ICSFileHandler;
import src.main.java.use_case.Calendar;
import src.main.java.entity.PlanItem;
import src.main.java.use_case.WeeklyPlanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ICSConverter {

    private static final DateTimeFormatter ICS_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

    public static void convertToICS(Calendar calendar, String filePath) {
        StringBuilder icsContent = new StringBuilder();

        if (Files.exists(Paths.get(filePath))) {
            try {
                icsContent.append(ICSFileHandler.loadICSFile(filePath));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            icsContent.append("BEGIN:VCALENDAR\n");
            icsContent.append("VERSION:2.0\n");
            icsContent.append("PRODID:-//Your Organization//Your Product//EN\n");
        }

        List<WeeklyPlanner> weeks = calendar.getWeeks();
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
            e.printStackTrace();
        }
    }
}
