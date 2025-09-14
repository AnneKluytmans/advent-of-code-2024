package Helpers;

public class InBoundsChecker {
    public static boolean inBounds(char[][] map, Point p) {
        return p.y() >= 0 && p.y() < map.length && p.x() >= 0 && p.x() < map[0].length;
    }
}
