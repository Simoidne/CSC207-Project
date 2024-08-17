package use_case;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.PlanItem;

public class ICSFileHandler {
    private static final Logger logger = Logger.getLogger(ICSFileHandler.class.getName());

    public static String loadICSFile(String filePath) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load ICS file: " + filePath, e);
            throw e;
        }
    }

    public static void saveICSFile(String filePath, String content) throws IOException {
        try {
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save ICS file: " + filePath, e);
            throw e;
        }
    }

    public static void appendPlanItem(StringBuilder icsContent, PlanItem item, DateTimeFormatter formatter) {
        icsContent.append("BEGIN:VEVENT\n");
        icsContent.append("UID:").append(item.getTitle().hashCode()).append("@yourdomain.com\n");
        icsContent.append("SUMMARY:").append(item.getTitle()).append("\n");
        icsContent.append("DTSTART:").append(item.getStartTime().format(formatter)).append("Z\n");
        icsContent.append("DTEND:").append(item.getEndTime().format(formatter)).append("Z\n");
        icsContent.append("DESCRIPTION:").append(item.getType()).append("\n");
        icsContent.append("END:VEVENT\n");
    }
}

