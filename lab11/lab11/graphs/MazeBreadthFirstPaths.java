package lab11.graphs;

import java.util.ArrayDeque;
//import java.util.Queue;
import edu.princeton.cs.algs4.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int source;
    private int destination;
    private boolean isTargetFound;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        source = maze.xyTo1D(sourceX, sourceY);
        destination = maze.xyTo1D(targetX, targetY);
        distTo[source] = 0;
        edgeTo[source] = source;
        isTargetFound = false;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs(int source) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        marked[source] = true;
        Queue<Integer> fringe = new Queue<>();
        fringe.enqueue(source);
        announce();

        while (!fringe.isEmpty() && !isTargetFound) {
            int popV = fringe.dequeue();
            if (popV == destination) {
                isTargetFound = true;
                break;
            }
            for (int adjV : maze.adj(popV)) {
                if (!marked[adjV]) {
                    marked[adjV] = true;
                    distTo[adjV] = distTo[popV] + 1;
                    edgeTo[adjV] = popV;
                    fringe.enqueue(adjV);
                    announce();
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs(source);
    }
}

