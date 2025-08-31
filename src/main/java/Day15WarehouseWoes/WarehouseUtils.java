package Day15WarehouseWoes;

public class WarehouseUtils {
    public static Point moveRobot(Point robotPosition, int nx, int ny, char[][] map) {
        map[robotPosition.y()][robotPosition.x()] = '.';
        map[ny][nx] = '@';
        return new Point(nx, ny);
    }
}
