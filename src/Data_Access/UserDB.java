package src.Data_Access;

import src.Entities.Course;

import java.util.List;

public interface UserDB {
    Course getCourse(String courseId);
    List<Course> getCourses();
}