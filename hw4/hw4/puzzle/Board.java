package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private int[][] goal;
    private int[][] initial;
    private int N;
    private static final int BLANK = 0;

    public Board(int[][] tiles) {
        N = tiles.length;
        goal = new int[N][N];
        initial = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                goal[i][j] = i * N + j;
            }
        }
        goal[N - 1][N - 1] = BLANK;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                initial[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i >= N || j >= N) {
            throw new IndexOutOfBoundsException("i and j must be between 0 and N − 1.");
        }
        return initial[i][j];
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (initial[i][j] != BLANK && initial[i][j] != goal[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int number = initial[i][j];
                if (number != BLANK) {
                    int goalX = (number - 1) / N;
                    int goalY = (number - 1) % N;
                    count += Math.abs(i - goalX) + Math.abs(j - goalY);
                }
            }
        }
        return count;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        Board a = (Board) y;
        if (a.size() != this.size()) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a.tileAt(i, j) != this.tileAt(i, j)) return false;
            }
        }
        return true;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public int size() {
        return N;
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
