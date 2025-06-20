package Day4CeresSearch;

public class WordSearch {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] puzzleBoard = puzzleInput.getPuzzleBoard();

//        for (char[] row : puzzleBoard) {
//            System.out.println(Arrays.toString(row));
//        }

        String puzzleWord = "XMAS";
        int count = countOccurrences(puzzleBoard, puzzleWord);
        System.out.println("Total occurrences of '" + puzzleWord + "': " + count);
    }

    public static int countOccurrences(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (checkDirection(board, row, col, word, 0, 1)) {
                    count++;
                }
                if (checkDirection(board, row, col, word, 0, -1)) {
                    count++;
                }
                if (checkDirection(board, row, col, word, 1, 0)) {
                    count++;
                }
                if (checkDirection(board, row, col, word, -1, 0)) {
                    count++;
                }
                if (checkDirection(board, row, col, word, 1, 1)) {
                    count++;
                }
                if (checkDirection(board, row, col, word, 1, -1)) {
                    count++;
                }
                if (checkDirection(board, row, col, word, -1, 1)) {
                    count++;
                }
                if (checkDirection(board, row, col, word, -1, -1)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static boolean checkDirection(char[][] puzzleBoard, int row, int col, String word, int rowDir, int colDir) {
        int wordLength = word.length();

        for (int i = 0; i < wordLength; i++) {
            int newRow = row + i * rowDir;
            int newCol = col + i * colDir;

            if (!isInBounds(puzzleBoard, newRow, newCol) || puzzleBoard[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isInBounds(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

}
