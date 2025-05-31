package Day7BridgeRepair;

import java.util.*;

public class EquationOperatorChecker {
    public static void main(String[] args) {
        Long totalSumPartOne = 0L;
        Long totalSumPartTwo = 0L;

        PuzzleInput puzzleInput = new PuzzleInput();
        Map<Long, List<Long>> equations = puzzleInput.getEquationsMap();

        for (Map.Entry<Long, List<Long>> entry : equations.entrySet()) {
            Long targetNumber = entry.getKey();
            List<Long> numbers = entry.getValue();

            if (isCorrectEquation(targetNumber, numbers, 1, numbers.getFirst())) {
                totalSumPartOne += targetNumber;
            }

            if (isCorrectEquationWithConcat(targetNumber, numbers, 1, numbers.getFirst(), numbers.getFirst(), '-')) {
                totalSumPartTwo += targetNumber;
            }
        }

        System.out.println("Total sum of the target numbers from the correct equations: " + totalSumPartOne);
        System.out.println("Total sum of the target numbers from the correct equations with the concatenation: " + totalSumPartTwo);
    }

    public static boolean isCorrectEquation(Long targetNumber, List<Long> numbers, int index, Long currentTotal) {
        if (index == numbers.size()) {
            return currentTotal.equals(targetNumber);
        }

        Long nextNumber = numbers.get(index);

        if (isCorrectEquation(targetNumber, numbers, index + 1, currentTotal + nextNumber)) {
            return true;
        }

        if (isCorrectEquation(targetNumber, numbers, index + 1, currentTotal * nextNumber)) {
            return true;
        }

        return false;
    }

    public static boolean isCorrectEquationWithConcat(Long targetNumber, List<Long> numbers, int index, Long currentTotal, Long previousNumber, char previousOperator) {
        if (index == numbers.size()) {
            return currentTotal.equals(targetNumber);
        }

        Long nextNumber = numbers.get(index);

        if (isCorrectEquationWithConcat(targetNumber, numbers, index + 1, currentTotal + nextNumber, nextNumber, '+')) {
            return true;
        }

        if (isCorrectEquationWithConcat(targetNumber, numbers, index + 1, currentTotal * nextNumber, nextNumber, '*')) {
            return true;
        }

        long concatNumber = Long.parseLong(String.valueOf(previousNumber) + nextNumber);
        long adjustedTotal;
        if (previousOperator == '+') {
            adjustedTotal = currentTotal - previousNumber + concatNumber;
        } else if (previousOperator == '*') {
            if (previousNumber == 0) {
                return false;
            }
            adjustedTotal = (currentTotal / previousNumber) * concatNumber;
        } else {
            adjustedTotal = concatNumber;
        }

        if (isCorrectEquationWithConcat(targetNumber, numbers, index + 1, adjustedTotal, concatNumber, previousOperator)) {
            return true;
        }

        return false;
    }
}
