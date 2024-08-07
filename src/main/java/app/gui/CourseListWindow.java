package app.gui;

import entity.Course;
import app.CalendarController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CourseListWindow extends JPanel {
    private JList<Course> courseList;
    private DefaultListModel<Course> listModel;
    private CalendarController controller; // Add controller reference

    public CourseListWindow(CalendarController controller) {
        this.controller = controller; // Initialize controller
        setLayout(new BorderLayout());

        // create the model
        listModel = new DefaultListModel<>();

        // create the JList and sets its model to listModel
        courseList = new JList<>(listModel);

        // functionality of only selecting single course at one time
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add double-click listener
        courseList.addMouseListener(new MouseAdapter() {
            @Override
            // called when the mouse is clicked
            public void mouseClicked(MouseEvent e) {
                // check if it is a double click
                if (e.getClickCount() == 2) {
                    // get the index of the clicked course
                    int index = courseList.locationToIndex(e.getPoint());
                    // retrieve the course object at the clicked index
                    Course selectedCourse = listModel.getElementAt(index);
                    // selected course has condition: syllabus available
                    if (selectedCourse.isSyllabusFound()) {
                        controller.showAssignmentViewPanel(selectedCourse.getId());
                    }
                    // selected course has condition: syllabus not available
                    else {
                        controller.showManualSyllabusPanel(selectedCourse.getId());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(courseList);
        add(scrollPane, BorderLayout.CENTER);
    }

    // method to update the course list in JList
    public void updateCourseList(List<Course> courses) {
        //clear existing courses
        listModel.clear();

        // iterate through the course list
        for (Course course : courses) {
            String displayText = course.getName();
            if (course.isSyllabusFound()) {
                displayText += " (Syllabus Available)";
            } else {
                displayText += " (Syllabus Unavailable)";
            }

            // add the course display string to the model
            listModel.addElement(course);
        }
    }
}