package Tests.ApiTests;

import ApiPackage.GeminiAPI;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeminiApiTests {

    GeminiAPI testAPI;

    @BeforeEach
    public void setUp() {
        testAPI = new GeminiAPI();
    }

    @Test
    @DisplayName("Gemini API returns the correctly formatted JSONObject")
    public void TestReturnsCorrectlyFormattedJSON() {
        JSONObject expected = new JSONObject("{\n" +
                "    \"candidates\": [\n" +
                "        {\n" +
                "            \"content\": {\n" +
                "                \"parts\": [\n" +
                "                    {\n" +
                "                        \"text\": \"1 \\n\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"role\": \"model\"\n" +
                "            },\n" +
                "            \"finishReason\": \"STOP\",\n" +
                "            \"index\": 0,\n" +
                "            \"safetyRatings\": [\n" +
                "                {\n" +
                "                    \"category\": \"HARM_CATEGORY_SEXUALLY_EXPLICIT\",\n" +
                "                    \"probability\": \"NEGLIGIBLE\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"category\": \"HARM_CATEGORY_HATE_SPEECH\",\n" +
                "                    \"probability\": \"NEGLIGIBLE\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"category\": \"HARM_CATEGORY_HARASSMENT\",\n" +
                "                    \"probability\": \"NEGLIGIBLE\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"category\": \"HARM_CATEGORY_DANGEROUS_CONTENT\",\n" +
                "                    \"probability\": \"NEGLIGIBLE\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"usageMetadata\": {\n" +
                "        \"promptTokenCount\": 10,\n" +
                "        \"candidatesTokenCount\": 1,\n" +
                "        \"totalTokenCount\": 11\n" +
                "    }\n" +
                "}");

        JSONObject actualResponseJSON = testAPI.getResponse("Give me a 1 and nothing else.");
        Assertions.assertEquals(expected.toString(), actualResponseJSON.toString());
    }

    @Test
    @DisplayName("GeminiAPI method, parseResponse, returns a string containing gemini's response")
    public void TestParseResponse() {
        String expected = "1";
        String actualResponse;

        JSONObject responseJSON = testAPI.getResponse("Give me a 1 and nothing else.");
        actualResponse = testAPI.parseResponse(responseJSON).replaceAll("\\s+", "");
        Assertions.assertEquals(expected, actualResponse);
    }
}
