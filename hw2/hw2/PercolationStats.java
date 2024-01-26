package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int trialTimes;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        fractions = new double[T];
        trialTimes = T;
        Percolation p = pf.make(N);
        int numOpen = 0;
        for (int i = 0; i < T; i++) {
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    numOpen += 1;
                }

            }
            fractions[i] = (double) numOpen / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLow() {
        double miu = mean();
        double sigma = stddev();
        return miu - 1.96 * sigma / Math.sqrt(trialTimes);
    }

    public double confidenceHigh() {
        double miu = mean();
        double sigma = stddev();
        return miu + 1.96 * sigma / Math.sqrt(trialTimes);
    }
}
