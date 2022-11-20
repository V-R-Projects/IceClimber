import org.json.simple.JSONObject;

import java.net.*;
import java.io.*;

public class Client implements GameUtils{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String respose;
    private JSONObject JSONresponse;

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws Exception {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws Exception {
        in.close();
        out.close();
        clientSocket.close();
    }

    public JSONObject sendRequest(String message)
    {
        try{
            startConnection("127.0.0.1", PORT);
            String response = sendMessage(message);
            JSONresponse = JsonDecompiler.convertToJSON(response);
            stopConnection();
        }
        catch (Exception e) {
            System.out.println("Error connecting to server");
        }
        return JSONresponse;
    }
}
