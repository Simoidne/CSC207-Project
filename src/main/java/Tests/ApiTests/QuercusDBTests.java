package Tests.ApiTests;

// need to use mockito for these tests

import ApiPackage.NoAPITokenException;
import ApiPackage.QuercusDB;
import ApiPackage.UserDB;
import entity.Course;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuercusDBTests {
    UserDB userDB;

    @BeforeEach
    public void setUp() {
        userDB = new QuercusDB();
        userDB.setAPIToken(null);
    }

    @DisplayName("Test if getCourse will throw NoAPITokenException when there is no API token given")
    @Test
    public void testGetCourseMethod_throwsNoAPITokenException() {
        Assert.assertThrows(NoAPITokenException.class, () -> userDB.getCourse("345741"));
    }

    @DisplayName("Test if getCourses will throw NoAPITokenException when there is no API token given")
    @Test
    public void testGetCoursesMethod_throwsNoAPITokenException() {
        Assert.assertThrows(NoAPITokenException.class, () -> userDB.getCourses());
    }

    @DisplayName("Test if getCourse will return a course with a syllabus")
    @Test
    public void testGetCourseMethod_usingCSC207_returnsCourseWithSyllabus() throws NoAPITokenException {
        String QUERCUS_TOKEN = System.getenv("QUERCUS_TOKEN");
        userDB.setAPIToken(QUERCUS_TOKEN);
        Course course = userDB.getCourse("345741");
        Assertions.assertFalse(course.getAssignments().isEmpty());
    }
}
