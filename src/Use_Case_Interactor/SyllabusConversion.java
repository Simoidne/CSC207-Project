package src.Use_Case_Interactor;

import src.Entities.Assignment;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.Entities.RawSyllabus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SyllabusConversion {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public List<Assignment> getAssignments(RawSyllabus syllabus) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        String content = String.format("The following content is the syllabus of a course formatted as a %s: %s. Please only return all assignments numbered in the order that they are due along with the due date, in LocalTimeDate format (yyyy-MM-ddThh:mm:ss), in the following JSONArray format:\n[{\"name\": \"<Assignment1 Name>\", \"order\": \"<Order>\", \"dueDate\": \"<Due Date>\"},\n{\"name\": \"<Assignment2 Name>\", \"order\": \"<Order>\", \"dueDate\": \"<Due Date>\"},\n...]",
                                        syllabus.dataFormat,
                                        syllabus.rawSyllabusData);
        content = String.format("[{\"parts\": [{\"text\": %s}]}]", content);

        JSONObject requestBody = new JSONObject(String.format("{\"contents\": \"%s\"}", content));
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());

        Request request = new Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent")
                .method("POST", body)
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                return this.fromJSONtoAssignments(responseBody, syllabus.courseId);
            } else {
                throw new RuntimeException(response.message());
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
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
