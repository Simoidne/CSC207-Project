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

        listModel = new DefaultListModel<>();
        courseList = new JList<>(listModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add double-click listener
        courseList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = courseList.locationToIndex(e.getPoint());
                    Course selectedCourse = listModel.getElementAt(index);

                    if (selectedCourse.isSyllabusFound()) {
                        controller.showAssignmentViewPanel(selectedCourse.getId());
                    } else {
                        controller.showManualSyllabusPanel(selectedCourse.getId());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(courseList);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateCourseList(List<Course> courses) {
        listModel.clear();
        for (Course course : courses) {
            String displayText = course.getName();
            if (course.isSyllabusFound()) {
                displayText += " (Syllabus Available)";
            } else {
                displayText += " (Syllabus Unavailable)";
            }
            listModel.addElement(course);
        }
    }
}