import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**

 * Clase que crea objetos tipo Block, los cuales representan los bloques
 * de hielo en la pantalla
 * @author: Valesska Blanco, Ramsés Gutiérrez
 * @version: 16/11/22/B
 */
public class Block {
    private int[] position;
    private boolean isDestroyed;
    private int id;
    private Image image;
    private int width;
    private int height;

    /**
     * Constructor para generar un nuevo objeto tipo Block
     * @param position posición en la que se quiere colocar el bloque
     * @param id número de bloque único para cada bloque en cada nivel
     */
    public Block(int[] position, int id) {
        this.position = position;
        this.id = id;
        Path path = Paths.get("Client/src/resources/Ice_block.png");
        loadImage(String.valueOf(path.toAbsolutePath()));
    }
    /**

     * Método que se encarga de cargar la imagen del bloque
     * @param path ruta donde puede ser encontrada la imagen de los bloques

     */
    private void loadImage(String path) {

        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    /**

     * Método que devuelve la posición en x del bloque
     * @return el número entero que representa la posición en x

     */
    public int getX(){
        return position[0];
    }

    /**

     * Método que devuelve la posición en y del bloque
     * @return el número entero que representa la posición en y

     */
    public int getY(){
        return position[1];
    }

    /**

     * Método que devuelve la posición x,y en forma de lista
     * @return lista que contiene la posición en x y y

     */
    public int[] getPosition() {
        return position;
    }

    /**

     * Método que setea la posición del bloque
     * @param position posición a la que se quiere colocar el bloque

     */
    public void setPosition(int[] position) {
        this.position = position;
    }
    /**

     * Método que devuelve el número de ítems (números aleatorios) existentes en la serie
     * @return El número de ítems (números aleatorios) de que consta la serie

     */
    public boolean isDestroyed() {
        return isDestroyed;
    }
    /**

     * Método que define los bloques como destruidos
     * @param destroyed booleano que describe si el objeto ya fue destruido

     */

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
    /**

     * Método que retorna el id del bloque
     * @return el id del bloque
     */
    public int getId() {
        return id;
    }
    /**

     * Método que realiza el set del id del bloque
     * @param id  el id por el que se desea cambiar
     *
     */
    public void setId(int id) {
        this.id = id;
    }
    /**

     * Método que devuelve la imagen del bloque
     * @return la imagen del objeto bloque
     */
    public Image getImage() {
        return image;
    }
    /**

     * Método que le asigna una imagen al objeto
     * @param image imagen del bloque
     */
    public void setImage(Image image) {
        this.image = image;
    }
    /**

     * Método que devuelve un rectángulo con los límites del objeto
     * @return un objeto tipo Rectangle con los límites del objeto bloque
     */
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
