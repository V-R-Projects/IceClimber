import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.json.simple.*;


public class GameFrame extends JFrame implements GameUtils {
    
    public JPanel gamePane;
    private Timer timer;
    private Client client;
    private String response;
    private JSONObject gameData;
    private Player Popo;
    private Player Nana;

    public GameFrame(Client p_client, int players)
    {
        this.client = p_client;
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Board board = new Board(client, players);
        add(board);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                board.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                board.keyReleased(e);
            }
        });
    }
    
}

