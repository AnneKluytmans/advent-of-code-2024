package Day13ClawContraption;

import java.util.*;

public class ClawMachineSolver {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        List<ClawMachine> clawMachines = puzzleInput.getClawMachines();

        long totalTokens = calculateMinimumTokens(clawMachines, Mode.NORMAL);
        long totalTokensExtended = calculateMinimumTokens(clawMachines, Mode.EXTENDED);
        System.out.println("Minimal tokens to win all prizes in normal mode: " + totalTokens);
        System.out.println("Minimal tokens to win all prizes in extended mode: " + totalTokensExtended);
    }

    public static long calculateMinimumTokens(List<ClawMachine> clawMachines, Mode mode) {
        long totalTokens = 0;
        for (ClawMachine machine : clawMachines) {
            Long tokens = calculateTokensForMachine(machine, mode);

            if (tokens != null) {
                totalTokens += tokens;
            }
        }
        return totalTokens;
    }

    public static Long calculateTokensForMachine(ClawMachine machine, Mode mode) {
        long ax = machine.getButtonA().dx();
        long ay = machine.getButtonA().dy();
        long bx = machine.getButtonB().dx();
        long by = machine.getButtonB().dy();

        long prizeX = machine.getPrizeLocation().x();
        long prizeY = machine.getPrizeLocation().y();

        if (mode == Mode.EXTENDED) {
            prizeX += 10_000_000_000_000L;
            prizeY += 10_000_000_000_000L;
        }

        // prize X = i * ax + j * bx
        // prize Y = i * ay + j * by
        long determinant = ax * by - ay * bx;

        if (determinant == 0) {
            return null;
        }

        long iNum = prizeX * by - prizeY * bx;
        long jNum = ax * prizeY - ay * prizeX;

        if (iNum % determinant != 0 || jNum % determinant != 0) return null;

        long i = iNum / determinant;
        long j = jNum / determinant;

        if (i < 0 || j < 0) return null;
        if (mode == Mode.NORMAL && (i > 100 || j > 100)) return null;

        return 3L * i + j;
    }

}
