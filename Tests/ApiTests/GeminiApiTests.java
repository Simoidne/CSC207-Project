package Tests.ApiTests;

import ApiPackage.GeminiAPI;
import org.json.JSONObject;
import org.junit.Assert;
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
                "  \"candidates\": [\n" +
                "    {\n" +
                "      \"content\": {\n" +
                "        \"parts\": [\n" +
                "          {\n" +
                "            \"text\": \"1\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"role\": \"model\"\n" +
                "      },\n" +
                "      \"finishReason\": \"STOP\",\n" +
                "      \"index\": 0,\n" +
                "      \"safetyRatings\": [\n" +
                "        {\n" +
                "          \"category\": \"HARM_CATEGORY_SEXUALLY_EXPLICIT\",\n" +
                "          \"probability\": \"NEGLIGIBLE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"category\": \"HARM_CATEGORY_HATE_SPEECH\",\n" +
                "          \"probability\": \"NEGLIGIBLE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"category\": \"HARM_CATEGORY_HARASSMENT\",\n" +
                "          \"probability\": \"NEGLIGIBLE\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"category\": \"HARM_CATEGORY_DANGEROUS_CONTENT\",\n" +
                "          \"probability\": \"NEGLIGIBLE\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"promptFeedback\": {\n" +
                "    \"safetyRatings\": [\n" +
                "      {\n" +
                "        \"category\": \"HARM_CATEGORY_SEXUALLY_EXPLICIT\",\n" +
                "        \"probability\": \"NEGLIGIBLE\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"category\": \"HARM_CATEGORY_HATE_SPEECH\",\n" +
                "        \"probability\": \"NEGLIGIBLE\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"category\": \"HARM_CATEGORY_HARASSMENT\",\n" +
                "        \"probability\": \"NEGLIGIBLE\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"category\": \"HARM_CATEGORY_DANGEROUS_CONTENT\",\n" +
                "        \"probability\": \"NEGLIGIBLE\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}");

        JSONObject responseJSON = testAPI.getResponse("Give me a 1 and nothing else.");
        Assert.assertEquals(expected.toString(), responseJSON.toString());
    }
}
