package ApiPackage;

import CalenderPackage.Course;

public interface UserDB {
    Course getCourse(String courseId);
    Course[] getCourses();
    RawSyllabus getSyllabus(String courseId);
}