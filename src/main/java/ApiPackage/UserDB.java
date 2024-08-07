package ApiPackage;

import entity.Course;

import java.util.List;

public interface UserDB {
    Course getCourse(String courseId) throws NoAPITokenException;
    List<Course> getCourses();
    void setAPIToken(String apiToken);
    RawSyllabus getSyllabus(String courseId) throws SyllabusNotFoundException;
}