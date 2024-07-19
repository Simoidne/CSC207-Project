package ApiPackage;

import org.json.JSONObject;

interface ChatbotDB {
    JSONObject getResponse(String prompt);
}