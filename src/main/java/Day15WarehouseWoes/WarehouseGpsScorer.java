package Day15WarehouseWoes;

import java.util.Arrays;
import java.util.List;

public class WarehouseGpsScorer {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] warehouseMap = puzzleInput.getMap();
        List<Direction> directions = puzzleInput.getDirections();

        long GpsScore = calculateGpsScore(warehouseMap, directions);
        System.out.println("The total sum of the robots GPS coördinates: " + GpsScore);
    }

    public static long calculateGpsScore(char[][] warehouseMap, List<Direction> directions) {
        long GpsScore = 0;
        char[][] endPositions = simulateMovements(warehouseMap, directions);

        for (int y = 0; y < endPositions.length; y++) {
            for (int x = 0; x < endPositions[y].length; x++) {
                if (endPositions[y][x] == '0') {
                    GpsScore += (long) 100 * y + x;
                }
            }
        }

        return GpsScore;
    }

    public static char[][] simulateMovements(char[][] warehouseMap, List<Direction> directions) {
        char[][] endPositions = copyGrid(warehouseMap);
        Point startPosition = findStart(warehouseMap);

        for (Direction direction : directions) {
            //  Look in map at the neighbour (n) of '@" corresponding with the direction (LEFT (dx = -1; dy = 0), RIGHT (dx = +1; dy = 0), UP (dx = 0; dy = -1) or DOWN (dx = 0; dy = +1))
            //    If n == '.' -> replace n with '@' and current '@' with '.'
            //    If n == '#' -> do nothing (continue)
            //    If n == '0' -> check neighbour of '0' and move both
        }

        return endPositions;
    }

    private static char[][] copyGrid(char[][] grid) {
        char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return copy;
    }

    private static Point findStart(char[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == '@') {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }
}
