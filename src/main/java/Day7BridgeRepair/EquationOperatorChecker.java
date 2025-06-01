package Day7BridgeRepair;

import java.util.*;

public class EquationOperatorChecker {
    public static void main(String[] args) {
        long totalSumPartOne = 0;
        long totalSumPartTwo;

        PuzzleInput puzzleInput = new PuzzleInput();
        Map<Long, List<Long>> equations = puzzleInput.getEquationsMap();
        Map<Long, List<Long>> remainingEquations = new HashMap<>();

        for (Map.Entry<Long, List<Long>> entry : equations.entrySet()) {
            Long target = entry.getKey();
            List<Long> numbers = entry.getValue();

            if (isValidEquation(target, numbers, List.of('+', '*'))) {
                totalSumPartOne += target;
            } else {
                remainingEquations.put(target, numbers);
            }
        }

        totalSumPartTwo = totalSumPartOne;

        for (Map.Entry<Long, List<Long>> entry : remainingEquations.entrySet()) {
            Long target = entry.getKey();
            List<Long> numbers = entry.getValue();

            if (isValidEquation(target, numbers, List.of('+', '*', '|'))) {
                totalSumPartTwo += target;
            }
        }

        System.out.println("Total sum of the target numbers from the correct equations: " + totalSumPartOne);
        System.out.println("Total sum of the target numbers from the correct equations with the concatenation: " + totalSumPartTwo);
    }

    public static boolean isValidEquation(Long target, List<Long> numbers, List<Character> operators) {
        int operatorPositions = numbers.size() - 1;
        List<List<Character>> operatorCombinations = generateOperatorCombos(operatorPositions, operators);

        for (List<Character> combination : operatorCombinations) {
            if (evaluateOperators(numbers, combination) == target) {
                return true;
            }
        }

        return false;
    }

    public static long evaluateOperators(List<Long> numbers, List<Character> operators) {
        long result = numbers.getFirst();

        for (int i = 0; i < operators.size(); i++) {
            long next = numbers.get(i + 1);
            char operator = operators.get(i);

            if (operator == '+') {
                result += next;
            } else if (operator == '*') {
                result *= next;
            } else if (operator == '|') {
                result = Long.parseLong(String.valueOf(result) + next);
            }
        }

        return result;
    }

    public static List<List<Character>> generateOperatorCombos(int positions, List<Character> operators) {
        List<List<Character>> operatorCombinations = new ArrayList<>();
        generateOperatorCombosRecursive(positions, operators, new ArrayList<>(), operatorCombinations);
        return operatorCombinations;
    }

    public static void generateOperatorCombosRecursive(int positions, List<Character> operators, List<Character> current, List<List<Character>> operatorCombinations) {
        if (current.size() == positions) {
            operatorCombinations.add(new ArrayList<>(current));
            return;
        }

        for (char operator : operators) {
            current.add(operator);
            generateOperatorCombosRecursive(positions, operators, current, operatorCombinations);
            current.removeLast();
        }
    }
}
