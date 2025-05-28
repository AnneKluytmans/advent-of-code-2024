package Day6GuardGallivant;

import java.util.*;

public class GuardLoop {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput(PuzzleInput.mapInput, '^');
        String[] map = puzzleInput.getMap();
        int[][] directions = puzzleInput.getDirections();
        int[] start = findGuardStart(map, '^');
        int startCol = start[1];
        int startRow = start[0];

        Set<String> loopObstructionPositions = findLoopObstructions(map, directions, startRow, startCol);

        System.out.println("Possible obstruction positions: " + loopObstructionPositions);
        System.out.println("Number of obstruction positions: " + loopObstructionPositions.size());
    }

    public static Set<String> findLoopObstructions(String[] map, int[][] directions, int startRow, int startCol) {
        Set<String> obstructionPositions = new HashSet<>();
        int rows = map.length;
        int cols = map[0].length();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (map[r].charAt(c) == '.') { // Alleen lege plekken testen
                    if (causesLoopWithObstruction(map, directions, startRow, startCol, r, c)) {
                        obstructionPositions.add(r + "," + c);
                    }
                }
            }
        }

        return obstructionPositions;
    }

    public static boolean causesLoopWithObstruction(String[] map, int[][] directions, int startRow, int startCol, int obstructionRow, int obstructionCol) {
        // Maak een kopie van de kaart met de obstructie toegevoegd
        String[] tempMap = map.clone();
        char[] tempRow = tempMap[obstructionRow].toCharArray();
        tempRow[obstructionCol] = '#';
        tempMap[obstructionRow] = new String(tempRow);

        // Simuleer de beweging van de bewaker
        int row = startRow;
        int col = startCol;
        int dirIndex = 0;
        Set<String> visitedStates = new HashSet<>();

        int stepCount = 0;
        int maxSteps = 4 * countFreeTiles(tempMap); //Het aantal vrije plaatsen

        while (stepCount <= maxSteps) {
            String state = row + "," + col + "," + dirIndex;

            if (visitedStates.contains(state)) {
                return true;
            }

            visitedStates.add(state);
            stepCount++;

            int nextRow = row + directions[dirIndex][0];
            int nextCol = col + directions[dirIndex][1];

            if (nextRow < 0 || nextRow >= tempMap.length || nextCol < 0 || nextCol >= tempMap[0].length()) {
                return false;
            }

            if (tempMap[nextRow].charAt(nextCol) == '#') {
                dirIndex = (dirIndex + 1) % 4;
            } else {
                row = nextRow;
                col = nextCol;
            }
        }

        return false;
    }

    public static int countFreeTiles(String[] map) {
        int count = 0;
        for (String row : map) {
            for (char c : row.toCharArray()) {
                if (c == '.') {
                    count++;
                }
            }
        }
        return count;
    }


    public static int[] findGuardStart(String[] map, char guardSymbol) {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++) {
                if (map[r].charAt(c) == guardSymbol) {
                    return new int[]{r, c};
                }
            }
        }
        throw new IllegalArgumentException("Guard not found on the map!");
    }
}

