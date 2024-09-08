package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Hallway {
    Position start, end, corner;
    int key;

    public Hallway(Position start, Position end, Position corner, int key) {
        this.start = start;
        this.end = end;
        this.corner = corner;
        this.key = key;
    }

    public static void drawHorizontalHallway(Position origin, Position destination, TETile[][] world) {
        for (int i = Math.min(origin.x, destination.x); i <= Math.max(origin.x, destination.x); i++) {
            world[i][origin.y + 1] = Tileset.WALL;
            world[i][origin.y - 1] = Tileset.WALL;
        }
    }

    public static void drawVerticalHallway(Position origin, Position destination, TETile[][] world) {
        for (int j = Math.min(origin.y, destination.y); j <= Math.max(origin.y, destination.y); j++) {
            world[origin.x + 1][j] = Tileset.WALL;
            world[origin.x - 1][j] = Tileset.WALL;
        }
    }

    public static void drawCorner(Position corner, TETile[][] world) {
        for (int i = corner.x - 1; i <= corner.x + 1; i++) {
            for (int j = corner.y - 1; j <= corner.y + 1; j++) {
                world[i][j] = Tileset.WALL;
            }
        }
    }

    public static void fillHorizontalHallway(Position origin, Position destination, TETile[][] world) {
        for (int i = Math.min(origin.x, destination.x); i <= Math.max(origin.x, destination.x); i++) {
            world[i][origin.y] = Tileset.FLOOR;
        }
    }

    public static void fillVerticalHallway(Position origin, Position destination, TETile[][] world) {
        for (int j = Math.min(origin.y, destination.y); j <= Math.max(origin.y, destination.y); j++) {
            world[origin.x][j] = Tileset.FLOOR;
        }
    }

    public static ArrayList<Hallway> generateHallways(ArrayList<Room> rooms, Random RANDOM) {
        ArrayList<Hallway> hallways = new ArrayList<>();
        for (int i = 0; i < rooms.size() - 1; i++) {
            Position p1 = rooms.get(i).innerRand(RANDOM);
            Position p2 = rooms.get(i+1).innerRand(RANDOM);
            int key = RANDOM.nextInt(2);
            Position start = Position.smallerX(p1, p2);
            Position end = Position.largerX(p1, p2);
            if (key == 0) { // firstly draw horizontal hallway
                Position corner = new Position(end.x, start.y);
                hallways.add(new Hallway(start, end, corner, key));
            } else { // firstly draw vertically hallway
                Position corner = new Position(start.x, end.y);
                hallways.add(new Hallway(start, end, corner, key));
            }
        }
        return hallways;
    }

    public static void drawHallways(ArrayList<Hallway> hallways, TETile[][] world) {
        for (Hallway hallway : hallways) {
            if (hallway.key == 0) {
                Hallway.drawHorizontalHallway(hallway.start, hallway.corner, world);
                Hallway.drawCorner(hallway.corner, world);
                Hallway.drawVerticalHallway(hallway.corner, hallway.end, world);
            } else {
                Hallway.drawVerticalHallway(hallway.start, hallway.corner, world);
                Hallway.drawCorner(hallway.corner, world);
                Hallway.drawHorizontalHallway(hallway.corner, hallway.end, world);
            }
        }
    }

    public static void fillHallways(ArrayList<Hallway> hallways, TETile[][] world) {
        for (Hallway hallway : hallways) {
            if (hallway.key == 0) {
                Hallway.fillHorizontalHallway(hallway.start, hallway.corner, world);
                Hallway.fillVerticalHallway(hallway.corner, hallway.end, world);
            } else {
                Hallway.fillVerticalHallway(hallway.start, hallway.corner, world);
                Hallway.fillHorizontalHallway(hallway.corner, hallway.end, world);
            }
        }
    }

}
