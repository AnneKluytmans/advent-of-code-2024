package Day15WarehouseWoes;

import Helpers.CharFinder;
import Helpers.GridCopier;

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

    public static char[][] simulateMovements(char[][] map, List<Direction> directions) {

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
