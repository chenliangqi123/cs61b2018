package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MapGenerator implements Serializable {
    private static final long serialVersionUID = 156464981;
    private int WIDTH;
    private int HEIGHT;
    private Random RANDOM;
    private long SEED;
    private static final int TIMES=100;
    private static TETile[][] world;
    //记录房间的位置
    private static TETile[][] positionOfRoom;
    private static ArrayList<Room> Rooms;
    public Position player;
    public Position door;

}
