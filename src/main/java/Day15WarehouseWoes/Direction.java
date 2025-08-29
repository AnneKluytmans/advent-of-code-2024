package Day15WarehouseWoes;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Direction fromChar(char c) {
        return switch (c) {
            case '^' -> UP;
            case 'v' -> DOWN;
            case '<' -> LEFT;
            case '>' -> RIGHT;
            default -> null;
        };
    }
}
