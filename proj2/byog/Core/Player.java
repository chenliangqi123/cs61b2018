package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    public static Position fillPlayer(ArrayList<Room> rooms, TETile[][] world, Random RANDOM) {
//        RANDOM.setSeed();
        Room room = rooms.get(RANDOM.nextInt(rooms.size()));
        Position startPosition = room.innerRand(RANDOM);
        world[startPosition.x][startPosition.y] = Tileset.PLAYER;
        return startPosition;
    }
}
