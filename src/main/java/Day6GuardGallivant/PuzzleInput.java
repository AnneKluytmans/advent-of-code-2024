package Day6GuardGallivant;

public class PuzzleInput {
    static final String mapInput =
                    "....#.....\n" +
                    ".........#\n" +
                    "..........\n" +
                    "..#.......\n" +
                    ".......#..\n" +
                    "..........\n" +
                    ".#..^.....\n" +
                    "........#.\n" +
                    "#.........\n" +
                    "......#...";
    private final String[] map;
    private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private final int dirIndex;

    public PuzzleInput(String mapInput, char direction) {
        this.map = mapInput.split("\n");
        this.dirIndex = switch (direction) {
            case '^' -> 0;
            case '>' -> 1;
            case 'v' -> 2;
            case '<' -> 3;
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        };
    }

    public String[] getMap() {
        return map;
    }

    public int[][] getDirections() {
        return directions;
    }

    public int getDirIndex() {
        return dirIndex;
    }

    public String getMapInput() {
        return mapInput;
    }
}
