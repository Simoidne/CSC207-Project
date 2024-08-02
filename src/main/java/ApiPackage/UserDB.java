package ApiPackage;

import entity.Course;

import java.util.List;

public interface UserDB {
    Course getCourse(String courseId);
    List<Course> getCourses();
    //TODO add setAPIToken
}