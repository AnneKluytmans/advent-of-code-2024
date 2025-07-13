package Day10HoofIt;

import java.util.HashSet;
import java.util.Set;

public class TrailheadsScoreCalculator {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        int [][] topographicMap = puzzleInput.getTopographicMap();

        long trailheadsScore = calculateTrailheadScore(topographicMap);
        System.out.println("Total trailhead score: " + trailheadsScore);
    }

    public record Point(int row, int col) {}
    public static long calculateTrailheadScore(int[][] map) {
        long trailheadsScore = 0;
        Set<Point> endpoints = new HashSet<>();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 0) {
                    searchPath(map, y, x, 0, endpoints);
                    trailheadsScore += endpoints.size();
                    endpoints.clear();
                }
            }
        }
        return trailheadsScore;
    }

    public static void searchPath(int[][] map, int row, int col, int currentValue, Set<Point> endpoints) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        if (map[row][col] != currentValue) return;

        if (currentValue == 9) {
            endpoints.add(new Point(row, col));
            return;
        }

        for (int d = 0; d < 4; d++) {
            int newRow = row + dy[d];
            int newCol = col + dx[d];
            if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
                searchPath(map, newRow, newCol, currentValue + 1, endpoints);
            }
        }
    }

}
