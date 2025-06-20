package Day8EasterBunny;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AntinodeLocationCounter {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] antennaMap = puzzleInput.getAntennaMap();

        int count = countUniqueAntinodeLocations(antennaMap);
        System.out.println("Count of unique antinode-locations: " + count);
    }

    public static int countUniqueAntinodeLocations(char[][] map) {
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
                    for (Location antinode : calculateAntinodeLocations(location1, location2)) {
                        if (isWithinBounds(antinode, rows, cols)) {
                            antinodes.add(antinode);
                        }
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

    public static List<Location> calculateAntinodeLocations(Location location1, Location location2) {
        List<Location> antinodes = new ArrayList<>();
        int diffY = location2.row - location1.row;
        int diffX = location2.col - location1.col;

        antinodes.add(new Location(location1.row - diffY, location1.col - diffX));
        antinodes.add(new Location(location2.row + diffY, location2.col + diffX));

        return antinodes;
    }

    public static boolean isWithinBounds(Location location, int rows, int cols) {
        return location.row >= 0 && location.row < rows && location.col >= 0 && location.col < cols;
    }

}
