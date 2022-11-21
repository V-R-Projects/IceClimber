import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**

 * Clase que se encarga del manejo de los mensajes que llegan a través del servidor
 * con el fin de actualizarlos en el archivo struct.json
 * @author: Valesska Blanco, Ramsés Gutiérrez
 * @version: 19/11/22/D
 */
public class JsonDecompiler {
    static private JSONParser parser = new JSONParser();
    static private String pathToFile = "Client/src/resources/struct.json";
    static private Path path = Paths.get(pathToFile);

    public static JSONObject stringToJSONObject(String stringToParse) throws ParseException {
        JSONObject jsonObject = (JSONObject) parser.parse(stringToParse);
        return jsonObject;
    }
    /**

     * Método que se encarga de convertir un archivo .json en un JSONObject
     * @return un JSONObject que es la lectura del archivo .json

     */
    public static JSONObject jsonFileToJSONObject() throws IOException, ParseException {

        System.out.println(path.toAbsolutePath());
        JSONObject data = (JSONObject) parser.parse(
                new FileReader(path.toAbsolutePath().toFile())); //path to the JSON file.
        return data;
    }
    /**

     * Método que se encarga de re-escribir el archivo .json con datos actualizados por la interfaz
     * @param jsonString un string que contiene la estructura de datos completa
     *                   proveniente del .json
     * @param updatedData los datos que se desean actualizar en el archivo .json

     */
    public static void reWriteFile(String jsonString, String updatedData, String key) throws IOException, ParseException {
        JSONObject newJsonObject = replaceValue(jsonString, updatedData, key);
        FileWriter file = new FileWriter(path.toAbsolutePath().toFile());
        file.write(newJsonObject.toJSONString());
        file.close();
    }
    /**

     * Método que se encarga de reemplazar el valor de una llave en el archivo .json
     * @param dest un string que contiene el destino donde se quieren reemplazar datos
     * @param source los datos que se desean actualizar en el archivo .json
     * @param key string que contiene el valor de la llave a la que se desea
     *            reemplazar el valor

     */
    public static JSONObject replaceValue(String dest, String source, String key) throws ParseException {
        JSONObject jsonEnemies = JsonDecompiler.stringToJSONObject(source);
        JSONObject jsonFile = JsonDecompiler.stringToJSONObject(dest);
//        System.out.println(jsonEnemies.get("enemies").toString());
        jsonFile.replace(key, jsonEnemies.get(key));
//        System.out.println(jsonFile.get("enemies").toString());
        return jsonFile;
    }


}
