public class OffByN implements CharacterComparator{
    public int n;
    public OffByN(int n) {
        this.n = n;
    }
    public boolean equalChars(char x, char y) {
        return Math.abs(x-y) == n;
    }
}
