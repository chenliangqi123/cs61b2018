package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenerator {

    public final long SEED;
    public final int WIDTH = 100;
    public final int HEIGHT = 100;
    public WorldGenerator(long SEED) {
        this.SEED = SEED;
    }
    public World generateWorld() {
        Random RANDOM = new Random();
        RANDOM.setSeed(this.SEED);
        TERenderer ter = new TERenderer();
//        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
//        MapGenerator map = new MapGenerator(world, 1234);

        ArrayList<Room> rooms = Room.generateRooms(10, world, 10, 10, RANDOM);
        MapGenerator.fillWorldWithNothing(world, WIDTH, HEIGHT);
        Room.drawRooms(world, rooms);

        ArrayList<Hallway> hallways = Hallway.generateHallways(rooms, RANDOM);
        Hallway.drawHallways(hallways, world);
        Room.fillRooms(world, rooms);
        Hallway.fillHallways(hallways, world);
        Position lockedDoor = LockedDoor.fillLockedDoor(rooms, world, RANDOM);
        Random playerRandom = new Random(this.SEED + 250);
        Position player = Player.fillPlayer(rooms, world, playerRandom);
//        ter.renderFrame(world);

        World myWorld = new World(lockedDoor, player, world);
        return myWorld;
    }
}
