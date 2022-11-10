
public class Main {
    public static void main(String args[]) {
        try {
            String response;
            Client client = new Client();
            client.startConnection("192.168.18.25", 43001);
            response = client.sendMessage("quit");
            client.stopConnection();
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("Error connecting to server");
        }
    }
}
