package Day14RestroomRedoubt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormationDetector {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        List<Robot> robots = puzzleInput.getRobots();
        Size spaceSize = new Size(101, 103);
        int maxSeconds = spaceSize.width() * spaceSize.height();

        simulateRobots(robots, maxSeconds, spaceSize);
    }

    static void simulateRobots(List<Robot> robots, int maxSeconds, Size spaceSize) {
        for (int t = 0; t < maxSeconds; t++) {
            Set<Point> positions = new HashSet<>();

            for (Robot robot : robots) {
                Point position = RobotMover.moveRobot(robot, spaceSize, t);
                positions.add(position);
            }

            int clustered = scoreDensity(positions);
            if (clustered > 0.75 * robots.size()) {
                System.out.println("Possible Christmas Tree formation after " + t + " seconds:");
                printGrid(positions, spaceSize);
                break;
            }
        }
    }

    static int scoreDensity(Set<Point> robots) {
        int score = 0;
        for (Point robot : robots) {
            if (hasNeighbor(robot, robots)) score ++;
        }
        return score;
    }

    static boolean hasNeighbor(Point robot, Set<Point> robots) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                if (robots.contains(new Point(robot.x() + dx, robot.y() + dy))) {
                    return true;
                }
            }
        }
        return false;
    }

    static void printGrid(Set<Point> robots, Size spaceSize) {
        System.out.println();

        for (int y = 0; y < spaceSize.height(); y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < spaceSize.width(); x++) {
                Point point = new Point(x, y);
                line.append(robots.contains(point) ? "^" : ".");
            }
            System.out.println(line);
        }

        System.out.println();
    }
}
