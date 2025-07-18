package Day11PlutonianPebbles;

import java.util.*;

public class PlutonianPebbleBlinkTransformer {

    private static final Map<Long, List<Long>> pebbleCache = new HashMap<>();

    public static void main(String[] args) {
        String puzzleInput = "0 44 175060 3442 593 54398 9 8101095";
        List<Long> pebbles = getPebbles(puzzleInput);

        int numberOfBlinks = 75;
        long pebbleCount = countPebblesAfterBlinks(pebbles, numberOfBlinks);

        System.out.println("Amount of pebbles after " + numberOfBlinks + " blinks: " + pebbleCount);
    }

    public static long countPebblesAfterBlinks(List<Long> pebbles, int numberOfBlinks) {
        long totalPebbles = 0L;

        Map<Long, Long> counts = new HashMap<>();
        for (long pebble : pebbles) {
            Long currCount = counts.get(pebble);
            if (currCount == null) {
                counts.put(pebble, 1L);
            } else {
                counts.put(pebble, currCount + 1);
            }
        }

        for (int i = 0; i < numberOfBlinks; i++) {
            Map<Long, Long> nextCounts = new HashMap<>();

            for (Map.Entry<Long, Long> entry : counts.entrySet()) {
                long pebble = entry.getKey();
                long count = entry.getValue();

                List<Long> transformed = getTransformedPebbles(pebble);

                for (Long newPebble : transformed) {
                    nextCounts.merge(newPebble, count, Long::sum);
                }
            }
            counts = nextCounts;
        }

        for (Long count : counts.values()) {
            totalPebbles += count;
        }
        return totalPebbles;
    }

    public static List<Long> getTransformedPebbles(long pebble) {
        if (pebbleCache.containsKey(pebble)) {
            return pebbleCache.get(pebble);
        }
        List<Long> transformedPebbles = new ArrayList<>();
        int digitCount = countDigits(pebble);

        if (pebble == 0) {
            transformedPebbles.add(1L);
        } else if (digitCount % 2 == 0) {
            LongHalves halves = splitLong(pebble, digitCount);
            transformedPebbles.add(halves.left);
            transformedPebbles.add(halves.right);
        } else  {
            transformedPebbles.add(pebble * 2024);
        }

        pebbleCache.put(pebble, transformedPebbles);
        return transformedPebbles;
    }


    private static List<Long> getPebbles(String puzzleInput) {
        List<Long> pebbles = new ArrayList<>();
        String[] parts = puzzleInput.split(" ");

        for (String part : parts) {
            pebbles.add(Long.parseLong(part));
        }

        return pebbles;
    }

    private static int countDigits(long number) {
        if (number == 0) return 1;
        return (int) Math.log10(Math.abs(number)) + 1;
    }

    private static LongHalves splitLong(long number, int digitCount) {
        number = Math.abs(number);
        int mid = digitCount / 2;

        long divider = (long) Math.pow(10, digitCount - mid);
        long left = number / divider;
        long right = number % divider;

        return new LongHalves(left, right);
    }

    public record LongHalves(long left, long right) {}
}
