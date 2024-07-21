package src.main.java.use_case;

import src.main.java.entity.Assignment;
import src.main.java.entity.Course;
import src.main.java.api.UserDB;

import java.util.List;

public final class GetCourseAssignmentsUseCase {
    private final UserDB userDB;

    public GetCourseAssignmentsUseCase(UserDB userDB) {
        this.userDB = userDB;
    }

    /**
     * Obtain a list of assignments for a given course ID.
     *
     * @param courseId The ID of the course for which to retrieve assignments.
     * @return A list of Assignment objects associated with the given course ID,
     *         or an empty list if no assignments are found.
     */
    public List<Assignment> getCourseAssignments(String courseId) {
        Course course = userDB.getCourse(courseId);
        if (course != null) {
            return course.getAssignments();
        } else {
            // Handle case where course is not found
            return List.of();
        }
    }
}