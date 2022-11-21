import java.util.ArrayList;
import java.util.List;

public class Level {

    private int position;
    private int[] blocks;
    private int height = 150;
    private int id;
    private ArrayList<Block> blocksList = new ArrayList<>(32);

    public Level(int position, int[] blocks, int id) {
        this.position = position;
        this.blocks = blocks;
        this.id = id;

        createBlocksList();
    }

    private void createBlocksList() {
        for (int i = 0; i < blocks.length; i++) {
            blocksList.add(new Block(new int[]{i*32, position+16}, i));
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int[] getBlocks() {
        return blocks;
    }

    public void setBlocks(int[] blocks) {
        this.blocks = blocks;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Block> getBlocksList() {
        return blocksList;
    }

    public void setBlocksList(ArrayList<Block> blocksList) {
        this.blocksList = blocksList;
    }

    public String to_JSONString() {

        ArrayList<Integer> filotaEsta = new ArrayList<>(32);
        for (Block block :
                blocksList) {
            filotaEsta.add(block.isDestroyed() ? 0:1);
        }

        return "{\"id\": " + Integer.toString(id) + ", \"position\": " +
                Integer.toString(position) + ", \"blocks\": " + filotaEsta.toString() + "}";

    }
}
