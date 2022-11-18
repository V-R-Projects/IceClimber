import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame implements GameUtils{
    
    public JPanel gamePane;
    private Timer timer;
    private Client client;
    private String response;

    public GameFrame(Client p_client)
    {
        this.client = p_client;
        setTitle(TITLE);
        setResizable(false);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        paintPane();
        gamePane.repaint();
    }

    public void paintPane()
    {
        gamePane = new JPanel();
        this.getContentPane().add(gamePane);
        gamePane.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        gamePane.setLayout(null);
        gamePane.setBackground(Color.BLACK);
    }

    private void gameLoop()
    {
        timer = new Timer(DELAY, (e) -> {
            // updateData();
            repaint();
        });
        timer.start();
    }

    private void updateData()
    {
        try{
            client.startConnection("127.0.0.1", 43005);
            response = client.updateData("holi");
            client.stopConnection();
        }
        catch (Exception e) {
            System.out.println("Error connecting to server");
        }

        //leer desde el json y llamar a actualizar la posicion de los jugadores
    }
    
}

