package ApiPackage;

import entity.Assignment;
import ApiPackage.RawSyllabus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SyllabusConverter {
    public List<Assignment> getAssignments(RawSyllabus syllabus) throws SyllabusNotFoundException {
        //If there is no syllabus then throw SyllabusNotFoundException
        if (!syllabus.syllabusFound) {
            throw new SyllabusNotFoundException();
        }

        //Creating the prompt
        String prompt = String.format("The following content is the syllabus of a course formatted as %s: %s. Please only return all assignments numbered in the order that they are due along with the due date, in LocalTimeDate format (yyyy-MM-ddThh:mm:ss), in the following JSONArray format:\n[{\"name\": \"<Assignment1 Name>\", \"order\": \"<Order>\", \"dueDate\": \"<Due Date>\"},\n{\"name\": \"<Assignment2 Name>\", \"order\": \"<Order>\", \"dueDate\": \"<Due Date>\"},\n...]",
                syllabus.dataFormat,
                syllabus.rawSyllabusData);

        ChatbotAPI chatbotAPI = new GeminiAPI();

        //Get the JSONObject from the API call using the prompt
        JSONObject responseBody = chatbotAPI.getResponse(prompt);

        return this.fromResponseToAssignments(responseBody, syllabus.courseId, chatbotAPI);
    }

    private List<Assignment> fromResponseToAssignments(JSONObject responseBody,
                                                       String courseId,
                                                       ChatbotAPI chatbotAPI) throws JSONException {

        List<Assignment> assignments = new ArrayList<>();

        //Ask chatbotDB to parse the response JSONObject into a usable response in string format
        String assignmentsString = chatbotAPI.parseResponse(responseBody);

        //Create a JSONObject called assignmentArray using the chatbot response string
        JSONArray assignmentsArray = new JSONArray(assignmentsString);
        for (int i = 0; i < assignmentsArray.length(); i++) {
            JSONObject assignmentJSON = assignmentsArray.getJSONObject(i);
            Assignment assignment = new Assignment(assignmentJSON.getString("order"),
                    assignmentJSON.getString("name"),
                    LocalDateTime.parse(assignmentJSON.getString("dueDate")),
                    courseId);
            assignments.add(assignment);
        }
        return assignments;
    }
}
