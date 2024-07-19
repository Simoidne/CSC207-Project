package ApiPackage;

import CalenderPackage.Assignment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SyllabusConversion {
    public List<Assignment> getAssignments(RawSyllabus syllabus) {

        //Creating the prompt
        String prompt = String.format("The following content is the syllabus of a course formatted as a %s: %s. Please only return all assignments numbered in the order that they are due along with the due date, in LocalTimeDate format (yyyy-MM-ddThh:mm:ss), in the following JSONArray format:\n[{\"name\": \"<Assignment1 Name>\", \"order\": \"<Order>\", \"dueDate\": \"<Due Date>\"},\n{\"name\": \"<Assignment2 Name>\", \"order\": \"<Order>\", \"dueDate\": \"<Due Date>\"},\n...]",
                syllabus.dataFormat,
                syllabus.rawSyllabusData);

        ChatbotDB chatbotDB = new GeminiDB();

        //Get the JSONObject from the API call
        JSONObject responseBody = chatbotDB.getResponse(prompt);

        return this.fromJSONtoAssignments(responseBody, syllabus.courseId);
    }

    private List<Assignment> fromJSONtoAssignments(JSONObject responseBody, String courseId) throws JSONException {
        List<Assignment> assignments = new ArrayList<>();
        String assignmentsString = responseBody.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");

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
