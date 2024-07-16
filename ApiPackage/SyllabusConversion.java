package ApiPackage;

import CalenderPackage.Assignment;
import CalenderPackage.Course;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class SyllabusConversion {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public List<Assignment> getAssignments(String dataFormat, String syllabusData) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent")
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
