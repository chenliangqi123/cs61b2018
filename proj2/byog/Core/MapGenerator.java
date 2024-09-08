package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    public TETile[][] world;
    private long seed;

    private static final Random RANDOM = new Random();

    public MapGenerator(TETile[][] world, long seed) {
        this.world = world;
        this.seed = seed;
        RANDOM.setSeed(this.seed);
    }

    public static void fillWorldWithNothing(TETile[][] world, int WIDTH, int HEIGHT) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }


}
