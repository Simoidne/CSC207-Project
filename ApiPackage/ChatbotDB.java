package ApiPackage;

import org.json.JSONObject;

interface ChatbotDB {
    JSONObject getResponse(JSONObject prompt);
}