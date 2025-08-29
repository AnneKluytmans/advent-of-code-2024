package Day15WarehouseWoes;

import Helpers.CharFinder;
import Helpers.GridCopier;

import java.util.ArrayList;
import java.util.List;

public class ExpandedWarehouseGpsScorer {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] warehouseMap = expandMap(puzzleInput.getMap());
        List<Direction> directions = puzzleInput.getDirections();

        long gpsScore = calculateGpsScore(warehouseMap, directions);
        System.out.println("The total sum of the robots GPS coördinates is: " + gpsScore);
    }

    public static long calculateGpsScore(char[][] warehouseMap, List<Direction> directions) {
        long gpsScore = 0;
        char[][] endPositions = simulateMovements(warehouseMap, directions);

        for (int y = 0; y < endPositions.length; y++) {
            for (int x = 0; x < endPositions[y].length; x++) {
                if (endPositions[y][x] == '[') {
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

            char target = map[nx][ny];

            if (target == '.') {
                WarehouseUtils.moveRobot(robotPosition, nx, ny, map);
            } else if (target == '[' || target == ']') {
                if (direction == Direction.UP || direction == Direction.DOWN) {
                    if (tryPushVertical(map, dy, nx, ny)) {
                        robotPosition = WarehouseUtils.moveRobot(robotPosition, nx, ny, map);
                    }
                } else {
                    if (tryPushHorizontal(map, dx, dy, nx, ny)) {
                        robotPosition = WarehouseUtils.moveRobot(robotPosition, nx, ny, map);
                    }
                }
            }
        }
        return map;
    }

    public static boolean tryPushVertical(char[][] map, int dy, int nx, int ny) {
        List<int[]> boxes = new ArrayList<>();
        collectBoxes(map, nx, ny, dy, boxes);

        if (boxes.isEmpty()) return false;

        for (int[] box : boxes) {
            int bx = box[0];
            int by = box[1];
            if (map[by + dy][bx] == '#' || map[by + dy][bx + 1] == '#') return false;
        }

        boxes.sort((a, b) -> dy > 0 ? b[1] - a[1] : a[1] - b[1]);

        for (int[] box : boxes) {
            int bx = box[0];
            int by = box[1];
            map[by][bx] = '.';
            map[by][bx + 1] = '.';
            map[by + dy][bx] = '[';
            map[bx + dy][bx + 1] = ']';
        }

        return true;
    }

    public static boolean tryPushHorizontal(char[][] map, int dx, int dy, int nx, int ny) {
        int nextX = nx;
        int nextY = ny;

        while (map[nextY][nextX] == '[' || map[nextY][nextX] == ']') {
            nextX += dx;
            nextY += dy;
        }

        char nextTarget = map[nextY][nextX];

        if (nextTarget == '.') {
            map[nextY][nextX] = ']';
            return true;
        }

        return false;
    }


    private static void collectBoxes(char[][] map, int x, int y, int dy, List<int[]> boxes) {
        char target = map[y][x];
        if (target != '[' && target != ']') {
            return;
        }

        int leftX = (target == '[') ? x : x - 1;

        for (int[] b : boxes) {
            if (b[0] == leftX && b[1] == y) {
                return;
            }
        }

        boxes.add(new int[]{leftX, y});

        char aboveLeft  = map[y + dy][leftX];
        char aboveRight = map[y + dy][leftX + 1];

        if (aboveLeft == '[' || aboveLeft == ']' || aboveRight == '[' || aboveRight == ']') {
            collectBoxes(map, leftX, y + dy, dy, boxes);
            collectBoxes(map, leftX + 1, y + dy, dy, boxes);
        }
    }

    private static char[][] expandMap(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;

        char[][] expandedMap = new char[rows][cols * 2];

        for (int y = 0; y < rows; y++) {
            int newX = 0;
            for (int x = 0; x < cols; x++) {
                char c = map[y][x];
                switch (c) {
                    case  '#' -> {expandedMap[y][newX++] = '#'; expandedMap[y][newX++] = '#';}
                    case  'O' -> {expandedMap[y][newX++] = '['; expandedMap[y][newX++] = ']';}
                    case '.' -> {expandedMap[y][newX++] = '.'; expandedMap[y][newX++] = '.';}
                    case '@' -> {expandedMap[y][newX++] = '@'; expandedMap[y][newX++] = '.';}
                    default -> {expandedMap[y][newX++] = c; expandedMap[y][newX++] = c;}
                }
            }
        }
        return expandedMap;
    }
}
