package Day8EasterBunnyAntinodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AntinodeCounter {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] antennaMap = puzzleInput.getAntennaMap();

        int result1 = countUniqueAntinodeLocations(antennaMap, 1);
        System.out.println("Count of unique antinode-locations by distance rule: " + result1);

        int result2 = countUniqueAntinodeLocations(antennaMap, 2);
        System.out.println("Count of unique antinode-locations by collinearity: " + result2);
    }

    public static int countUniqueAntinodeLocations(char[][] map, int puzzlePart) {
        Set<Location> antinodes = new HashSet<>();
        int rows = map.length;
        int cols = map[0].length;

        Set<Character> frequencies = findAllFrequencies(map);

        for (char freq : frequencies) {
            List<Location> antennas = findAllAntennasOfFreq(map, freq);

            for (int i = 0; i < antennas.size(); i++) {
                for (int j = i + 1; j < antennas.size(); j++) {
                    Location location1 = antennas.get(i);
                    Location location2 = antennas.get(j);
                    if (puzzlePart == 1) {
                        for (Location antinode : calculateAntinodeLocationsByDistance(location1, location2)) {
                            if (isWithinBounds(antinode, rows, cols)) {
                                antinodes.add(antinode);
                            }
                        }
                    } else if (puzzlePart == 2) {
                        antinodes.addAll(calculateAntinodeLocationsByCollinearity(location1, location2, rows, cols));
                    }

                }
            }
        }

        return antinodes.size();
    }

    public static Set<Character> findAllFrequencies(char[][] map) {
        Set<Character> frequencies = new HashSet<>();
        for (char[] row : map) {
            for (char character : row) {
                if (character != '.') {
                    frequencies.add(character);
                }
            }
        }
        return frequencies;
    }

    public static List<Location> findAllAntennasOfFreq(char[][] map, char freq) {
        List<Location> locations = new ArrayList<>();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == freq) {
                    locations.add(new Location(row, col));
                }
            }
        }
        return locations;
    }

    public static List<Location> calculateAntinodeLocationsByDistance(Location location1, Location location2) {
        List<Location> antinodes = new ArrayList<>();
        int dRow = location2.row - location1.row;
        int dCol = location2.col - location1.col;

        antinodes.add(new Location(location1.row - dRow, location1.col - dCol));
        antinodes.add(new Location(location2.row + dRow, location2.col + dCol));

        return antinodes;
    }

    public static List<Location> calculateAntinodeLocationsByCollinearity(Location location1, Location location2, int rows, int cols) {
        List<Location> antinodes = new ArrayList<>();
        int dRow = location2.row - location1.row;
        int dCol = location2.col - location1.col;

        int gcd = gcd(dRow, dCol);
        int stepRow = dRow / gcd;
        int stepCol = dCol / gcd;

        int row = location1.row;
        int col = location1.col;
        while (isWithinBounds(new Location(row, col), rows, cols)) {
            antinodes.add(new Location(row, col));
            row += stepRow;
            col += stepCol;
        }

        row = location1.row - stepRow;
        col = location1.col - stepCol;
        while (isWithinBounds(new Location(row, col), rows, cols)) {
            antinodes.add(new Location(row, col));
            row -= stepRow;
            col -= stepCol;
        }

        return antinodes;
    }

    public static boolean isWithinBounds(Location location, int rows, int cols) {
        return location.row >= 0 && location.row < rows && location.col >= 0 && location.col < cols;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
