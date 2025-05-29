package Day7BridgeRepair;

import java.util.List;
import java.util.Map;

public class EquationOperatorChecker {
    public static void main(String[] args) {
        Long totalSum = 0L;

        PuzzleInput puzzleInput = new PuzzleInput();
        Map<Long, List<Integer>> equations = puzzleInput.getEquationsMap();

        for (Map.Entry<Long, List<Integer>> entry : equations.entrySet()) {
            Long targetNumber = entry.getKey();
            List<Integer> numbers = entry.getValue();

            if (correctEquation(targetNumber, numbers)) {
                totalSum += targetNumber;
            }
        }

        System.out.println("Total sum of the target numbers from the correct equations: " + totalSum);
    }

    public static boolean correctEquation(Long targetNumber, List<Integer> numbers) {
        int amountOfOperators = numbers.size() - 1;
        int totalCombinations = (int) Math.pow(2, amountOfOperators); // Aantal mogelijke combinaties van + en * tussen de getallen = 2^aantal_operators

        // Doorloop alle mogelijke combinaties van + en *
        for (int combination = 0; combination < totalCombinations; combination++) {
            Long solution = Long.valueOf(numbers.getFirst());

            for (int i = 0; i < amountOfOperators; i++) {
                int nextNumber = numbers.get(i + 1);

                // Bepaal welke operator je moet gebruiken op positie i:
                // (combination / 2^i) % 2 geeft 0 of 1 → 0 betekent: gebruik "+" en 1 betekent: gebruik "*"
                int operator = (combination / (int)Math.pow(2, i)) % 2;

                switch (operator) {
                    case 0:
                        solution += nextNumber;
                        break;
                    case 1:
                        solution *= nextNumber;
                        break;
                }
            }

            if (solution.equals(targetNumber)) {
                return true;
            }
        }

        return false;
    }
}
