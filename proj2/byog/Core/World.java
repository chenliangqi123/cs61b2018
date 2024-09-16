package byog.Core;

import byog.TileEngine.TETile;

import java.io.Serializable;

public class World implements Serializable {
    private Position lockedDoor;
    private Position player;
    private TETile[][] world;

    public World(Position lockedDoor, Position player, TETile[][] world) {
        this.lockedDoor = lockedDoor;
        this.player = player;
        this.world = world;
    }

    public Position getLockedDoor() {
        return this.lockedDoor;
    }

    public Position getPlayer() {
        return this.player;
    }

    public TETile[][] getWorld() {
        return this.world;
    }
}
