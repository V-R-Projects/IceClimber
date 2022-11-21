import java.util.ArrayList;
import java.util.List;

public class Level {

    private int position;
    private int[] blocks;
    private int height = 150;
    private int id;
    private ArrayList<Block> blocksList = new ArrayList<>(32);
    /*
    Constructor de la clase Level
    @param position: posición en y del nivel
    @param blocks: lista de numeros que representan que bloques estan destruidos
    @param id: identificador del nivel
    */
    public Level(int position, int[] blocks, int id) {
        this.position = position;
        this.blocks = blocks;
        this.id = id;

        createBlocksList();
    }
    /*
    Metodo el cual crea una lista de bloques y lo guarda en el atributo blocksList
    */
    private void createBlocksList() {
        for (int i = 0; i < blocks.length; i++) {
            blocksList.add(new Block(new int[]{i*32, position+16}, i));
        }
    }
    /**
    Getter de position
    @returns: int position
     */
    public int getPosition() {
        return position;
    }
    /**
    Setter de position
    @param: int position
     */
    public void setPosition(int position) {
        this.position = position;
    }
    /**
    Getter de blocks
    @returns: int[] blocks
     */
    public int[] getBlocks() {
        return blocks;
    }
    /**
    Setter de blocks
    @param: int[] blocks
     */
    public void setBlocks(int[] blocks) {
        this.blocks = blocks;
    }
    /**
    Getter de Height
    @returns: int height
     */
    public int getHeight() {
        return height;
    }
    /*
    Setter de Height
    @param: int height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
    Getter de id
    @returns: int id
     */
    public int getId() {
        return id;
    }
    /**
    Setter de id
    @param: int id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
    Getter de BlockList
    @returns: ArrayList<Block> blocksList
     */
    public ArrayList<Block> getBlocksList() {
        return blocksList;
    }
    /**
    Setter de BlockList
    @param: ArrayList<Block> blocksList
     */
    public void setBlocksList(ArrayList<Block> blocksList) {
        this.blocksList = blocksList;
    }
    /*
    Metodo que convierte el nivel en su estructura JSON
    @returns: un string en fomato JSON
    */
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
