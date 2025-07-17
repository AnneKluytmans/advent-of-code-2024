package Day11PlutonianPebbles;

import java.util.ArrayList;
import java.util.List;

public class PlutonianPebbleBlinkTransformer {
    public static void main(String[] args) {
        String puzzleInput = "0 44 175060 3442 593 54398 9 8101095";
        List<Long> pebbles = getPebbles(puzzleInput);

        int numberOfBlinks = 25;
        int pebbleCount = transformPebbles(pebbles, numberOfBlinks).size();
        System.out.println("Amount of pebbles after " + numberOfBlinks + " blinks: " + pebbleCount);
    }


    public record LongHalves(long left, long right) {}

    public static List<Long> transformPebbles(List<Long> pebbles, int numberOfBlinks) {
        List<Long> result = new ArrayList<>();
        int blinkCount = 0;

        while (blinkCount < numberOfBlinks) {
            result.clear();

            for (long pebble : pebbles) {
                int digitCount = countDigits(pebble);

                if (pebble == 0) {
                    result.add(1L);
                } else if (digitCount % 2 == 0) {
                    LongHalves halves = splitLong(pebble, digitCount);
                    result.add(halves.left);
                    result.add(halves.right);
                } else {
                    result.add(pebble * 2024);
                }
            }

            blinkCount++;
            pebbles = new ArrayList<>(result);
        }

        return pebbles;
    }

    private static List<Long> getPebbles(String puzzleInput) {
        List<Long> pebbles = new ArrayList<>();
        List<String> puzzleInputParts = List.of(puzzleInput.split(" "));

        for (String puzzleInputPart : puzzleInputParts) {
            pebbles.add(Long.parseLong(puzzleInputPart));
        }

        return pebbles;
    }

    private static int countDigits(long number) {
        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }

    private static LongHalves splitLong(long number, int digitCount) {
        int mid = digitCount / 2;

        String digits = String.valueOf(Math.abs(number));
        String leftDigits = digits.substring(0, mid).replaceFirst("^0+(?!$)", "");
        String rightDigits = digits.substring(mid).replaceFirst("^0+(?!$)", "");

        return new LongHalves(Long.parseLong(leftDigits), Long.parseLong(rightDigits));
    }
}
