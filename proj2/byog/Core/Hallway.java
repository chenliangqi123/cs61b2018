package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.Random;

public class Hallway {
    private static Position RandomStart(TETile[][] world, long seed) {
        Random RANDOM = new Random(seed);
        while (true) {
            int startX = RANDOM.nextInt(world.length - 2);
            int startY = RANDOM.nextInt(world[0].length - 2);
            startX = startX % 2 == 1 ? startX : startX + 1;
            startY = startY % 2 == 1 ? startY : startY + 1;
            if (world[startX][startY] == Tileset.NOTHING) {
                return new Position(startX, startY);
            }
        }
    }

    private static void PositionAvailableAroundAndConnectPath(Position position, TETile[][] world, LinkedList<Position> positionList, long seed) {
        int[][] nextDirections = {
                {2, 0},
                {0, -2},
                {-2, 0},
                {0, 2}
        };
        Random RANDOM=new Random(seed);
        //标记每一个方向是否走过
        boolean[] book = new boolean[4];
        //只能连接一次
        boolean isConnected = false;
        while (book[0] == false || book[1] == false || book[2] == false || book[3] == false) {
            int next = RANDOM.nextInt(4);
            if (book[next]) continue;
            book[next] = true;
            int nextX = position.x + nextDirections[next][0];
            int nextY = position.y + nextDirections[next][1];
            if (nextX < 0 || nextX > world.length - 1 || nextY < 0 || nextY > world[0].length - 1) {
                continue;
            }
            if (Tileset.NOTHING.equals(world[nextX][nextY])) {
                world[nextX][nextY] = Tileset.FLOWER;
                positionList.add(new Position(nextX, nextY));
            }
            if (Tileset.FLOOR.equals(world[nextX][nextY]) && isConnected == false) {
                int mx = (position.x + nextX) / 2;
                int my = (position.y + nextY) / 2;
                world[mx][my] = Tileset.FLOOR;
                isConnected = true;
            }
        }
    }

    public static boolean generateHallWay(TETile[][] world, long seed) {
        Position start = RandomStart(world, seed);
        generateHallway(world, start, seed);
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                if (Tileset.NOTHING.equals(world[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void generateHallway(TETile[][] world, Position start, long seed) {
        LinkedList<Position> positionLinkedList = new LinkedList<>();
        positionLinkedList.add(start);
        Random random = new Random(seed);
        while (!positionLinkedList.isEmpty()) {
            int index = random.nextInt(positionLinkedList.size());
            Position curPos = positionLinkedList.get(index);
            int curX = curPos.x;
            int curY = curPos.y;
            world[curX][curY] = Tileset.FLOOR;
            PositionAvailableAroundAndConnectPath(curPos, world, positionLinkedList, seed);
            positionLinkedList.remove(index);
        }
    }

    public static void initializeHallWay(TETile[][]world){
        for (int i=0;i<=world.length-1;i++)
            for (int j=0;j<=world[0].length-1;j++)
                world[i][j]= Tileset.WALL;

        for (int i=1;i<=world.length-2;i+=2)
            for (int j=1;j<=world[0].length-2;j+=2)
                world[i][j]=Tileset.NOTHING;
    }

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(79,29);
        TETile[][]world=new TETile[79][29];
        initializeHallWay(world);
        generateHallWay(world,180084946);
        ter.renderFrame(world);
    }


}
