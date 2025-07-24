package Day12GardenGroups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GardenFencingPriceCalculator {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] puzzleBoard = puzzleInput.getPuzzleBoard();

        long totalPrice = calculateTotalFencingPrice(puzzleBoard);
        System.out.println("The total fencing price is: " + totalPrice);
    }

    public record Point(int row, int col) {}

    public static long calculateTotalFencingPrice(char[][] puzzleBoard) {
        long totalPrice = 0;
        Set<Point> visited = new HashSet<>();
        List<Region> regions = new ArrayList<>();

        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard[0].length; j++) {
                Point p = new Point(i, j);
                if (!visited.contains(p)) {
                    Region region = new Region();
                    char target = puzzleBoard[i][j];
                    scanRegion(puzzleBoard, i, j, visited, region, target);
                    regions.add(region);
                }
            }
        }

        for (Region region : regions) {
            totalPrice += region.getFencingPrice();
        }

        return totalPrice;
    }

    public static void scanRegion(char[][] puzzleBoard, int row, int col, Set<Point> visited, Region region, char target) {
        Point current = new Point(row, col);
        if (visited.contains(current)) return;

        visited.add(current);
        region.increaseArea();

        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        for (int d = 0; d < 4; d++) {
            int newRow = row + dy[d];
            int newCol = col + dx[d];

            if (newRow >= 0 && newRow < puzzleBoard.length &&
                newCol >= 0 && newCol < puzzleBoard[0].length) {

                if (puzzleBoard[newRow][newCol] == target) {
                    scanRegion(puzzleBoard, newRow, newCol, visited, region, target);
                } else {
                    region.increasePerimeter();
                }
            } else {
                region.increasePerimeter();
            }
        }
    }
}
