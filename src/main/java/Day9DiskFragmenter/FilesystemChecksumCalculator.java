package Day9DiskFragmenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilesystemChecksumCalculator {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        List<Integer> digits = parseToDigits(puzzleInput.getPuzzleInput());

        List<Integer> disk = createDiskLayout(digits);
        List<Integer> disk1 = compactDiskByShiftingFragments(disk);
        List<Integer> disk2 = compactDiskByShiftingWholeFiles(disk);

        long checksum1 = calculateChecksum(disk1);
        long checksum2 = calculateChecksum(disk2);
        System.out.println("Filesystem checksum part 1: " + checksum1);
        System.out.println("Filesystem checksum part 2: " + checksum2);
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

    public static List<Integer> compactDiskByShiftingFragments(List<Integer> disk) {
        List<Integer> compactDisk = new ArrayList<>(disk);

        while (true) {
            int firstFreeSpace = -1;
            for (int i = 0; i < disk.size(); i++) {
                if (compactDisk.get(i) == null) {
                    firstFreeSpace = i;
                    break;
                }
            }
            if (firstFreeSpace == -1) break;

            int lastDigit = -1;
            for (int i = disk.size() -1; i > firstFreeSpace; i--) {
                if (compactDisk.get(i) != null) {
                    lastDigit = i;
                    break;
                }
            }
            if (lastDigit == -1) break;

            compactDisk.set(firstFreeSpace, compactDisk.get(lastDigit));
            compactDisk.set(lastDigit, null);
        }

        return compactDisk;
    }

    public static List<Integer> compactDiskByShiftingWholeFiles(List<Integer> disk) {
        List<Integer> compactDisk = new ArrayList<>(disk);
        int highestId = 0;

        for (int i = compactDisk.size() - 1; i > 0; i--) {
            if (compactDisk.get(i) != null) {
                highestId = compactDisk.get(i);
                break;
            }
        }

        for (int fileId = highestId; fileId > 0; fileId--) {
            int fileStart = compactDisk.indexOf(fileId);
            int fileEnd = fileStart;

            for (int i = fileStart; i < compactDisk.size(); i++) {
                if (!Objects.equals(compactDisk.get(i), fileId)) {
                    fileEnd = i - 1;
                    break;
                }
                fileEnd = i;
            }

            int fileLength = fileEnd - fileStart + 1;

            for (int i = 0; i <= fileStart; i++) {
                boolean fits = true;
                for (int j = 0; j < fileLength; j++) {
                    if (compactDisk.get(i + j) != null) {
                        fits = false;
                        break;
                    }
                }

                if (fits) {
                    for (int j = 0; j < fileLength; j++) {
                        compactDisk.set(i + j, fileId);
                        compactDisk.set(fileStart + j, null);
                    }
                    break;
                }
            }
        }

        return compactDisk;
    }

    private static List<Integer> parseToDigits(String input) {
        List<Integer> digits = new ArrayList<>();

        for (char c : input.toCharArray()) {
            digits.add(Character.getNumericValue(c));
        }
        return digits;
    }

}
