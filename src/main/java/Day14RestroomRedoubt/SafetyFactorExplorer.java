package Day14RestroomRedoubt;

import java.util.*;

public class SafetyFactorExplorer {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        List<Robot> robots = puzzleInput.getRobots();
        Size spaceSize = new Size(101, 103);
        int seconds = 100;

        long safetyFactor = calculateSafetyFactor(robots, spaceSize, seconds);
        System.out.println("Safety Factor after " + seconds + " seconds: " + safetyFactor);
    }

    public static long calculateSafetyFactor(List<Robot> robots, Size spaceSize, int seconds) {
        int width = spaceSize.width();
        int height = spaceSize.height();
        int midX = width / 2;
        int midY = height / 2;

        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;

        for (Robot robot : robots) {
            Point end = getEndPosition(robot, spaceSize, seconds);
            int x = end.x();
            int y = end.y();

            if (x == midX || y == midY) {
                continue;
            }

            if (x < midX && y < midY) {
                q1++;
            } else if (x > midX && y < midY) {
                q2++;
            } else if (x < midX) {
                q3++;
            } else {
                q4++;
            }
        }

        return (long) q1 * q2 * q3 * q4;
    }

    public static Point getEndPosition(Robot robot, Size spaceSize, int seconds) {
        Point position = robot.getPosition();
        Velocity velocity = robot.getVelocity();


        int newX = mod(position.x() + velocity.dx() * seconds, spaceSize.width());
        int newY = mod(position.y() + velocity.dy() * seconds, spaceSize.height());
        return new Point(newX, newY);
    }

    public static int mod(int value, int max) {
        return ((value % max) + max) % max;
    }
}
