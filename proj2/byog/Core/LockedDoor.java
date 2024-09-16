package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class LockedDoor {
    public static Position fillLockedDoor(ArrayList<Room> rooms, TETile[][] world, Random RANDOM) {
        Room room = rooms.get(RANDOM.nextInt(rooms.size()));
        int xPos = room.topLeft.x - 1 + RANDOM.nextInt(room.width + 1);
        while (world[xPos][room.bottomRight.y - 1] == Tileset.FLOOR) {
            xPos = room.topLeft.x + RANDOM.nextInt(room.width);
        }
        world[xPos][room.bottomRight.y - 1] = Tileset.LOCKED_DOOR;
        return new Position(xPos, room.bottomRight.y);
    }
}
