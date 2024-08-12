package ApiPackage;

import entity.Course;

import java.util.List;

public interface UserDB {
    Course getCourse(String courseId) throws NoAPITokenException;
    List<Course> getCourses() throws NoAPITokenException;
    void setAPIToken(String apiToken);
    boolean hasAPIToken();
    RawSyllabus getSyllabus(String courseId) throws SyllabusNotFoundException;
}