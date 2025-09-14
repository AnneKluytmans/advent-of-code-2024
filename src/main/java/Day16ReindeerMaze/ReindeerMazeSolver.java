package Day16ReindeerMaze;

import Helpers.InBoundsChecker;
import Helpers.Point;
import Helpers.CharFinder;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReindeerMazeSolver {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] maze = puzzleInput.getMaze();

        long lowestScore = findLowestScore(maze, Direction.EAST);
        System.out.println("The lowest score for the reindeer to get from the start (S) to the end (E) is: " + lowestScore);
    }

    private static long findLowestScore(char[][] maze, Direction startDirection) {
        Point start = CharFinder.findChar(maze, 'S');
        Point end = CharFinder.findChar(maze, 'E');

        //Dijkstra-algorithm
        PriorityQueue<State> queue = new PriorityQueue<>();
        Map<String, Long> bestScore = new HashMap<>();

        State startState = new State(start, startDirection, 0, 0);
        queue.add(startState);
        bestScore.put(startState.key(), startState.getTotalScore());

        while (!queue.isEmpty()) {
            State current = queue.poll();

            Long knownScore = bestScore.get(current.key());
            if (knownScore != null && knownScore < current.getTotalScore()) continue;

            if (current.position().equals(end)) {
                return current.getTotalScore();
            }

            for (Direction d : Direction.values()) {
                int rotationSteps = Direction.rotationSteps(current.direction(), d);
                Point newPosition = new Point(
                        current.position().x() + d.dx,
                        current.position().y() + d.dy
                );

                if (!InBoundsChecker.inBounds(maze, newPosition) || maze[newPosition.y()][newPosition.x()] == '#') continue;

                State nextState = new State(
                        newPosition,
                        d,
                        current.steps() + 1,
                        current.rotations() + rotationSteps
                );

                long newScore = nextState.getTotalScore();

                if (bestScore.getOrDefault(nextState.key(), Long.MAX_VALUE) > newScore) {
                    bestScore.put(nextState.key(), newScore);
                    queue.add(nextState);
                }
            }
        }
        throw new IllegalArgumentException("No best score for the reindeer to get from the start (S) to the end (E).");
    }


}
