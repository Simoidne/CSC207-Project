package src.main.java.api;

import src.main.java.entity.Course;

import java.util.List;

public interface UserDB {
    Course getCourse(String courseId);
    List<Course> getCourses();
}