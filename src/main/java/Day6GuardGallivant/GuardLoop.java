package Day6GuardGallivant;

import java.util.HashSet;
import java.util.Set;

import static Day6GuardGallivant.PuzzleInput.mapInput;

public class GuardLoop {
    public static void main(String[] args) {
        int startX = 0;
        int startY = 0;
        char direction = '^';

        PuzzleInput puzzleInput = new PuzzleInput(mapInput, direction);
        String[] map = puzzleInput.getMap();
        int[][] directions = puzzleInput.getDirections();
        int dirIndex = puzzleInput.getDirIndex();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length(); x++) {
                char c = map[y].charAt(x);
                if (c == '^' || c == '>' || c == '<' || c == 'v') {
                    startX = x;
                    startY = y;
                    direction = c;
                    break;
                }
            }
        }

        Set<String> validObstructions = new HashSet<>();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length(); x++) {
                if (map[y].charAt(x) == '.' && !(x == startX && y == startY)) {
                    if (causesLoop(map, startX, startY, dirIndex, directions, x, y)) {
                        validObstructions.add(x + "," + y);
                    }
                }
            }
        }

        System.out.println("Valid obstructions : " + validObstructions.size());
    }

    private static boolean causesLoop(String[] map, int startX, int startY, int dirIndex, int[][] directions, int obsX, int obsY) {
        Set<String> visited = new HashSet<>();
        int x = startX;
        int y = startY;
        int directionIndex = dirIndex;

        char[][] tempMap = copyMap(map);
        tempMap[obsY][obsX] = '#';

        while (true) {
            String state = x + "," + y + "," + directionIndex;

            if (visited.contains(state)) {
                return true;
            }
            visited.add(state);

            if (x < 0 || y < 0 || y >= tempMap.length || x >= tempMap[0].length) {
                break;
            }

            int nextX = x + directions[directionIndex][0];
            int nextY = y + directions[directionIndex][1];

            if (nextX >= 0 && nextY >= 0 && nextY < tempMap.length && nextX < tempMap[0].length && tempMap[nextY][nextX] == '#') {
                directionIndex = (directionIndex + 1) % 4;
                continue;
            }

            x = nextX;
            y = nextY;

        }
        return false;
    }

    private static char[][] copyMap(String[] map) {
        char[][] newMap = new char[map.length][];
        for (int i = 0; i < map.length; i++) {
            newMap[i] = map[i].toCharArray();
        }
        return newMap;
    }
}
