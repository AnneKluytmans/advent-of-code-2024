package Day16ReindeerMaze;

public enum Direction {
    NORTH(0, -1, '^', 0),
    EAST(1, 0, '>', 1),
    SOUTH(0, 1, 'v', 2),
    WEST(-1, 0, '<', 3);

    final int dx, dy;
    final char symbol;
    final int index;

    Direction(int dx, int dy, char symbol, int index) {
        this.dx = dx;
        this.dy = dy;
        this.symbol = symbol;
        this.index = index;
    }

    public static int rotationSteps(Direction from, Direction to) {
        int steps = Math.abs(from.index - to.index);
        return Math.min(steps, 4 - steps);
    }

    public static Direction fromChar(char c) {
        for (Direction d : Direction.values()) {
            if (d.symbol == c) { return d; }
        }
        return null;
    }
}
