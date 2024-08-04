package app;

import entity.Course;
import app.gui.APITokenWindow;

import java.util.List;

public class CalendarController {
    private String apiToken;

    private APITokenWindow apiTokenWindow;
//    private CourseListWindow courseListWindow;
//    private ManualSyllabusWindow manualSyllabusWindow;
//    private AssignmentViewWindow assignmentViewWindow;
//    private DownloadWindow downloadWindow;

    // ... (Add variables for other windows as needed)

    public CalendarController() {
        apiTokenWindow = new APITokenWindow(this);
        apiTokenWindow.setVisible(true);
    }

    public void setAPIToken(String apiToken) {
        this.apiToken = apiToken;
        // ... (Store API token if needed - environment variable, config file)

//        // Fetch and process courses (replace with your implementation)
//        List<Course> courses = fetchAndProcessCourses();

        apiTokenWindow.dispose();

//        courseListWindow = new CourseListWindow(this, courses);
//        courseListWindow.setVisible(true);
    }

//    // Placeholder - replace with your actual course fetching and processing
//    private List<Course> fetchAndProcessCourses() {
//        // ... (Use your QuercusDB and logic to get and process courses)
//        // For this example, I'll just return an empty list
//        return new ArrayList<>();
//    }
//
//    public void courseSelected(Course selectedCourse) {
//        if (selectedCourse.isSyllabusFound()) {
//            // ... (Fetch/generate assignments and show AssignmentViewWindow)
//            List<Assignment> assignments = generateAssignments(selectedCourse);
//            courseListWindow.dispose();
//            assignmentViewWindow = new AssignmentViewWindow(this, assignments);
//            assignmentViewWindow.setVisible(true);
//        } else {
//            // ... (Show ManualSyllabusWindow to get syllabus input)
//            courseListWindow.dispose();
//            manualSyllabusWindow = new ManualSyllabusWindow(this, selectedCourse);
//            manualSyllabusWindow.setVisible(true);
//        }
//    }

    // ... (Other methods for handling events from other windows)
}