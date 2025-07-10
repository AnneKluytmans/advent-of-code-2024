package Day9DiskFragmenter;

import java.util.ArrayList;
import java.util.List;

public class FilesystemChecksumCalculator {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        List<Integer> digits = parseToDigits(puzzleInput.getPuzzleInput());

        List<Integer> disk = createDiskLayout(digits);
        compactDisk(disk);

        long checksum = calculateChecksum(disk);
        System.out.println("Filesystem checksum: " + checksum);
    }

    public static long calculateChecksum(List<Integer> disk) {
        long checksum = 0L;

        for (int i = 0; i < disk.size(); i++) {
            Integer fieldId = disk.get(i);
            if (fieldId != null) {
                checksum += (long) fieldId * i;
            }
        }
        return checksum;
    }

    private static List<Integer> createDiskLayout(List<Integer> digitBlocks) {
        List<Integer> disk = new ArrayList<>();
        int fileId = 0;

        for (int i = 0; i < digitBlocks.size(); i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < digitBlocks.get(i); j++) {
                    disk.add(fileId);
                }
                fileId++;
            } else {
                for (int j = 0; j < digitBlocks.get(i); j++) {
                    disk.add(null);
                }
            }
        }
        return disk;
    }

    public static void compactDisk(List<Integer> disk) {
        while (true) {
            int firstFreeSpace = -1;
            for (int i = 0; i < disk.size(); i++) {
                if (disk.get(i) == null) {
                    firstFreeSpace = i;
                    break;
                }
            }
            if (firstFreeSpace == -1) break;

            int lastDigit = -1;
            for (int i = disk.size() -1; i > firstFreeSpace; i--) {
                if (disk.get(i) != null) {
                    lastDigit = i;
                    break;
                }
            }
            if (lastDigit == -1) break;

            disk.set(firstFreeSpace, disk.get(lastDigit));
            disk.set(lastDigit, null);
        }
    }

    private static List<Integer> parseToDigits(String input) {
        List<Integer> digits = new ArrayList<>();

        for (char c : input.toCharArray()) {
            digits.add(Character.getNumericValue(c));
        }
        return digits;
    }

}
