import java.util.ArrayList;

public class Player {

    private String spritePath;
    private int speed = 5;
    private ArrayList<Integer> position;
    private int mode = 0; // 0 is not in attack mode, 1 is in attack mode

    public Player(String player, ArrayList initialPosition) {
        if (player == "Popo") {
            spritePath = "./sprites/Popo.jpg";
        }
        else if (player == "Nana") {
            spritePath = "./sprites/Nana.jpg";
        }
        position = initialPosition;
    }
    public void moveRight() {
        position.set(0, (position.get(0) + speed));
    }
    public void moveLeft() {
        position.set(0, (position.get(0) - speed));
    }
    public void jump() {
        position.set(1, (position.get(1) + speed));
    }
    public void fall() {
        position.set(1, (position.get(1) - speed));
    }

    public ArrayList getPosition() {
        return position;
    }
}
