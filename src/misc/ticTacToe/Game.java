package misc.ticTacToe;

import javax.swing.*;

import static misc.ticTacToe.TFrame.COLS;
import static misc.ticTacToe.TFrame.ROWS;

public class Game {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }

    public final TFrame frame;
    //----------------------------------------------------------------------
    // INSTANCE VARIABLES for game play

    private GameState currentState;  // the current game state
    private Mark currentPlayer;  // the current player
    private Board board;


    //----------------------------------------------------------------------
    // CONSTRUCTOR

    private Game() {
        frame = new TFrame(this);
        board = new Board();
        restartGame(); // initialize the game board contents and game variables
    }


    // Initialize the game-board contents and the status
    private void restartGame() {
        board.reset();
        currentState = GameState.PLAYING; // ready to play
        currentPlayer = Mark.CROSS;       // cross plays first
    }

    // If game is not a win or draw yet and this is a valid move, make the move and update the game state.
    //  Add code here to automatically make the opponent's move, if the currentState is still GameState.PLAYING
    public void makeMoveOrRestart( int rowSelected, int colSelected ) {
        if (currentState == GameState.PLAYING) {
            if (validMove(rowSelected,colSelected)) {
                currentState = getNextGameState(currentPlayer, rowSelected, colSelected);
                switchPlayer();
            }
        } else {       // game over
            restartGame(); // restart the game
        }
    }

    // Is row,col a valid move?
    private boolean validMove(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS && board.getMark(row, col) == Mark.EMPTY;
    }

    // Change to next player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == Mark.CROSS) ? Mark.NOUGHT : Mark.CROSS;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public Mark getCurrentPlayer() {
        return currentPlayer;
    }

    public Mark getMark(int row, int col){
        return board.getMark(row, col);
    }

    // Make the move and update the state of the game.
    private GameState getNextGameState(Mark theMark, int rowSelected, int colSelected) {
        board.setMark(rowSelected, colSelected, theMark); // Make the move
        if (board.hasWon(theMark, rowSelected, colSelected)) {  // check for win
            return (theMark == Mark.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) {  // check for draw
            return GameState.DRAW;
        }
        return currentState;
    }
}
