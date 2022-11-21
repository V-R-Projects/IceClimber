import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main implements GameUtils {

    public static void main(String[] args) {

        Client client = new Client();
        MainMenu mainMenu = new MainMenu(client);

    }
}