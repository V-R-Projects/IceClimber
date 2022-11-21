import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonDecompiler {
    static private JSONParser parser = new JSONParser();
    static private String pathToFile = "Client/src/resources/struct.json";
    static private Path path = Paths.get(pathToFile);

    public static JSONObject stringToJSONObject(String stringToParse) throws ParseException {
        JSONObject jsonObject = (JSONObject) parser.parse(stringToParse);
        return jsonObject;
    }

    public static JSONObject jsonFileToJSONObject() throws IOException, ParseException {

        System.out.println(path.toAbsolutePath());
        JSONObject data = (JSONObject) parser.parse(
                new FileReader(path.toAbsolutePath().toFile())); //path to the JSON file.
        return data;
    }

    public static JSONObject getPlayer(JSONObject jsonObject, int ply) throws ParseException {

        JSONArray players = (JSONArray) parser.parse(jsonObject.get("players").toString());
        JSONObject player = (JSONObject) parser.parse(players.get(ply).toString());

        return player;
    }

    public static void reWriteFile(String jsonString, String updatedData, String key) throws IOException, ParseException {
        JSONObject newJsonObject = replaceValue(jsonString, updatedData, key);
        FileWriter file = new FileWriter(path.toAbsolutePath().toFile());
        file.write(newJsonObject.toJSONString());
        file.close();
    }
    public static JSONObject replaceValue(String dest, String source, String key) throws ParseException {
        JSONObject jsonEnemies = JsonDecompiler.stringToJSONObject(source);
        JSONObject jsonFile = JsonDecompiler.stringToJSONObject(dest);
//        System.out.println(jsonEnemies.get("enemies").toString());
        jsonFile.replace(key, jsonEnemies.get(key));
//        System.out.println(jsonFile.get("enemies").toString());
        return jsonFile;
    }


}
