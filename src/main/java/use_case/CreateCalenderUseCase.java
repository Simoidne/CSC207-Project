package src.main.java.use_case;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateCalenderUseCase {
    public static void main(String[] args) {
        // Get the project root directory
        String projectPath = System.getProperty("user.dir");

        // Ensure the output directory exists
        String outputDirPath = projectPath + File.separator + "output";
        File outputDir = new File(outputDirPath);
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }

        // Specify the file path for the ICS file within the project directory
        String filePath = outputDirPath + File.separator + "sample1.ics";

        // Create ICS content
        StringBuilder icsContent = new StringBuilder();
        icsContent.append("BEGIN:VCALENDAR\n");
        icsContent.append("VERSION:2.0\n");
        icsContent.append("PRODID:-//Your Organization//Your Product//EN\n");

        // Example event
        icsContent.append("BEGIN:VEVENT\n");
        icsContent.append("UID:1@yourdomain.com\n");
        icsContent.append("SUMMARY:Example Event\n");
        icsContent.append("DTSTART:").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"))).append("Z\n");
        icsContent.append("DTEND:").append(LocalDateTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"))).append("Z\n");
        icsContent.append("DESCRIPTION:This is a test event\n");
        icsContent.append("END:VEVENT\n");

        icsContent.append("END:VCALENDAR");

        // Save ICS file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(icsContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Sample ICS file created and saved as " + filePath);
    }
}

