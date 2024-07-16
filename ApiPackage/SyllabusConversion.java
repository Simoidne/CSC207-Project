package ApiPackage;

import CalenderPackage.Assignment;
import CalenderPackage.Course;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SyllabusConversion {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public List<Assignment> getAssignments(RawSyllabus syllabus) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        String content = String.format("The following content is the syllabus of a course formatted as a %s: %s. Please return all assignments numbered in the order that they are due along with the due date, in LocalTimeDate format, in the following format: <Assignment Name> <Order> <Due Date>",
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

            if (responseBody.getInt("status_code") == 200) {
                return this.fromJSONtoAssignments(responseBody);
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Assignment> fromJSONtoAssignments(JSONObject responseBody) throws JSONException {
        List<Assignment> assignments = new ArrayList<>();
        String assignmentsArray = responseBody.getString("assignments");
    }
}
