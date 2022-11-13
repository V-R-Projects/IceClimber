
public class Main {

    public static GUI interfaceGui;
    public static void main(String args[]) {
        try {
            String response;
            Client client = new Client();
            interfaceGui = new GUI(client);

            // client.startConnection("192.168.0.20", 43003);
            // response = client.sendMessage("quit");
            // client.stopConnection();
            // System.out.println(response);
            
        } catch (Exception e) {
            System.out.println("Error connecting to server");
        }
    }
}
