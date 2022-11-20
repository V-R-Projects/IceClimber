import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Client client = new Client();

        try {
            Path path = Paths.get("Client/src/resources/struct.json");
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(
                    new FileReader(path.toAbsolutePath().toFile())); //path to the JSON file.

            JSONArray array = (JSONArray) parser.parse(data.get("enemies").toString());
            System.out.println(array.get(1));
            String jsonFile = data.toJSONString();
//            JSONObject jsonResponse = client.sendRequest("hola");
//            System.out.println(jsonResponse.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading the json file");
        }

//        MainMenu mainMenu = new MainMenu(client);

    }
}