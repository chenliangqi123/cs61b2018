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
        MapGenerator.drawHorizontalHallway(new Position(50, 98), new Position(15, 98), world);
        MapGenerator.drawVerticalHallway(new Position(50, 98), new Position(50, 40), world);
//        MapGenerator.drawTopLeftCorner(new Position(40, 40), world);
//        MapGenerator.drawTopRightCorner(new Position(40, 40), world);
//        MapGenerator.drawBottomLeftCorner(new Position(40, 40), world);
//        MapGenerator.drawBottomRightCorner(new Position(40, 40), world);
        ter.renderFrame(world);
//        System.out.println(rooms);
    }
}
