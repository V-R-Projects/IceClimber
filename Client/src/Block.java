import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Block {
    private int[] position;
    private boolean isDestroyed;
    private int id;
    private Image image;
    private int width;
    private int height;

    public Block(int[] position, int id) {
        this.position = position;
        this.id = id;
        Path path = Paths.get("Client/src/resources/Ice_block.png");
        loadImage(String.valueOf(path.toAbsolutePath()));
    }
    private void loadImage(String path) {

        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public int getX(){
        return position[0];
    }

    public int getY(){
        return position[1];
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
