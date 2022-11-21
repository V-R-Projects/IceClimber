import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.json.simple.*;

/**

 * Esta clase crea un frame para la interfaz gráfica, extiende de la clase padre JFrame

 * @author: Valesska Blanco, Ramsés Gutiérrez

 * @version: 17/11/22/C
 */

public class GameFrame extends JFrame implements GameUtils {
    
    public JPanel gamePane;
    private Timer timer;
    private Client client;
    private String response;
    private JSONObject gameData;
    private Player Popo;
    private Player Nana;

    /**

     * Constructor para generar el JFrame

     * @param p_client Instancia de un cliente mediante el cual realiza consultas al servidor
     * @param players Representa la cantidad de jugadores que se presentarán en pantalla

     */
    public GameFrame(Client p_client, int players)
    {
        // guardando el cliente como atributo de la clase
        this.client = p_client;

        // incializando los parámetros del JFrame
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creando el JPanel para colocar los objetos en pantalla
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

