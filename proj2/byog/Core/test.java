package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        final int WIDTH = 100;
        final int HEIGHT = 100;
        Random RANDOM = new Random();
        RANDOM.setSeed(1234);
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        MapGenerator map = new MapGenerator(world, 1234);

        ArrayList<Room> rooms = Room.generateRooms(10, world, 10, 10, RANDOM);
        MapGenerator.fillWorldWithNothing(world, WIDTH, HEIGHT);
        Room.drawRooms(world, rooms);

        ArrayList<Hallway> hallways = Hallway.generateHallways(rooms, RANDOM);
        Hallway.drawHallways(hallways, world);
        Room.fillRooms(world, rooms);
        Hallway.fillHallways(hallways, world);
        ter.renderFrame(world);

    }
}
