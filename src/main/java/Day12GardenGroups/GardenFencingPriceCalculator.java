package Day12GardenGroups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GardenFencingPriceCalculator {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] puzzleBoard = puzzleInput.getPuzzleBoard();

        List<Region> regions = getRegions(puzzleBoard);

        long totalPrice = calculateTotalFencingPrice(regions);
        long discountPrice = calculateDiscountFencingPrice(regions);
        System.out.println("The total fencing price is: " + totalPrice);
        System.out.println("The total discount price is: " + discountPrice);
    }

    public static long calculateTotalFencingPrice(List<Region> regions) {
        long totalPrice = 0;

        for (Region region : regions) {
            totalPrice += region.getFencingPrice();
        }

        return totalPrice;
    }

    public static long calculateDiscountFencingPrice(List<Region> regions) {
        long discountPrice = 0;

        for (Region region : regions) {
            discountPrice += region.getDiscountFencingPrice();
        }

        return discountPrice;
    }

    public static List<Region> getRegions(char[][] puzzleBoard) {
        Set<Point> visited = new HashSet<>();
        List<Region> regions = new ArrayList<>();

        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard[0].length; j++) {
                Point p = new Point(i, j);
                if (!visited.contains(p)) {
                    Region region = new Region();
                    Set<Edge> edges = new HashSet<>();
                    char target = puzzleBoard[i][j];
                    scanRegion(puzzleBoard, i, j, visited, region, target, edges);
                    scanEdges(edges, region);
                    regions.add(region);
                }
            }
        }

        return regions;
    }

    public static void scanRegion(char[][] puzzleBoard, int row, int col, Set<Point> visited, Region region, char target, Set<Edge> edges) {
        Point current = new Point(row, col);
        if (visited.contains(current)) return;

        visited.add(current);
        region.increaseArea();

        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        for (int d = 0; d < 4; d++) {
            int newRow = row + dy[d];
            int newCol = col + dx[d];

            Edge edge = new Edge(new Point(row, col), new Point(newRow, newCol));
            if (newRow >= 0 && newRow < puzzleBoard.length &&
                newCol >= 0 && newCol < puzzleBoard[0].length) {

                if (puzzleBoard[newRow][newCol] == target) {
                    scanRegion(puzzleBoard, newRow, newCol, visited, region, target, edges);
                } else {
                    region.increasePerimeter();
                    edges.add(edge);
                }
            } else {
                region.increasePerimeter();
                edges.add(edge);
            }
        }
    }

    public static void scanEdges(Set<Edge> edges, Region region) {
        Set<Edge> visited = new HashSet<>();

        for (Edge edge : edges) {
            if (visited.contains(edge)) continue;

            region.increaseSides();
            visited.add(edge);

            Edge nextEdge = edge.nextInSameDirection();
            while (nextEdge != null && edges.contains(nextEdge) && !visited.contains(nextEdge)) {
                visited.add(nextEdge);
                nextEdge = nextEdge.nextInSameDirection();
            }

            Edge previousEdge = edge.previousInSameDirection();
            while (previousEdge != null && edges.contains(previousEdge) && !visited.contains(previousEdge)) {
                visited.add(previousEdge);
                previousEdge = previousEdge.previousInSameDirection();
            }
        }

    }
}
