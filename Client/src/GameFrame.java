import javax.swing.*;
import java.awt.*;
import org.json.simple.*;


public class GameFrame extends JFrame implements GameUtils{
    
    public JPanel gamePane;
    private Timer timer;
    private Client client;
    private String response;
    private JSONObject gameData;

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
        gameData = client.sendRequest("request data");
        // createEnemies(gameData.get(2));
    }
    
}

