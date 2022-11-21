import org.json.simple.JSONObject;

import java.net.*;
import java.io.*;

public class Client implements GameUtils{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String respose;
    private JSONObject JSONresponse;
    /*
    Metodo que abre una conexion por socket con a partir de un puerto y una dirección IP
    @param ip: direccion del servidor
    @param port: puerto por el cual se conecta
    */
    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    /*
    Metodo que envía un mesaje por una conexion previamente creada
    @param msg: mensaje que se va a enviar por el socket
    */
    public String sendMessage(String msg) throws Exception {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }
    /*
    Metodo que cierra una conexion por socket previamente creada
    */
    public void stopConnection() throws Exception {
        in.close();
        out.close();
        clientSocket.close();
    }


    /*
    Metodo que envia una petición a un servidor haciendo uso de los metodos anteriores
    @param message: mensaje de la petición al servidor
    */
    public String sendRequest(String message)
    {
        try{
            startConnection("127.0.0.1", PORT);
            String response = sendMessage(message);
            stopConnection();
        }
        catch (Exception e) {
            System.out.println("Error connecting to server");
        }
        return respose;
    }
}
