package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private static long SEED;
    private static final int MENUW = 40, MENUH = 60;
    private boolean gameOver;
    private String s;
    private int timeCounter;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        showBlank();
        showMenu();
        StdDraw.text(MENUW / 2, MENUH / 4, "Press your choice please: ");
        StdDraw.show();
        timeCounter = 100;
        StdDraw.enableDoubleBuffering();
        while (true) {
            s = "";
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();
            s += key;
            StdDraw.enableDoubleBuffering();
            StdDraw.clear(Color.black);
            showMenu();
            StdDraw.text(MENUW / 2, MENUH / 4, "Press your choice please: " + s);
            StdDraw.show();
            switch (key) {
                case ('n'):
                case ('N'): {
                    String sd = "";
                    String trueSeed = "";
                    char c = 'l';
                    StdDraw.text(MENUW / 2, MENUH / 4, "Now please input a seed, then press 's' to start the game.");
                    StdDraw.show();
                    do {
                        if (!StdDraw.hasNextKeyTyped()) {
                            continue;
                        }
                        c = StdDraw.nextKeyTyped();
                        if (c >= 48 && c <= 57) {
                            trueSeed += String.valueOf(c);
                        }
                        sd += String.valueOf(c);
                        if (c != 's') {
                            StdDraw.clear(Color.black);
                            showMenu();
                            StdDraw.text(MENUW / 2, MENUH / 4, "Your seed is: " + sd);
                            StdDraw.show();
                        }
                    } while (c != 's');

                    SEED = Long.parseLong(trueSeed);
                    StdDraw.pause(500);
                    System.out.println("## Game final SEED: " + SEED);

                    WorldGenerator worldGenerator = new WorldGenerator(SEED);
                    ter.initialize(worldGenerator.WIDTH, worldGenerator.HEIGHT);
                    World world = worldGenerator.generateWorld();
                    ter.renderFrame(world.getWorld());

                    playGame(world);

                    break;
                }
                case ('l'):
                case ('L'): {
                    World cw = loadCrazyWorld();
                    ter.initialize(80, 30);
                    ter.renderFrame(cw.getWorld());
                    gameOver = false;
                    playGame(cw);
                    break;
                }
                case ('q'):
                case ('Q'): {
                    gameOver = true;
                    System.exit(0);
                    break;
                }
                default:
            }
        }
    }

    private void playGame(World cw) {

        new Thread(() -> {
            while (timeCounter > 0) {
                StdDraw.enableDoubleBuffering();

                timeCounter--;
                //long hh = timeCounter / 60 / 60 % 60;
                //long mm = timeCounter / 60 % 60;
                //long ss = timeCounter % 60;
                //System.out.println("left + hh + "hours" + mm + "minutes" + ss + "seconds");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        char key;
        String record = "";
        while (!gameOver) {
            mousePointer(cw);
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            key = StdDraw.nextKeyTyped();
            record += key;
            if (timeCounter == 0) {
                gameOver = true;
                //System.out.println("You lose!");
                showBlank();
                drawFrame("Sorry! You lose!");
                StdDraw.pause(5000);
                break;
            } else if ((cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y - 1].equals(Tileset
                    .LOCKED_DOOR)) && timeCounter > 0) {
                gameOver = true;
                showBlank();
                drawFrame("Congratulation! You win!");
                StdDraw.pause(5500);
                break;
            }
            //System.out.println(record);
            for (int i = 0; i < record.length() - 1; i += 1) {
                if ((record.charAt(i) == ':' && record.charAt(i + 1) == 'q')
                        || (record.charAt(i) == ':' && record.charAt(i + 1) == 'Q')) {
                    saveCrazyWorld(cw);
                    showBlank();
                    drawFrame("Your game has been saved!");
                    StdDraw.pause(3000);
                    gameOver = true;
                }
            }
            cw = move(cw, key);
        }
        showBlank();
        drawFrame("Have an another try next time!");
        StdDraw.pause(5000);
    }

    private World move(World cw, char key) {
        TETile upper = cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y + 1];
        TETile lower = cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y - 1];
        TETile right = cw.getWorld()[cw.getPlayer().x + 1][cw.getPlayer().y];
        TETile left = cw.getWorld()[cw.getPlayer().x - 1][cw.getPlayer().y];
        switch (key) {
            case ('w'):
            case ('W'): {
                if (upper.equals(Tileset.WALL)) {

                    return cw;
                } else {
                    cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y + 1] = Tileset.PLAYER;
                    cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y] = Tileset.FLOOR;
                    Position newPlayer = new Position(cw.getPlayer().x, cw.getPlayer().y + 1);
                    return new World(cw.getLockedDoor(), newPlayer, cw.getWorld());
                }
            }
            case ('s'):
            case ('S'): {
                if (lower.equals(Tileset.WALL)) {

                    return cw;
                } else if (lower.equals(Tileset.LOCKED_DOOR)) {
                    gameOver = true;
                    return cw;
                } else {

                    cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y - 1] = Tileset.PLAYER;
                    cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y] = Tileset.FLOOR;
                    Position newPlayer = new Position(cw.getPlayer().x, cw.getPlayer().y - 1);
                    return new World(cw.getLockedDoor(), newPlayer, cw.getWorld());
                }
            }
            case ('a'):
            case ('A'): {
                if (left.equals(Tileset.WALL)) {

                    return cw;
                } else {

                    cw.getWorld()[cw.getPlayer().x - 1][cw.getPlayer().y] = Tileset.PLAYER;
                    cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y] = Tileset.FLOOR;
                    Position newPlayer = new Position(cw.getPlayer().x - 1, cw.getPlayer().y);
                    return new World(cw.getLockedDoor(), newPlayer, cw.getWorld());
                }
            }
            case ('d'):
            case ('D'): {
                if (right.equals(Tileset.WALL)) {

                    return cw;
                } else {

                    cw.getWorld()[cw.getPlayer().x + 1][cw.getPlayer().y] = Tileset.PLAYER;
                    cw.getWorld()[cw.getPlayer().x][cw.getPlayer().y] = Tileset.FLOOR;
                    Position newPlayer = new Position(cw.getPlayer().x + 1, cw.getPlayer().y);
                    return new World(cw.getLockedDoor(), newPlayer, cw.getWorld());
                }
            } default: return cw;
        }
    }


    private void mousePointer(World cw) {
        int mx = (int) StdDraw.mouseX();
        int my = (int) StdDraw.mouseY();
        if (cw.getWorld()[mx][my].equals(Tileset.LOCKED_DOOR)) {
            ter.renderFrame(cw.getWorld());
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH / 2, 1, "This is a door "
                    + "where you can escape from this crazy world!");
        } else if (cw.getWorld()[mx][my].equals(Tileset.WALL)) {
            ter.renderFrame(cw.getWorld());
            StdDraw.enableDoubleBuffering();
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH / 2, 1, "This is a wall where you can't go, "
                    + "otherwise you'll lose one life!");
        } else  if (cw.getWorld()[mx][my].equals(Tileset.PLAYER)) {
            ter.renderFrame(cw.getWorld());
            StdDraw.enableDoubleBuffering();
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH / 2, 1, "You, the player!");
        } else if (cw.getWorld()[mx][my].equals(Tileset.FLOOR)) {
            ter.renderFrame(cw.getWorld());
            StdDraw.enableDoubleBuffering();
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH / 2, 1, "Floor!");
        } else if (cw.getWorld()[mx][my].equals(Tileset.FLOWER)) {
            ter.renderFrame(cw.getWorld());
            StdDraw.enableDoubleBuffering();
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH / 2, 1, "Flower! You can eat it to add health value!");
        } else if (cw.getWorld()[mx][my].equals(Tileset.SAND)) {
            ter.renderFrame(cw.getWorld());
            StdDraw.enableDoubleBuffering();
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH / 2, 1, "Sand! You need to clean it to win the game!");
        } else {
            ter.renderFrame(cw.getWorld());
            StdDraw.enableDoubleBuffering();
            StdDraw.setPenColor(Color.white);
            StdDraw.text(WIDTH / 2, 1, "Nothing!");
        }

        StdDraw.text(WIDTH / 2, HEIGHT - 1,
                "Time Left: " + Integer.toString(timeCounter));

        StdDraw.show();
    }

    private void showBlank() {
        gameOver = false;

        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(MENUW * 16, MENUH * 16);
        Font font = new Font("Monaco", Font.BOLD, 100);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, MENUW);
        StdDraw.setYscale(0, MENUH);
        StdDraw.clear(Color.BLACK);
    }

    private void showMenu() {
        Font title = new Font("Monaco", Font.BOLD, 25);
        Font mainMenu = new Font("Monaco", Font.PLAIN, 16);
        StdDraw.setFont(title);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(MENUW / 2, MENUH * 2 / 3, "==== CS61B proj2: Cool Game! ====");
        StdDraw.setFont(mainMenu);
        StdDraw.text(MENUW / 2, MENUH * 5.5 / 10, "New Game (n / N)");
        StdDraw.text(MENUW / 2, MENUH * 4.5 / 10, "Load Game (l / L)");
        StdDraw.text(MENUW / 2, MENUH * 3.5 / 10, "Quit (q / Q)");
    }

    public void drawFrame(String str) {
        int midWidth = MENUW / 2;
        int midHeight = MENUH / 2;
        StdDraw.clear();
        StdDraw.clear(Color.black);
        // Draw the actual text
        Font bigFont = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(bigFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(midWidth, midHeight, str);
        StdDraw.show();
    }

    private static World loadCrazyWorld() {
        File f = new File("./crazyWorld.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (World) os.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        /* In the case no World has been saved yet, we return a new one. */
        return new WorldGenerator(SEED).generateWorld();
    }

    private static void saveCrazyWorld(World cw) {
        File f = new File("./crazyWorld.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(cw);
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        char playMode = input.charAt(0);
        System.out.println("MODE：" + playMode);
        return enterGame(playMode, input);
    }

    private TETile[][] enterGame(char mode, String input) {
        //pick the initial character to be playMode choice.
        switch (mode) {
            case ('n'):
            case ('N'): {
                SEED = Long.parseLong(input);

                //ter.initialize(wgp.width(), wgp.height());
                WorldGenerator worldGenerator = new WorldGenerator(SEED);
                World cw = worldGenerator.generateWorld();
                //ter.renderFrame(cw.world());

                int start = 1;
                for (int i = 0; i < input.length(); i += 1) {
                    if (input.charAt(i) == 's' || input.charAt(i) == 'S') {
                        start = i + 1;
                        break;
                    }
                }
                for (int i = start; i < input.length(); i += 1) {
                    cw = move(cw, input.charAt(i));
                    if ((input.charAt(i) == ':' && input.charAt(i + 1) == 'q')
                            || (input.charAt(i) == ':' && input.charAt(i + 1) == 'Q')) {
                        gameOver = true;
                        saveCrazyWorld(cw);
                        System.out.println("Saved");
                        break;
                    }
                }
                return cw.getWorld();
            }
            case ('l'):
            case ('L'): {
                //load game.
                World cw = loadCrazyWorld();
                int start = 1;
                for (int i = 0; i < input.length(); i += 1) {
                    if (input.charAt(i) == 's' || input.charAt(i) == 'S') {
                        start = i + 1;
                        break;
                    }
                }
                for (int i = start; i < input.length(); i += 1) {
                    if ((input.charAt(i) == ':' && input.charAt(i + 1) == 'q')
                            || (input.charAt(i) == ':' && input.charAt(i + 1) == 'Q')) {
                        gameOver = true;
                        saveCrazyWorld(cw);
                        System.out.println("Saved");
                        break;
                    }
                    cw = move(cw, input.charAt(i));
                }
                return cw.getWorld();
            }
            case ('q'):
            case ('Q'): {
                gameOver = true;
                TETile[][] world = new TETile[80][30];
                for (TETile[] x : world) {
                    for (TETile y : x) {
                        y = Tileset.NOTHING;
                    }
                }
                return world;
            } default: {
                gameOver = true;
                TETile[][] world = new TETile[80][30];
                for (TETile[] x : world) {
                    for (TETile y : x) {
                        y = Tileset.NOTHING;
                    }
                }
                return world;
            }
        }
    }



}
