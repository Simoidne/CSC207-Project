package app;

import ApiPackage.QuercusDB;
import ApiPackage.RawSyllabus;
import ApiPackage.SyllabusNotFoundException;
import ApiPackage.UserDB;
import app.gui.CourseListWindow;
import app.gui.ManualSyllabusPanel;
import entity.Course;
import app.gui.APITokenWindow;

import java.awt.*;
import java.util.List;

public class CalendarController {
    private UserDB userDB;
    private String apiToken;
    private APITokenWindow apiTokenWindow; // Reference to APITokenWindow
    private CourseListWindow courseListWindow;
    private ManualSyllabusPanel manualSyllabusPanel;


    public CalendarController(APITokenWindow apiTokenWindow) {
        this.apiTokenWindow = apiTokenWindow;
        this.userDB = new QuercusDB();

        courseListWindow = new CourseListWindow(this);
        courseListWindow.setVisible(false);
        apiTokenWindow.add(courseListWindow, BorderLayout.CENTER);

        manualSyllabusPanel = new ManualSyllabusPanel(this);
        manualSyllabusPanel.setVisible(false);
        apiTokenWindow.add(manualSyllabusPanel, BorderLayout.CENTER);
    }


    public void setAPIToken(String apiToken) {
        this.apiToken = apiToken;
        this.userDB.setAPIToken(apiToken);

        try {
            List<Course> courses = userDB.getCourses();

            for (Course course : courses) {
                try {
                    RawSyllabus syllabus = userDB.getSyllabus(course.getId());
                    course.setSyllabusFound(true);
                } catch (SyllabusNotFoundException e) {
                    course.setSyllabusFound(false);
                }
            }

            apiTokenWindow.showCourseListPanel(courses);

        } catch (Exception ex) {
            System.err.println("Error fetching courses: " + ex.getMessage());
            // Improve error handling
        }
    }

    public void processManualSyllabus(RawSyllabus rawSyllabus) {
        //    need to store the rawSyllabus data somewhere
        //    (e.g., in the corresponding Course object, or in a separate data structure).
        //    For now, let's just print it to the console:
        System.out.println("Received Syllabus: " + rawSyllabus.rawSyllabusData);

        // 3.  For now, we'll just stay on the ManualSyllabusPanel.
        //     Later, you'll transition to the AssignmentViewPanel here.
    }

    public void showManualSyllabusPanel(String courseId) {
        manualSyllabusPanel.setCourseId(courseId);

        courseListWindow.setVisible(false);
        manualSyllabusPanel.setVisible(true);
    }
}