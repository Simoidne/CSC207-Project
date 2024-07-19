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

        //Get the JSONObject from the API call using the prompt
        JSONObject responseBody = chatbotDB.getResponse(prompt);

        return this.fromResponseToAssignments(responseBody, syllabus.courseId, chatbotDB);
    }

    private List<Assignment> fromResponseToAssignments(JSONObject responseBody,
                                                       String courseId,
                                                       ChatbotDB chatbotDB) throws JSONException {

        List<Assignment> assignments = new ArrayList<>();

        //Ask chatbotDB to parse the response JSONObject into a usable response in string format
        String assignmentsString = chatbotDB.parseResponse(responseBody);

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
