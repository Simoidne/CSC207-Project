package ApiPackage;

import CalenderPackage.Course;

import java.util.List;

public interface UserDB {
    Course getCourse(String courseId);
    List<Course> getCourses();
}