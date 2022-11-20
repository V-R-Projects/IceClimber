import org.json.simple.JSONObject;

public class Main {

    public static void main(String args[]) {
        String jsonString = "{\n" +
                "    \"players\":[\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"position\": [0, 0],\n" +
                "            \"attacking\": 0,\n" +
                "            \"jumping\": 0,\n" +
                "            \"level\": 0,\n" +
                "            \"lifes\": 0,\n" +
                "            \"direction\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"position\": [0, 0],\n" +
                "            \"attacking\": 0,\n" +
                "            \"jumping\": 0,\n" +
                "            \"level\": 0,\n" +
                "            \"lifes\": 0,\n" +
                "            \"direction\": 0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"enemies\": [\n" +
                "        {\n" +
                "            \"id\": 0,\n" +
                "            \"position\": [0, 0],\n" +
                "            \"status\": 0,\n" +
                "            \"level\": 0,\n" +
                "            \"type\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"position\": [0, 0],\n" +
                "            \"status\": 0,\n" +
                "            \"level\": 0,\n" +
                "            \"type\": 0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"levels\": [\n" +
                "        {\n" +
                "            \"id\": 0,\n" +
                "            \"position\": [0, 0],\n" +
                "            \"color\": 0,\n" +
                "            \"blocks\": [2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        String prueba = "quit";
        Client client = new Client();
        JSONObject jsonResponse = client.sendRequest(prueba);
        System.out.println(jsonResponse.toString());
//        MainMenu mainMenu = new MainMenu(client);

    }
}