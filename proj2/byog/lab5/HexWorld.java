package byog.lab5;

import byog.Core.Position;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private static final long SEED = 2873122;
    private static final Random RANDOM = new Random(SEED);


    public static void fillWorldWithNothing(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    public static void drawRow(TETile[][] world, Position position, int length, TETile tile) {
        for (int i = 0; i < length; i++) {
            world[position.x + i][position.y] = tile;
        }
    }

    public static void drawHexagon(TETile[][] world, Position position, int size, TETile tile) {
        for (int i = 0; i < size; i++) {
            position = position.shift(-1, -1);
            drawRow(world, position, size + 2 * i, tile);
            Position bottom_position = position.shift(0, -(2 * (size - i) - 1));
            drawRow(world, bottom_position, size + 2 * i, tile);
        }
    }

    public static void drawColumnHexagon(TETile[][] world, Position position, int size, int number) {
        int gap = size * 2;
        for (int i = 0; i < number; i++) {
            TETile tile = randomTile();
            Position top_position = position.shift(0, -gap * i);
            drawHexagon(world, top_position, size, tile);
        }
    }

    public static void drawHexagonWorld(TETile[][] world, Position position, int size) {
        int dx = 2 * size - 1;
        int dy = size;
        drawColumnHexagon(world, position, size, 2 * size - 1);
        for (int i = 1; i < size; i++) {
//            TETile tile = randomTile();
            Position rightTopPosition = position.shift(i * dx, -i * dy);
            drawColumnHexagon(world, rightTopPosition, size, 2 * size - 1 - i);
            Position leftTopPosition = position.shift(-i * dx, -i * dy);
            drawColumnHexagon(world, leftTopPosition, size, 2 * size - 1 - i);
        }
    }



    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillWorldWithNothing(world);

        Position anchor = new Position(50, 90);
        drawHexagonWorld(world, anchor, 4);
//        drawHexagon(world, anchor, 3, randomTile());
//        drawColumnHexagon(world, anchor, 3, 5);
//        drawRow(world, anchor, 5, randomTile());
//        drawWorld(world, anchor, 4, 4);

        ter.renderFrame(world);
    }

}

