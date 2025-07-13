package Day10HoofIt;

import java.util.HashSet;
import java.util.Set;

public class TrailheadCalculator {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        int [][] topographicMap = puzzleInput.getTopographicMap();

        Trailhead trailhead = calculateTrailheadScores(topographicMap);
        System.out.println("Total trailhead score: " + trailhead.score);
        System.out.println("Total trailhead rating: " + trailhead.rating);
    }

    public record Point(int row, int col) {}
    public record Trailhead(int score, int rating) {}

    public static Trailhead calculateTrailheadScores(int[][] map) {
        int totalScore = 0;
        int totalRating = 0;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 0) {
                    Set<Point> endpoints = new HashSet<>();
                    RatingCounter counter = new RatingCounter();

                    calculateScoreAndRating(map, y, x, 0, endpoints, counter);

                    totalScore += endpoints.size();
                    totalRating += counter.getCount();
                }
            }
        }

        return new Trailhead(totalScore, totalRating);
    }

    public static void calculateScoreAndRating(int[][] map, int row, int col, int currentValue, Set<Point> endpoints, RatingCounter counter) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        if (map[row][col] != currentValue) return;

        if (currentValue == 9) {
            endpoints.add(new Point(row, col));
            counter.increment();
            return;
        }

        for (int d = 0; d < 4; d++) {
            int newRow = row + dy[d];
            int newCol = col + dx[d];
            if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
                calculateScoreAndRating(map, newRow, newCol, currentValue + 1, endpoints, counter);
            }
        }
    }

}
