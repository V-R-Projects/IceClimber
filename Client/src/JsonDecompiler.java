import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class JsonDecompiler {
    static JSONObject jsonObject;

    public static JSONObject convertToJSON(String stringToDecompile)
    {
        try {
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(stringToDecompile);
        }
        catch (Exception e) {
            System.out.println("Error with JSONObject");
        }
        return jsonObject;
    }
}
