package app;

import ApiPackage.QuercusDB;
import ApiPackage.RawSyllabus;
import ApiPackage.SyllabusNotFoundException;
import ApiPackage.UserDB;
import app.gui.*;
import entity.Assignment;
import entity.Course;
import ApiPackage.SyllabusConverter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalendarController {
    private UserDB userDB;
    private String apiToken;
    private APITokenWindow apiTokenWindow;
    private JFrame courseListWindow;
    private JFrame manualSyllabusPanel;
    private JFrame assignmentViewPanel;
    private JFrame downloadPanel;
    public CalendarController(APITokenWindow apiTokenWindow) {
        this.apiTokenWindow = apiTokenWindow;
        this.userDB = new QuercusDB();

        initializeWindows();
    }

    private void initializeWindows() {
        // --- Course List Window ---
        courseListWindow = new JFrame("Course List");
        courseListWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        courseListWindow.setContentPane(new CourseListWindow(this));
        courseListWindow.pack();
        courseListWindow.setLocationRelativeTo(null);
        courseListWindow.setVisible(false); // Initially hidden

        // --- Manual Syllabus Panel ---
        manualSyllabusPanel = new JFrame("Enter Syllabus");
        manualSyllabusPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        manualSyllabusPanel.setContentPane(new ManualSyllabusPanel(this));
        manualSyllabusPanel.pack();
        manualSyllabusPanel.setLocationRelativeTo(null);
        manualSyllabusPanel.setVisible(false); // Initially hidden

        // --- Assignment View Panel ---
        assignmentViewPanel = new JFrame("View Assignments");
        assignmentViewPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        assignmentViewPanel.setContentPane(new AssignmentViewPanel(this));
        assignmentViewPanel.pack();
        assignmentViewPanel.setLocationRelativeTo(null);
        assignmentViewPanel.setVisible(false); // Initially hidden

        // --- Download Panel ---
        downloadPanel = new JFrame("Download Calendar");
        downloadPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        downloadPanel.setContentPane(new DownloadPanel(this));
        downloadPanel.pack();
        downloadPanel.setLocationRelativeTo(null);
        downloadPanel.setVisible(false); // Initially hidden
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
            showCourseListPanel(courses);

        } catch (Exception ex) {
            System.err.println("Error fetching courses: " + ex.getMessage());
            // Add more robust error handling (e.g., dialog box)
        }
    }


    // Processes the manually entered syllabus
    public void processManualSyllabus(RawSyllabus rawSyllabus) {
        try {
            SyllabusConverter syllabusConverter = new SyllabusConverter();
            List<Assignment> assignments = syllabusConverter.getAssignments(rawSyllabus);

            // Correct way to access and update AssignmentViewPanel from CalendarController:
            ((AssignmentViewPanel) assignmentViewPanel.getContentPane()).updateAssignments(assignments);

            assignmentViewPanel.setVisible(true);

        } catch (SyllabusNotFoundException e) {
            System.err.println("Error processing syllabus: " + e.getMessage());
        }
    }


    public void showCourseListPanel(List<Course> courses) {
        courseListWindow.setTitle("Course List");
        ((CourseListWindow) courseListWindow.getContentPane()).updateCourseList(courses);
        courseListWindow.setVisible(true);
    }

    // Shows the ManualSyllabusPanel for manual syllabus input
    public void showManualSyllabusPanel(String courseId) {
        manualSyllabusPanel.setTitle("Enter Syllabus for Course ID: " + courseId);
        ((ManualSyllabusPanel) manualSyllabusPanel.getContentPane()).setCourseId(courseId);
        manualSyllabusPanel.setVisible(true);
    }

    public void showAssignmentViewPanel(String courseId) {
        try {
            RawSyllabus syllabus = userDB.getSyllabus(courseId);
            SyllabusConverter syllabusConverter = new SyllabusConverter();
            List<Assignment> assignments = syllabusConverter.getAssignments(syllabus);

            assignmentViewPanel.setTitle("Assignments for Course ID: " + courseId);
            ((AssignmentViewPanel) assignmentViewPanel.getContentPane()).updateAssignments(assignments);
            assignmentViewPanel.setVisible(true);

        } catch (SyllabusNotFoundException e) {
            System.err.println("Error fetching or processing syllabus: " + e.getMessage());
        }
    }

    // Placeholder method to show the DownloadPanel (Window 5)
    public void showDownloadPanel() {
        downloadPanel.setVisible(true);
    }
}