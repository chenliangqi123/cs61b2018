package byog.Core;

public class Position {
    public int x;
    public int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position shift(int dx, int dy) {
        return new Position(this.x + dx, this.y + dy);
    }

    public static Position smallerX(Position p1, Position p2) {
        if (p1.x < p2.x) return p1;
        else return p2;
    }

    public static Position smallerY(Position p1, Position p2) {
        if (p1.y < p2.y) return p1;
        else return p2;
    }

    public static Position largerX(Position p1, Position p2) {
        if (p1.x >= p2.x) return p1;
        else return p2;
    }

    public static Position largerY(Position p1, Position p2) {
        if (p1.y >= p2.y) return p1;
        else return p2;
    }

}
