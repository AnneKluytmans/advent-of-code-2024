package Day15WarehouseWoes;

import Helpers.CharFinder;
import Helpers.GridCopier;

import java.util.List;

public class WarehouseGpsScorer {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] warehouseMap = puzzleInput.getMap();
        List<Direction> directions = puzzleInput.getDirections();

        long gpsScore = calculateGpsScore(warehouseMap, directions);
        System.out.println("The total sum of the robots GPS coördinates is: " + gpsScore);
    }

    public static long calculateGpsScore(char[][] warehouseMap, List<Direction> directions) {
        long gpsScore = 0;
        char[][] endPositions = simulateMovements(warehouseMap, directions);

        for (int y = 0; y < endPositions.length; y++) {
            for (int x = 0; x < endPositions[y].length; x++) {
                if (endPositions[y][x] == 'O') {
                    gpsScore += (long) 100 * y + x;
                }
            }
        }

        return gpsScore;
    }

    public static char[][] simulateMovements(char[][] map, List<Direction> directions) {
        char[][] warehouseMap = GridCopier.copyGrid(map);
        Point robotPosition = CharFinder.findChar(map, '@');

        for (Direction direction : directions) {
            int dx = 0, dy = 0;
            switch (direction) {
                case LEFT  -> dx = -1;
                case RIGHT -> dx = 1;
                case UP    -> dy = -1;
                case DOWN  -> dy = 1;
            }

            int targetX = robotPosition.x() + dx;
            int targetY = robotPosition.y() + dy;

            char target = warehouseMap[targetY][targetX];

            if (target == '.') {
                warehouseMap[robotPosition.y()][robotPosition.x()] = '.';
                warehouseMap[targetY][targetX] = '@';
                robotPosition = new Point(targetX, targetY);

            } else if (target == 'O') {
                int nextX = targetX;
                int nextY = targetY;

                while (warehouseMap[nextY][nextX] == 'O') {
                    nextX += dx;
                    nextY += dy;
                }

                char nextTarget = warehouseMap[nextY][nextX];

                if (nextTarget == '.') {
                    warehouseMap[nextY][nextX] = 'O';
                    warehouseMap[targetY][targetX] = '@';
                    warehouseMap[robotPosition.y()][robotPosition.x()] = '.';
                    robotPosition = new Point(targetX, targetY);
                }
            }
        }

        return warehouseMap;
    }

}
