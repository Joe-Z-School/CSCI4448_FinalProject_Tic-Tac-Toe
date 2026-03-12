package tictactoeTTE;

import tictactoeTTE.Players.Player;

public class Tictactoe {

    private Boolean gameIsOver = false;
    private GameBoard board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    public Tictactoe(GameBoard board, Player playerOne, Player playerTwo) {
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne; // Default to player one starting
    }

    public void play() {
        while (!gameIsOver) {
            board.displayBoard();
            currentPlayer.takeTurn(board);
            if (board.checkIfWinner(currentPlayer)) {
                gameIsOver = true;
                System.out.println("GAME OVER");
                System.out.println("Winner: " + currentPlayer);
            } else {
                switchTurn();
            }
        }
    }

    public Boolean isOver(){ return gameIsOver; }

    public Player getCurrentPlayer(){ return currentPlayer; }

    private void switchTurn() {
        currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;
    }

}