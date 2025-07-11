package Day9DiskFragmenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilesystemChecksumCalculator {

    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        List<Integer> digits = parseToDigits(puzzleInput.getPuzzleInput());

        List<Integer> initialDisk = createDiskLayout(digits);
        long checksumPart1 = calculateChecksum(compactDiskByShiftingFragments(initialDisk));
        long checksumPart2 = calculateChecksum(compactDiskByShiftingWholeFiles(initialDisk));

        System.out.println("Filesystem checksum part 1: " + checksumPart1);
        System.out.println("Filesystem checksum part 2: " + checksumPart2);
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
            for (int j = 0; j < digitBlocks.get(i); j++) {
                disk.add(i % 2 == 0 ? fileId : null);
            }
            if (i % 2 == 0) fileId++;
        }
        return disk;
    }

    public static List<Integer> compactDiskByShiftingFragments(List<Integer> originalDisk) {
        List<Integer> disk = new ArrayList<>(originalDisk);

        while (true) {
            int firstFreeSpace = getIndexOfFirstFreeSpace(disk);
            if (firstFreeSpace == -1) break;

            int lastFileFragment = getIndexOfLastFileFragment(disk, firstFreeSpace);
            if (lastFileFragment == -1) break;

            disk.set(firstFreeSpace, disk.get(lastFileFragment));
            disk.set(lastFileFragment, null);
        }

        return disk;
    }

    public static List<Integer> compactDiskByShiftingWholeFiles(List<Integer> initialDisk) {
        List<Integer> disk = new ArrayList<>(initialDisk);
        int highestFileId = getHighestFileId(initialDisk);

        for (int fileId = highestFileId; fileId > 0; fileId--) {
            int fileStart = disk.indexOf(fileId);
            int fileEnd = getIndexOfFileEnd(disk, fileId);
            if (fileEnd == -1) break;

            int fileLength = fileEnd - fileStart + 1;

            for (int i = 0; i <= fileStart; i++) {
                if (doesFileFit(disk, fileLength, i)) {
                    moveFile(disk, fileStart, fileLength, fileId, i);
                    break;
                }
            }
        }

        return disk;
    }

    private static List<Integer> parseToDigits(String input) {
        List<Integer> digits = new ArrayList<>();

        for (char c : input.toCharArray()) {
            digits.add(Character.getNumericValue(c));
        }
        return digits;
    }

    private static int getIndexOfFirstFreeSpace(List<Integer> disk) {
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == null) return i;
        }
        return -1;
    }

    private static int getIndexOfLastFileFragment(List<Integer> disk, int minIndex) {
        for (int i = disk.size() -1; i > minIndex; i--) {
            if (disk.get(i) != null) return i;
        }
        return -1;
    }

    private static int getHighestFileId(List<Integer> disk) {
        int indexLastFileFragment = getIndexOfLastFileFragment(disk, 0);
        return indexLastFileFragment == -1 ? -1 : disk.get(indexLastFileFragment);
    }

    private static int getIndexOfFileEnd(List<Integer> disk, int fileId) {
        for (int i = disk.size() -1; i > 0; i--) {
            if (Objects.equals(disk.get(i), fileId)) return i;
        }
        return -1;
    }

    private static boolean doesFileFit(List<Integer> disk, int fileLength, int start) {
        for (int i = 0; i < fileLength; i++) {
            if (disk.get(start + i) != null) return false;
        }
        return true;
    }

    private static void moveFile(List<Integer> disk, int fromIndex, int fileLength, int fileId, int toIndex) {
        for (int i = 0; i < fileLength; i++) {
            disk.set(toIndex + i, fileId);
            disk.set(fromIndex + i, null);
        }
    }
}
