package Day13ClawContraption;

import java.util.*;

public class ClawMachineSolver {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        List<ClawMachine> clawMachines = puzzleInput.getClawMachines();

        long minimumTokens = calculateMinimumTokens(clawMachines);
        System.out.println("Minimal tokens to win all prizes: " + minimumTokens);
    }

    public static long calculateMinimumTokens(List<ClawMachine> clawMachines) {
        long minimumTokens = 0;
        for (ClawMachine clawMachine : clawMachines) {
            Set<Integer> tokenCounts = new HashSet<>();
            Point start = new Point(0, 0);
            Map<Point, Integer> visited = new HashMap<>();

            exploreTokensToPrize(clawMachine, start, tokenCounts,0, 0, 0, visited);

            if (!tokenCounts.isEmpty()) {
                int minTokenCount = Collections.min(tokenCounts);
                minimumTokens += minTokenCount;
            }
        }
        return minimumTokens;
    }

    public static void exploreTokensToPrize(
            ClawMachine clawMachine,
            Point current,
            Set<Integer> tokenCounts,
            int aCounter,
            int bCounter,
            int tokenCount,
            Map<Point, Integer> visited
    ) {
        if (aCounter > 100 || bCounter > 100 ||
            current.x() > clawMachine.getPrizeLocation().x() ||
            current.y() > clawMachine.getPrizeLocation().y()) {
            return;
        }

        if (visited.containsKey(current) && visited.get(current) <= tokenCount) {
            return;
        }

        visited.put(current, tokenCount);

        if (current.equals(clawMachine.getPrizeLocation())) {
            tokenCounts.add(tokenCount);
            return;
        }

        exploreTokensToPrize(clawMachine, clawMachine.clickA(current), tokenCounts, aCounter + 1, bCounter, tokenCount + 3, visited);
        exploreTokensToPrize(clawMachine, clawMachine.clickB(current), tokenCounts, aCounter, bCounter + 1, tokenCount + 1, visited);
    }

}
