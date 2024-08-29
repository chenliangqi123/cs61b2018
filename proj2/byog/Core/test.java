package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        final int WIDTH = 100;
        final int HEIGHT = 100;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        MapGenerator map = new MapGenerator(world, 1234);
        ArrayList<Room> rooms = map.generateRooms(10, world, 10, 10);
        MapGenerator.fillWorldWithNothing(world, WIDTH, HEIGHT);
        MapGenerator.drawRooms(world, rooms);
        ter.renderFrame(world);
//        System.out.println(rooms);
    }
}
