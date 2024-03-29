package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Solver {
    private List<WorldState> solutions = new ArrayList<>();
    private MinPQ<SearchNode> MP = new MinPQ<>();
    private HashSet<WorldState> marked = new HashSet<>();

    public Solver(WorldState initial) {
        MP.insert(new SearchNode(initial, 0, null));
//        marked.add(initial);
        while (!MP.min().word.isGoal()) {
            SearchNode min = MP.delMin();
//            solutions.add(min.word);
            for (WorldState w : min.word.neighbors()) {
//                if (!marked.contains(w)) {
//                    marked.add(w);
//                    MP.insert(new SearchNode(w, min.moves + 1, min));
//                }
                if (min.previousNode == null || !w.equals(min.previousNode.word)) {
//                    marked.add(w);
                    MP.insert(new SearchNode(w, min.moves + 1, min));
                }
            }
        }
//        solutions.add(MP.min().word);
    }

    public int moves() {
        return MP.min().moves;
    }

    public Iterable<WorldState> solution() {
        Stack<WorldState> stack = new Stack<>();
        SearchNode pos = MP.min();
        while (pos != null) {
            stack.push(pos.word);
            pos = pos.previousNode;
        }
        while (!stack.isEmpty()) {
            solutions.add(stack.pop());
        }
        return solutions;
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
