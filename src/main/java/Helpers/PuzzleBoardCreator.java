package Helpers;

public class PuzzleBoardCreator {
    public static char[][] createPuzzleBoard(String puzzleInput) {
        String[] puzzleBoardRows = puzzleInput.split("\n");
        int rows = puzzleBoardRows.length;
        int cols = puzzleBoardRows[0].length();

        char[][] board = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            board[i] = puzzleBoardRows[i].toCharArray();
        }

        return board;
    }
}
