package Day6GuardGallivant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        String[] tempMap = new String[map.length];
        for (int i = 0; i < map.length; i++) {
            tempMap[i] = new String(map[i]);
        }
        char[] tempRow = tempMap[obsY].toCharArray();
        tempRow[obsX] = '#';
        tempMap[obsY] = new String(tempRow);

        List<String> movementHistory = new ArrayList<>();
        movementHistory.add(startX + "," + startY);

        int curX = startX;
        int curY = startY;

        while (true) {
            int nextX = curX + directions[dirIndex][1];
            int nextY = curY + directions[dirIndex][0];

            if (nextX < 0 || nextX >= tempMap[0].length() || nextY < 0 || nextY >= tempMap.length) {
                break;
            }

            if (tempMap[nextY].charAt(nextX) == '#') {
                dirIndex = (dirIndex + 1) % directions.length;
                continue;
            }

            curX = nextX;
            curY = nextY;

            String position = curX + "," + curY;
            movementHistory.add(position);

            if (hasRepeatingPattern(movementHistory)) {
                return true;
            }
        }
        return false;
    }


    // Hulpmethode om herhalende patronen te detecteren
    private static boolean hasRepeatingPattern(List<String> history) {
        int size = history.size();
        for (int len = 1; len <= size / 2; len++) {
            boolean repeating = true;
            for (int i = 0; i < len; i++) {
                if (!history.get(size - len - i - 1).equals(history.get(size - i - 1))) {
                    repeating = false;
                    break;
                }
            }
            if (repeating) {
                return true;
            }
        }
        return false;
    }
}
