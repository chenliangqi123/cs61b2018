package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solver {
    private Stack<WorldState> solutions = new Stack<>();
    private MinPQ<SearchNode> MP = new MinPQ<>();
    private HashSet<WorldState> marked = new HashSet<>();

    public Solver(WorldState initial) {
        MP.insert(new SearchNode(initial, 0, null));
        marked.add(initial);
        while (!MP.min().word.isGoal()) {
            SearchNode min = MP.delMin();
            solutions.push(min.word);
            for (WorldState w : min.word.neighbors()) {
//                if (!marked.contains(w)) {
//                    marked.add(w);
//                    MP.insert(new SearchNode(w, min.moves + 1, min));
//                }
                if (min.previousNode == null || !w.equals(min.previousNode.word)) {
                    marked.add(w);
                    MP.insert(new SearchNode(w, min.moves + 1, min));
                }
            }
        }

    }

    public int moves() {
        return MP.min().moves;
    }

    public Iterable<WorldState> solution() {
        List<WorldState> finalSolutions = new ArrayList<>();
        while (!solutions.isEmpty()) {
            finalSolutions.add(solutions.pop());
        }
        return finalSolutions;
    }

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState word;
        private int moves;
        private SearchNode previousNode;
        private int priority;

        public SearchNode(WorldState initial, int moves, SearchNode previousNode) {
            this.word = initial;
            this.moves = moves;
            this.previousNode = previousNode;
            this.priority = moves + initial.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }
}
