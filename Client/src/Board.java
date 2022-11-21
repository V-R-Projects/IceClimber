import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener, GameUtils{

    private final Client client;
    private final Image backGround;
    private Player Popo;
    private Player Nana;
    private final ArrayList<Level> levels = new ArrayList<>(5);
    private int count = 0;

    public Board(Client p_client, int players)
    {
        this.client = p_client;
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBackground(Color.BLACK);

        paintPlayers(players);
        createLevels();
        ImageIcon ii = new ImageIcon(String.valueOf(Paths.get("Client/src/resources/background.png").toAbsolutePath()));
        backGround = ii.getImage();
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }
    public void paintBackground(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backGround, 0, 0, this);
    }
    private void createLevels() {
        for (int i = 0; i < 5; i++) {
            levels.add(new Level(FRAME_HEIGHT - (i+1)*150 + 75, new int[]{2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2}, i));
        }
    }

    public void paintPlayers(int players)
    {
        if (players == 1)
        {
            Popo = new Player("Popo", (new int[]{FRAME_WIDTH / 2 , FRAME_HEIGHT-150}));
            Nana = new Player("Nana", (new int[]{2000,2000}));

        } else if ( players == 2) {
            Popo = new Player("Popo", (new int[]{FRAME_WIDTH / 2 - 10, FRAME_HEIGHT-150}));
            Nana = new Player("Nana", (new int[]{FRAME_WIDTH / 2 + 10, FRAME_HEIGHT-150}));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintBackground(g);
        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    public void passLevel(Player player) {
        if (player.getLevel() < 4) {
            Level levelUp = levels.get(player.getLevel() + 1);

            if (levelUp.getPosition() > player.getY() + player.getHeight()) {
                player.addLevel();
            }
        }
        Level level = levels.get(player.getLevel());

        if (level.getPosition() < player.getY()) {
            player.rmLevel();
        }
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(Popo.getImage(), Popo.getX(),
                Popo.getY(), this);
        g2d.drawImage(Nana.getImage(), Nana.getX(),
                Nana.getY(), this);

        for (Level level:
             levels) {
            for (Block block :
                    level.getBlocksList()) {
                if (!block.isDestroyed()) g2d.drawImage(block.getImage(), block.getX(),
                        block.getY(), this);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movePlayers();
        detectUpCollision();
        detectDownCollision(Popo);
        detectDownCollision(Nana);
        Popo.fall();
        Nana.fall();
        passLevel(Popo);
        passLevel(Nana);
        repaint();
        count += 1;
        if (count == 50)
        {
            try {
                updatePlayers();
                updateLevels();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Error while updating players");;
            }
//            System.out.println(Popo.createJSONPlayer());
//            System.out.println(Nana.createJSONPlayer());
            updateEnemies();
            count = 0;
        }
    }

    private void updateLevels() throws IOException, ParseException {
        ArrayList<String> strLevels = new ArrayList<>(5);
        for (Level level :
                levels) {
            strLevels.add(level.to_JSONString());
        }

        JSONObject jsonObject = JsonDecompiler.jsonFileToJSONObject();
        String jsonString = jsonObject.toString();
        String JSONLevels = "{\"levels\": " + strLevels.toString() + "}";
        JsonDecompiler.reWriteFile(jsonString, JSONLevels, "levels");
    }

    public void updateEnemies()
    {
        try{
            JSONObject jsonObject = JsonDecompiler.jsonFileToJSONObject();
            client.startConnection(IP, PORT);
            String response = client.sendMessage(jsonObject.toString());
//            String response = client.sendMessage("quit");
            JsonDecompiler.reWriteFile(jsonObject.toString(), response, "enemies");
            client.stopConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error connecting to server");
        }
    }

    public void updatePlayers() throws IOException, ParseException {
        JSONObject jsonObject = JsonDecompiler.jsonFileToJSONObject();
        String jsonString = jsonObject.toString();
        String players = "{\"players\": [" + Popo.createJSONPlayer() + ", " + Nana.createJSONPlayer() + "]}";
//        System.out.println(players);
//        System.out.println(players.toString());
        JsonDecompiler.reWriteFile(jsonString, players, "players");
    }

    public void detectUpCollision()
    {
        if (Popo.getLevel() < 4) {
            Level levelPopo = levels.get(Popo.getLevel() + 1);
            intersection(Popo, levelPopo);
        }
        if (Nana.getLevel() < 4) {
            Level levelNana = levels.get(Nana.getLevel() + 1);
            intersection(Nana, levelNana);
        }
    }

    public void detectDownCollision(Player player)
    {
        Rectangle r1 = player.getBounds();
        Level level = levels.get(player.getLevel());
        for (Block block :
                level.getBlocksList()) {
            Rectangle r3 = block.getBounds();
            if ((r1.intersects(r3)) && !block.isDestroyed()) {
                player.setFalling(false);
                player.setY(block.getY()-player.getHeight());
            }
        }
    }

    public void intersection(Player player, Level levelPlayer)
    {
        Rectangle playerRect = player.getBounds();
        for (Block block :
                levelPlayer.getBlocksList()) {
            Rectangle r3 = block.getBounds();
            if (playerRect.intersects(r3)) {
                if (!block.isDestroyed()) player.setDy(5);
                block.setDestroyed(true);
            }
        }
    }
    public void movePlayers()
    {
        Popo.move();
        Nana.move();
    }

    public void keyPressed(KeyEvent e) {
        Popo.keyPressed(e);
        Nana.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        Popo.keyReleased(e);
        Nana.keyReleased(e);
    }


}
