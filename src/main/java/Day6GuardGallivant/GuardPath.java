package Day6GuardGallivant;

import java.util.HashSet;
import java.util.Set;

import static Day6GuardGallivant.PuzzleInput.mapInput;

public class GuardPath {
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

        Set<String> visited = new HashSet<>();
        visited.add(startX + "," + startY);

        while (true) {
            int nextX = startX + directions[dirIndex][1];
            int nextY = startY + directions[dirIndex][0];

            if (nextX < 0 || nextX >= map[0].length() || nextY < 0 || nextY >= map.length) {
                break;
            }

            if (map[nextY].charAt(nextX) == '#') {
                dirIndex = (dirIndex + 1) % directions.length;
                continue;
            }

            startX = nextX;
            startY = nextY;

            visited.add(startX + "," + startY);
        }

        System.out.println("Places guard visited : " + visited.size());
    }
}
