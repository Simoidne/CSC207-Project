package src.main.java.main_component;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ICSConverter converter = new ICSConverter();
        ICSCalendarManager calendarManager = new ICSCalendarManager(converter, "planItems.dat");
        Scanner scanner = new Scanner(System.in);

        // Load existing data if available
        try {
            calendarManager.load();
            System.out.println("Data loaded from planItems.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing data found. Starting fresh.");
        }

        boolean running = true;
        while (running) {
            System.out.println("Enter command (add/show/save/exit): ");
            String command = scanner.nextLine();
            switch (command) {
                case "add":
                    // Example of adding a PlanItem
                    System.out.println("Enter item details...");
                    // add logic to capture details and create a PlanItem
                    PlanItem item = new PlanItem(); // create with actual details
                    converter.addPlanItem(item);
                    try {
                        calendarManager.save();
                        System.out.println("Data saved to planItems.dat");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;
                case "show":
                    System.out.println("Plan Items:");
                    for (PlanItem planItem : converter.getPlanItems()) {
                        System.out.println(planItem);
                    }
                    break;
                case "save":
                    try {
                        calendarManager.save();
                        System.out.println("Data saved to planItems.dat");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }

        // Save data before exiting
        try {
            calendarManager.save();
            System.out.println("Data saved to planItems.dat");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }

        scanner.close();
    }
}
