package Day4CeresSearch;

public class WordSearchXMas {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] puzzleBoard = puzzleInput.createPuzzleBoard();

        int count = countXMasOccurrences(puzzleBoard);
        System.out.println("Total occurrences of X-MAS: " + count);
    }

    public static int countXMasOccurrences(char[][] puzzleBoard) {
        int rows = puzzleBoard.length;
        int cols = puzzleBoard[0].length;
        int count = 0;

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (puzzleBoard[row][col] == 'A') {
                    if (isXMAS(puzzleBoard, row, col)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static boolean isXMAS(char[][] puzzleBoard, int row, int col) {
        return (matchesPattern(puzzleBoard, row - 1, col - 1, 'M', row + 1, col + 1, 'S') &&
                matchesPattern(puzzleBoard, row - 1, col + 1, 'M', row + 1, col - 1, 'S')) ||
                (matchesPattern(puzzleBoard, row - 1, col - 1, 'S', row + 1, col + 1, 'M') &&
                matchesPattern(puzzleBoard, row - 1, col + 1, 'S', row + 1, col - 1, 'M')) ||
                (matchesPattern(puzzleBoard, row - 1, col - 1, 'M', row + 1, col + 1, 'S') &&
                matchesPattern(puzzleBoard, row + 1, col - 1, 'M', row - 1, col + 1, 'S')) ||
                (matchesPattern(puzzleBoard, row - 1, col - 1, 'S', row + 1, col + 1, 'M') &&
                matchesPattern(puzzleBoard, row + 1, col - 1, 'S', row - 1, col + 1, 'M'));
    }

    public static boolean matchesPattern(char[][] puzzleBoard, int r1, int c1, char char1, int r2, int c2, char char2) {
        return isInBounds(puzzleBoard, r1, c1) && isInBounds(puzzleBoard, r2, c2) &&
                puzzleBoard[r1][c1] == char1 && puzzleBoard[r2][c2] == char2;
    }

    public static boolean isInBounds(char[][] puzzleBoard, int row, int col) {
        return row >= 0 && row < puzzleBoard.length && col >= 0 && col < puzzleBoard[0].length;
    }

}
