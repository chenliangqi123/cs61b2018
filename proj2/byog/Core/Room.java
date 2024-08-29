package byog.Core;

public class Room {
    public Position topLeft, bottomRight;
    public int width;
    public int height;

    public Room(Position anchor, int width, int height) {
        this.topLeft = anchor;
        this.bottomRight = anchor.shift(width, -height);
        this.width = width;
        this.height = height;
    }
}
