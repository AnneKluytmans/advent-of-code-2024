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

    public static char[][] simulateMovements(char[][] warehouseMap, List<Direction> directions) {
        char[][] map = GridCopier.copyGrid(warehouseMap);
        Point robotPosition = CharFinder.findChar(warehouseMap, '@');

        for (Direction direction : directions) {
            int dx = Direction.dx(direction);
            int dy = Direction.dy(direction);

            int nx = robotPosition.x() + dx;
            int ny = robotPosition.y() + dy;

            char target = map[ny][nx];

            if (target == '.') {
                robotPosition = WarehouseUtils.moveRobot(robotPosition, nx, ny, map);
            } else if (target == 'O') {
                int nextX = nx;
                int nextY = ny;

                while (map[nextY][nextX] == 'O') {
                    nextX += dx;
                    nextY += dy;
                }

                char nextTarget = map[nextY][nextX];

                if (nextTarget == '.') {
                    map[nextY][nextX] = 'O';
                    robotPosition = WarehouseUtils.moveRobot(robotPosition, nx, ny, map);
                }
            }
        }

        return map;
    }

}
