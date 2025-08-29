package Day15WarehouseWoes;

import java.util.Arrays;
import java.util.List;

public class WarehouseGpsScorer {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] warehouseMap = puzzleInput.getMap();
        List<Direction> directions = puzzleInput.getDirections();

        long GpsScore = calculateGpsScore(warehouseMap, directions);
        System.out.println("The total sum of the robots GPS coördinates is: " + GpsScore);
    }

    public static long calculateGpsScore(char[][] warehouseMap, List<Direction> directions) {
        long GpsScore = 0;
        char[][] endPositions = simulateMovements(warehouseMap, directions);

        for (int y = 0; y < endPositions.length; y++) {
            for (int x = 0; x < endPositions[y].length; x++) {
                if (endPositions[y][x] == 'O') {
                    GpsScore += (long) 100 * y + x;
                }
            }
        }

        return GpsScore;
    }

    public static char[][] simulateMovements(char[][] warehouseMap, List<Direction> directions) {
        char[][] endPositions = copyGrid(warehouseMap);
        Point currentPosition = findStart(warehouseMap);

        for (Direction direction : directions) {
            int dx = 0, dy = 0;
            switch (direction) {
                case LEFT  -> dx = -1;
                case RIGHT -> dx = 1;
                case UP    -> dy = -1;
                case DOWN  -> dy = 1;
            }

            int newX = currentPosition.x() + dx;
            int newY = currentPosition.y() + dy;

            char target = endPositions[newY][newX];

            if (target == '.') {
                endPositions[currentPosition.y()][currentPosition.x()] = '.';
                endPositions[newY][newX] = '@';
                currentPosition = new Point(newX, newY);

            } else if (target == 'O') {
                int nextX = newX;
                int nextY = newY;

                while (endPositions[nextY][nextX] == 'O') {
                    nextX += dx;
                    nextY += dy;
                }

                char nextTarget = endPositions[nextY][nextX];

                if (nextTarget == '.') {
                    endPositions[nextY][nextX] = 'O';
                    endPositions[newY][newX] = '@';
                    endPositions[currentPosition.y()][currentPosition.x()] = '.'; // oude robotpositie leeg
                    currentPosition = new Point(newX, newY);
                }
            }
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
        return new Point(0, 0);
    }
}
