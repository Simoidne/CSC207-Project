package ApiPackage;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GeminiDB implements ChatbotDB {

    private static final String API_TOKEN = System.getenv("API_TOKEN");

    @Override
    public JSONObject getResponse(String prompt) {
        String content = String.format("[{\"parts\": [{\"text\": %s}]}]", prompt);

        JSONObject requestBody = new JSONObject(String.format("{\"contents\": \"%s\"}", content));

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

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
                return responseBody;
            } else {
                throw new RuntimeException(response.message());
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}