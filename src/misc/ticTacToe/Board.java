package misc.ticTacToe;

import static misc.ticTacToe.TFrame.COLS;
import static misc.ticTacToe.TFrame.ROWS;

public class Board {
    private Mark[][] board; // Game board of ROWS-by-COLS cells

    Board() {
        board = new Mark[ROWS][COLS];
        reset();
    }

    // Return true if it is a draw (i.e., no more empty cell)
    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == Mark.EMPTY) {
                    return false; // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no more empty cell, it's a draw
    }

    // Return true if the player with "theMark" has won after placing at (rowSelected, colSelected)
    public boolean hasWon(Mark theMark, int rowSelected, int colSelected) {
        return ((board[rowSelected][0] == theMark      // 3-in-the-row
                && board[rowSelected][1] == theMark
                && board[rowSelected][2] == theMark) ||
                (board[0][colSelected] == theMark      // 3-in-the-column
                        && board[1][colSelected] == theMark
                        && board[2][colSelected] == theMark) ||
                (board[0][0] == theMark                // 3-in-the-diagonal
                        && board[1][1] == theMark
                        && board[2][2] == theMark) ||
                (board[0][2] == theMark                // 3-in-the-opposite-diagonal
                        && board[1][1] == theMark
                        && board[2][0] == theMark));
    }

    public Mark getMark(int row, int col) {
        return board[row][col];
    }

    public void setMark(int row, int col, Mark mark){
        board[row][col] = mark;
    }

    public void reset() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                setMark(row, col, Mark.EMPTY); // all cells empty
            }
        }
    }
}
