import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**

 * Clase principal donde se encuentra el void main
 * @author: Valesska Blanco, Ramsés Gutiérrez
 * @version: 20/11/22/F
 */
public class Main implements GameUtils {

    public static void main(String[] args) {

        // Instanciando el cliente
        Client client = new Client();
        // Creando la pantalla del MEnú Principal
        MainMenu mainMenu = new MainMenu(client);

    }
}