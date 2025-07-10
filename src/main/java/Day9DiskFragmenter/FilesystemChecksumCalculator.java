package Day9DiskFragmenter;

import java.util.ArrayList;
import java.util.List;

public class FilesystemChecksumCalculator {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        List<Integer> digits = toDigitsList(puzzleInput.getPuzzleInput());

        Long checksum = calculateChecksum(digits);
        System.out.println("Filesystem checksum: " + checksum);
    }

    public static Long calculateChecksum(List<Integer> digits) {
        long checksum = 0L;
        List<Integer> sortedDigitBlocks = toSortedDigitBlocks(digits);
        List<Integer> parsedDigits = backFillDotsWithDigits(sortedDigitBlocks);

        for (int i = 0; i < parsedDigits.size(); i++) {
            checksum += parsedDigits.get(i) * i;
        }
        return checksum;
    }

    private static List<Integer> toSortedDigitBlocks(List<Integer> digits) {
        List<Integer> blocks = new ArrayList<>();
        int fileId = 0;

        for (int i = 0; i < digits.size(); i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < digits.get(i); j++) {
                    blocks.add(fileId);
                }
                fileId++;
            } else {
                for (int j = 0; j < digits.get(i); j++) {
                    blocks.add(null);
                }
            }
        }
        return blocks;
    }

    public static List<Integer> backFillDotsWithDigits(List<Integer> digitBlocks) {
        List<Integer> blocks = new ArrayList<>(digitBlocks);

        while (true) {
            int firstDot = -1;
            for (int i = 0; i < blocks.size(); i++) {
                if (blocks.get(i) == null) {
                    firstDot = i;
                    break;
                }
            }
            if (firstDot == -1) break;

            int lastDigit = -1;
            for (int i = blocks.size() -1; i > firstDot; i--) {
                if (blocks.get(i) != null) {
                    lastDigit = i;
                    break;
                }
            }
            if (lastDigit == -1) break;

            blocks.set(firstDot, blocks.get(lastDigit));
            blocks.set(lastDigit, null);
        }

        List<Integer> result = new ArrayList<>();
        for (Integer n : blocks) {
            if (n != null) {
                result.add(n);
            }
        }

        return result;
    }

    public static List<Integer> toDigitsList(String input) {
        List<Integer> digitsList = new ArrayList<>();

        for (char c : input.toCharArray()) {
            digitsList.add(Character.getNumericValue(c));
        }
        return digitsList;
    }

}
