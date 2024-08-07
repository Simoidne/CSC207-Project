package app;

import ApiPackage.QuercusDB;
import ApiPackage.RawSyllabus;
import ApiPackage.SyllabusNotFoundException;
import ApiPackage.UserDB;
import app.gui.*;
import entity.Assignment;
import entity.Course;
import ApiPackage.SyllabusConverter; // Make sure to import SyllabusConverter

import java.awt.*;
import java.util.List;

public class CalendarController {
    private UserDB userDB;
    private String apiToken;
    private APITokenWindow apiTokenWindow;
    private CourseListWindow courseListWindow;
    private ManualSyllabusPanel manualSyllabusPanel;
    private AssignmentViewPanel assignmentViewPanel;
    private DownloadPanel downloadPanel;

    public CalendarController(APITokenWindow apiTokenWindow) {
        this.apiTokenWindow = apiTokenWindow;
        this.userDB = new QuercusDB();

        // Initialize and hide panels
        courseListWindow = new CourseListWindow(this);
        courseListWindow.setVisible(false);
        apiTokenWindow.add(courseListWindow, BorderLayout.CENTER);

        manualSyllabusPanel = new ManualSyllabusPanel(this);
        manualSyllabusPanel.setVisible(false);
        apiTokenWindow.add(manualSyllabusPanel, BorderLayout.CENTER);

        assignmentViewPanel = new AssignmentViewPanel(this);
        assignmentViewPanel.setVisible(false);
        apiTokenWindow.add(assignmentViewPanel, BorderLayout.CENTER);

        downloadPanel = new DownloadPanel(this);
        downloadPanel.setVisible(false);
        apiTokenWindow.add(downloadPanel, BorderLayout.CENTER);
    }

    // Sets the API token, fetches courses, and updates the CourseListWindow
    public void setAPIToken(String apiToken) {
        this.apiToken = apiToken;
        this.userDB.setAPIToken(apiToken);

        try {
            // Fetch courses from QuercusDB
            List<Course> courses = userDB.getCourses();

            // Process each course to check if the syllabus is available
            for (Course course : courses) {
                try {
                    RawSyllabus syllabus = userDB.getSyllabus(course.getId());
                    course.setSyllabusFound(true);
                } catch (SyllabusNotFoundException e) {
                    course.setSyllabusFound(false);
                }
            }
            // Update the CourseListWindow and make it visible
            apiTokenWindow.showCourseListPanel(courses);

        } catch (Exception ex) {
            System.err.println("Error fetching courses: " + ex.getMessage());
            // Add more robust error handling (e.g., dialog box)
        }
    }

    // Processes the manually entered syllabus
    public void processManualSyllabus(RawSyllabus rawSyllabus) {
        try {
            // Extract assignments using SyllabusConverter
            SyllabusConverter syllabusConverter = new SyllabusConverter();
            List<Assignment> assignments = syllabusConverter.getAssignments(rawSyllabus);

            // Update the AssignmentViewPanel with the extracted assignments
            assignmentViewPanel.updateAssignments(assignments);

            // Show the AssignmentViewPanel and hide the ManualSyllabusPanel
            manualSyllabusPanel.setVisible(false);
            assignmentViewPanel.setVisible(true);

        } catch (SyllabusNotFoundException e) {
            System.err.println("Error processing syllabus: " + e.getMessage());
            // Handle the exception appropriately
        }
    }

    // Shows the ManualSyllabusPanel for manual syllabus input
    public void showManualSyllabusPanel(String courseId) {
        manualSyllabusPanel.setCourseId(courseId); // Set the course ID for the panel

        // Hide the CourseListWindow and show the ManualSyllabusPanel
        courseListWindow.setVisible(false);
        manualSyllabusPanel.setVisible(true);
    }

    // Shows the AssignmentViewPanel (called from CourseListWindow)
    public void showAssignmentViewPanel(String courseId) {
        try {
            // Fetch the syllabus from QuercusDB
            RawSyllabus syllabus = userDB.getSyllabus(courseId);

            // Extract assignments from the syllabus using SyllabusConverter
            SyllabusConverter syllabusConverter = new SyllabusConverter();
            List<Assignment> assignments = syllabusConverter.getAssignments(syllabus);

            // Optional: Update the corresponding Course object with assignments
            // ... (Logic to find and update the Course object if needed)

            // Update the AssignmentViewPanel with extracted assignments
            assignmentViewPanel.updateAssignments(assignments);

            // Hide the CourseListWindow and show the AssignmentViewPanel
            courseListWindow.setVisible(false);
            assignmentViewPanel.setVisible(true);

        } catch (SyllabusNotFoundException e) {
            // Handle the exception (this might not be necessary if the syllabus is guaranteed to exist)
            System.err.println("Error fetching or processing syllabus: " + e.getMessage());
        }
    }

    // Placeholder method to show the DownloadPanel (Window 5)
    public void showDownloadPanel() {
        assignmentViewPanel.setVisible(false);
        downloadPanel.setVisible(true);
    }
}