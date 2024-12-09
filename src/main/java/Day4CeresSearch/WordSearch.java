package Day4CeresSearch;

public class WordSearch {
    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInput();
        char[][] puzzleBoard = puzzleInput.createPuzzleBoard();

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
                if (checkRight(board, row, col, word)) {
                    count++;
                }
                if (checkLeft(board, row, col, word)) {
                    count++;
                }
                if (checkDown(board, row, col, word)) {
                    count++;
                }
                if (checkUp(board, row, col, word)) {
                    count++;
                }
                if (checkDiagonalDownRight(board, row, col, word)) {
                    count++;
                }
                if (checkDiagonalDownLeft(board, row, col, word)) {
                    count++;
                }
                if (checkDiagonalUpRight(board, row, col, word)) {
                    count++;
                }
                if (checkDiagonalUpLeft(board, row, col, word)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static boolean checkRight(char[][] board, int row, int col, String word) {
        int wordLength = word.length();
        if (col + wordLength > board[0].length) {
            return false;
        }
        for (int i = 0; i < wordLength; i++) {
            if (board[row][col + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkLeft(char[][] board, int row, int col, String word) {
        int wordLength = word.length();
        if (col - wordLength < -1) {
            return false;
        }
        for (int i = 0; i < wordLength; i++) {
            if (board[row][col - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDown(char[][] board, int row, int col, String word) {
        int wordLength = word.length();
        if (row + wordLength > board.length) {
            return false;
        }
        for (int i = 0; i < wordLength; i++) {
            if (board[row + i][col] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkUp(char[][] board, int row, int col, String word) {
        int wordLength = word.length();
        if (row - wordLength < -1) {
            return false;
        }
        for (int i = 0; i < wordLength; i++) {
            if (board[row - i][col] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonalDownRight(char[][] board, int row, int col, String word) {
        int wordLength = word.length();
        if (row + wordLength > board.length || col + wordLength > board[0].length) {
            return false;
        }
        for (int i = 0; i < wordLength; i++) {
            if (board[row + i][col + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonalDownLeft(char[][] board, int row, int col, String word) {
        int wordLength = word.length();
        if (row + wordLength > board.length || col - wordLength < -1) {
            return false;
        }
        for (int i = 0; i < wordLength; i++) {
            if (board[row + i][col - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonalUpRight(char[][] board, int row, int col, String word) {
        int wordLength = word.length();
        if (row - wordLength < -1 || col + wordLength > board[0].length) {
            return false;
        }
        for (int i = 0; i < wordLength; i++) {
            if (board[row - i][col + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonalUpLeft(char[][] board, int row, int col, String word) {
        int wordLength = word.length();
        if (row - wordLength < -1 || col - wordLength < -1) {
            return false;
        }
        for (int i = 0; i < wordLength; i++) {
            if (board[row - i][col - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
