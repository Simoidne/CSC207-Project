package ApiPackage;

import entity.Assignment;
import ApiPackage.RawSyllabus;
import ApiPackage.SyllabusNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


// TODO probably the only fix to this problem is to change the prompt to give the content in a different format
public class SyllabusConverter {
    public List<Assignment> getAssignments(RawSyllabus syllabus) throws SyllabusNotFoundException {
        //If there is no syllabus then throw SyllabusNotFoundException
        if (!syllabus.syllabusFound) {
            throw new SyllabusNotFoundException();
        }

        //Creating the prompt
        String prompt = String.format("The following content is the syllabus of a course formatted as %s: %s. Please only return all assignments numbered in the order that they are due along with the due date, in LocalTimeDate format (yyyy-MM-ddThh:mm:ss), in the following JSONArray format:\n[{\"name\": \"<Assignment1 Name>\", \"order\": \"<Order>\", \"dueDate\": \"<Due Date>\"},\n{\"name\": \"<Assignment2 Name>\", \"order\": \"<Order>\", \"dueDate\": \"<Due Date>\"},\n...]\n Do not provide any explanation or any other labels under any circumstance. Make sure that all values in the JSON are strings.",
                syllabus.dataFormat,
                syllabus.rawSyllabusData);

        ChatbotAPI chatbotAPI = new GeminiAPI();

        //Get the JSONObject from the API call using the prompt
        JSONObject responseBody = chatbotAPI.getResponse(prompt);

        return fromResponseToAssignments(responseBody, syllabus.courseId, chatbotAPI);
    }

    private List<Assignment> fromResponseToAssignments(JSONObject responseBody,
                                                       String courseId,
                                                       ChatbotAPI chatbotAPI)
            throws JSONException, SyllabusNotFoundException {

        List<Assignment> assignments = new ArrayList<>();

        //Ask chatbotDB to parse the response JSONObject into a usable response in string format
        String assignmentsString = chatbotAPI.parseResponse(responseBody);

        // Print BEFORE cleaning:
        System.out.println("assignmentsString (BEFORE cleaning):\n" + assignmentsString);

        // Clean the response
        assignmentsString = this.cleanStringIntoJSONArrayString(assignmentsString).trim();

        // Print AFTER cleaning:
        System.out.println("\nassignmentsString (AFTER cleaning):\n" + assignmentsString);

        JSONArray assignmentsArray;

        if (!assignmentsString.isEmpty()) {
            assignmentsArray = new JSONArray(assignmentsString);
        } else {
            throw new SyllabusNotFoundException();
        }

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

    private String cleanStringIntoJSONArrayString(String stringJSONArray) {
        int startIndex = 0;
        boolean inJSONArray = false;
        int endIndex = -1;

        for(int i = 0; i < stringJSONArray.length(); i++){
            if (stringJSONArray.charAt(i) == '[' && !inJSONArray) {
                startIndex = i;
            } else if (stringJSONArray.charAt(i) == ']') {
                endIndex = i;
            }
        }

        return stringJSONArray.substring(startIndex, endIndex + 1);
    }
}
