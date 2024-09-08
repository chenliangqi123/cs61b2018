package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    public Position topLeft, bottomRight;
    public int width;
    public int height;

    public Room(Position anchor, int width, int height) {
        this.topLeft = anchor;
        this.bottomRight = anchor.shift(width-1, -(height-1));
        this.width = width;
        this.height = height;
    }

    private static boolean isOverlapped(Room room1, Room room2) {
        int x1 = room1.topLeft.x, y1 = room1.topLeft.y;
        int x2 = room1.bottomRight.x, y2 = room1.bottomRight.y;

        int x3 = room2.topLeft.x, y3 = room2.topLeft.y;
        int x4 = room2.bottomRight.x, y4 = room2.bottomRight.y;

        // if one rectangular is to the left of the other
        if (x2 < x3 - 1 || x4 < x1 - 1) {
            return false;
        }

        // If one rectangle is below the other
        if (y2 < y3 - 1 || y4 < y1 - 1) {
            return false;
        }

        // If none of the above conditions are true, the rectangles overlap
        return true;

    }

    public static ArrayList<Room> generateRooms(int roomNumberLimit, TETile[][] world, int widthLimit, int heightLimit, Random RANDOM) {
        int roomNumber = 1 + RANDOM.nextInt(roomNumberLimit);
        ArrayList<Room> rooms = new ArrayList<>();
        int width, height, x, y;
        Position anchor;

        while (rooms.size() < roomNumber) {
            width = 1 + RANDOM.nextInt(widthLimit - 1);
            height = 1 + RANDOM.nextInt(heightLimit - 1);
            x = 1 + RANDOM.nextInt(world.length - 2 - width);
            y = 1 + height + RANDOM.nextInt(world[0].length - 2 - height);
            anchor = new Position(x, y);
            Room newRoom = new Room(anchor, width, height);
            boolean isOverlapped = false;
            for (Room room : rooms) {
                if (isOverlapped(room, newRoom)) {
                    isOverlapped = true;
                    break;
                }
            }
            if (!isOverlapped) {
                rooms.add(newRoom);
            }
        }

        return rooms;
    }

    public static void drawRooms(TETile[][] world, ArrayList<Room> rooms) {
        for (Room room : rooms) {
            int width = room.width, height = room.height;
            int x = room.topLeft.x - 1, y = room.topLeft.y + 1;
            for (int i = 0; i < width + 1; i++) {
                world[x][y] = Tileset.WALL;
                x++;
            }
            for (int i = 0; i < height + 1; i++) {
                world[x][y] = Tileset.WALL;
                y--;
            }
            for (int i = 0; i < width + 1; i++) {
                world[x][y] = Tileset.WALL;
                x--;
            }
            for (int i = 0; i < height + 1; i++) {
                world[x][y] = Tileset.WALL;
                y++;
            }

        }
    }

    public static void fillRooms (TETile[][] world, ArrayList<Room> rooms) {
        for (Room room : rooms) {
            int width = room.width, height = room.height;

            int x = room.topLeft.x;
            int y = room.topLeft.y;
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    world[x+i][y-j] = Tileset.FLOOR;
                }
            }
        }
    }

    public Position innerRand(Random RANDOM) {
        int innerX = RANDOM.nextInt(this.width) + this.topLeft.x;
        int innerY = RANDOM.nextInt(this.height) + this.bottomRight.y;
        return new Position(innerX, innerY);
    }


}
