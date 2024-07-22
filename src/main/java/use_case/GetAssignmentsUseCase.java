package use_case;

import ApiPackage.ChatbotAPI;
import ApiPackage.UserDB;
import ApiPackage.GeminiAPI;
import ApiPackage.SyllabusConversion;
import entity.Assignment;
import entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GetAssignmentsUseCase {
    public List<Assignment> getAssignments(UserDB userDB, Course course) {
        List<Assignment> assignments = new ArrayList<>();
        ChatbotAPI chatbotAPI = new GeminiAPI();
    }
}
