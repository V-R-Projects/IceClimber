import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.nio.file.Path;
import java.nio.file.Paths;


/**

 * Clase que se encarga de crear jugadores
 * @author: Valesska Blanco, Ramsés Gutiérrez
 * @version: 17/11/22/C
 */

public class Player implements GameUtils{

    private String spritePath;
    private int id;
    private int level = 0;
    private int speed = 5;
    private int[] position;
    private int fallSpeed = 0;
//    private int mode = 0; // 0 is not in attack mode, 1 is in attack mode
    private Image image;
    private int width;
    private int height;
    private int dx;
    private int dy;
    private boolean falling = true;

    /**
     * Constructor para generar un nuevo Jugador
     * @param player nombre del nuevo jugaodor
     * @param initialPosition lista con la posición en x y y incial
     *                        del jugador
     */
    public Player(String player, int[] initialPosition) {
        if (Objects.equals(player, "Popo")) {
            spritePath = "Client/src/resources/Popo.png";
            this.id = 0;
        }
        else if (Objects.equals(player, "Nana")) {
            spritePath = "Client/src/resources/Nana.png";
            this.id = 1;
        }
        Path path = Paths.get(spritePath);
        position = initialPosition;
        loadImage(String.valueOf(path.toAbsolutePath()));
    }
    /**

     * Método que se encarga de crear un string que contiene un objeto que puede
     * ser parseado a JSONObject
     * @return string conteniendo la estructura de datos del jugador
     */
    public String createJSONPlayer()
    {
        JSONObject JSONPlayer = new JSONObject();
        JSONPlayer.put("id", this.id);
        JSONPlayer.put("level", this.level);
        JSONPlayer.put("x", getX());
        JSONPlayer.put("y", getY());
        return JSONPlayer.toJSONString();
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    /**

     * Método que se encarga de cargar la imagen del jugador y obtener su largo y ancho
     * @param path ruta de la imagen
     */
    private void loadImage(String path) {

        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    /**

     * Método que se encarga de cambiar la posición del jugador
     */
    public void move()
    {
        if (getX() <= 0) addX(5);
        if (getX() + width >= FRAME_WIDTH) addX(-5);
        if (getY() <= 0) dy = 1;
        addX(dx);
        addY(dy);
    }
    public void jump() {
        position[1] += speed;
    }
    public void fall() {
        if (isFalling()){
            fallSpeed = 1;
        }
        else {
            fallSpeed = 0;
        }
        dy += fallSpeed;
    }

    /**

     * Método que se encarga de detectar los eventos de teclado
     * @param e evento de teclado
     */
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (id == 0) {
            if (key == KeyEvent.VK_LEFT) {
                dx = -5;
            }

            if (key == KeyEvent.VK_RIGHT) {
                dx = 5;
            }

            if (key == KeyEvent.VK_NUMPAD0) {
                if (!isFalling()) dy = -20;
                setFalling(true);
            }
        }
        if (id == 1){
            if (key == KeyEvent.VK_A) {
                dx = -5;
            }

            if (key == KeyEvent.VK_D) {
                dx = 5;
            }

            if (key == KeyEvent.VK_SPACE) {
                if (!isFalling()) dy = -20;
                setFalling(true);
            }
        }
    }
    /**

     * Método que se encarga de detectar los eventos de teclado
     * @param e evento de teclado
     */
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dx = 0;
        }

    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Image getImage() {
        return image;
    }

    public int getX()
    {
        return position[0];
    }
    public int getY()
    {
        return position[1];
    }
    public void addX(int x)
    {
        position[0] += x;
    }
    public void addY(int y)
    {
        position[1] += y;
    }

    public void setY(int y)
    {
        position[1] = y;
    }

    public int getLevel() {
        return level;
    }

    public void addLevel() {
        if(this.level < 4) {
            this.level += 1;
        }
    }

    public void rmLevel() {
        if(this.level > 0) {
            this.level -= 1;
        }
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    /**

     * Método que se encarga de detectar los eventos de teclado
     * @return un objeto tipo Rectangle con los límites del jugador
     */
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
