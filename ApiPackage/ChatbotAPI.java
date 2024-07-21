package ApiPackage;

import org.json.JSONObject;

interface ChatbotAPI {
    //Gets the response from whichever chatbot
    JSONObject getResponse(String prompt);

    //Takes in the JSONObject returned as a response and parses the object to return a string containing
    //the chatbot response
    String parseResponse(JSONObject response);
}