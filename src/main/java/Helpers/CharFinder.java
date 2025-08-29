package Helpers;

import Day15WarehouseWoes.Point;

public class CharFinder {
    public static Point findChar(char[][] map, char target) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == target) {
                    return new Point(x, y);
                }
            }
        }
        return new Point(0, 0);
    }
}
