package use_case;

import java.io.*;
import java.util.Scanner;

public class ClearAllEventsInICS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "myCalendar.ics"; // Replace with your actual file path

        // 1. Confirm with User
        System.out.print("Are you sure you want to clear ALL events? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Action cancelled.");
            return; // Exit if the user doesn't confirm
        }

        // 2. Read Header (and store in a StringBuilder)
        StringBuilder headerContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("BEGIN:VEVENT")) {
                    break; // Stop reading when the first event is found
                }
                headerContent.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: ICS file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Error reading ICS file: " + e.getMessage());
            return;
        }

        // 3. & 4. Overwrite File with Header and Footer
        try (FileWriter fileWriter = new FileWriter(filePath, false)) { // Overwrite mode
            fileWriter.write(headerContent.toString()); // Write stored header content
            fileWriter.write("END:VCALENDAR\n");        // Write the standard ICS footer
            System.out.println("All events have been cleared from the calendar.");
        } catch (IOException e) {
            System.err.println("Error writing to ICS file: " + e.getMessage());
        }
    }
}