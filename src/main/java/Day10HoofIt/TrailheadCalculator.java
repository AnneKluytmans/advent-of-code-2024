package Day10HoofIt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrailheadCalculator {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        int [][] topographicMap = puzzleInput.getTopographicMap();

        Trailhead trailhead = calculateTrailheadScores(topographicMap);

        System.out.println("Total trailhead score: " + trailhead.getScore());
        System.out.println("Total trailhead rating: " + trailhead.getRating());
    }

    public record Point(int row, int col) {}

    public static Trailhead calculateTrailheadScores(int[][] map) {
        Trailhead trailhead = new Trailhead(0, 0);
        Set<Point> uniqueEndpoints = new HashSet<>();
        List<Point> hikingTrails = new ArrayList<>();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 0) {
                    findUniqueEndpoints(map, y, x, 0, uniqueEndpoints, hikingTrails);

                    trailhead.setScore(trailhead.getScore() + uniqueEndpoints.size());
                    trailhead.setRating(trailhead.getRating() + hikingTrails.size());

                    uniqueEndpoints.clear();
                    hikingTrails.clear();
                }
            }
        }
        return trailhead;
    }

    public static void findUniqueEndpoints(int[][] map, int row, int col, int currentValue, Set<Point> endpoints, List<Point> hikingTrails) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        if (map[row][col] != currentValue) return;

        if (currentValue == 9) {
            endpoints.add(new Point(row, col));
            hikingTrails.add(new Point(row, col));
            return;
        }

        for (int d = 0; d < 4; d++) {
            int newRow = row + dy[d];
            int newCol = col + dx[d];
            if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
                findUniqueEndpoints(map, newRow, newCol, currentValue + 1, endpoints, hikingTrails);
            }
        }
    }

}
