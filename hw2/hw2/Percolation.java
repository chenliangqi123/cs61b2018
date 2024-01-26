package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF topUnion;
    private WeightedQuickUnionUF overall;
    private int numOfOpenSites = 0;
    private int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        size = N;
        top = N * N + 1;
//        top = 0;
        bottom = N * N;
        topUnion = new WeightedQuickUnionUF(N * N + 2);
        overall = new WeightedQuickUnionUF(N * N + 2);
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isValid(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            return false;
        }
        return true;
    }


    private int mapping(int row, int col) {
        return row * size + col;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOfOpenSites += 1;
        }
        int currentNum = mapping(row, col);
        if (row == 0) {
            overall.union(top, currentNum);
            topUnion.union(top, currentNum);
        }

        if (row == size - 1) {
            overall.union(bottom, currentNum);
        }

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
//            validate(row + dx, col + dy);
            if (isValid(row + dx, col + dy) && isOpen(row + dx, col + dy)) {
                int mappingNumber = mapping(row + dx, col + dy);
                topUnion.union(currentNum, mappingNumber);
                overall.union(currentNum, mappingNumber);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return topUnion.connected(top, mapping(row, col));
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        return overall.connected(top, bottom);
    }

    public static void main(String[] args) {
//        Percolation p = new Percolation(3);
//        p.open(0, 0);
//        p.open(1, 0);
//        p.open(2, 0);
//        p.open(1, 2);
//        p.open(2, 2);
//        System.out.println(p.isOpen(1, 0));
//        System.out.println(p.isFull(2, 2));
//        System.out.println(p.isFull(1, 0));
//        System.out.println(p.percolates());
//        System.out.println(p.isOpen(0, 0));
    }


}
