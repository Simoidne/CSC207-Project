package ApiPackage;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GeminiAPI implements ChatbotAPI {

    //TODO get rid of API_TOKEN before git commit
    //System.getenv("API_TOKEN")
    private static final String API_TOKEN = System.getenv("API_TOKEN");;

    @Override
    public JSONObject getResponse(String prompt) {
//        String content = String.format("{\"role\": \"user\"," +
//                                        "\"parts\": [{\"text\": \"%s\"}]}", prompt);
//        System.out.println(content);
//
//        JSONObject requestBody = new JSONObject(String.format("{\"contents\": %s}", content));

        JSONObject requestBody = new JSONObject().put("contents",
                                    new JSONObject().put("role", "user")
                                                    .put("parts",
                                                            new JSONArray().put(new JSONObject().put("text", prompt))));

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());

        Request request = new Request.Builder()
                .url(String.format("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=%s", API_TOKEN))
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                return responseBody;
            } else {
                throw new RuntimeException(response.message());
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String parseResponse(JSONObject response) {
        String assignmentsString = response.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");

        return assignmentsString;
    }
}