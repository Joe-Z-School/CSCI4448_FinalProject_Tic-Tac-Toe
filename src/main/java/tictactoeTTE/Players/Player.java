package tictactoeTTE.Players;

import tictactoeTTE.GameBoard;
import tictactoeTTE.Movement;

import java.util.LinkedList;

public abstract class Player {
    private String playerName;
    private String playerSymbol;
    private Movement movementStyle;
    private LinkedList<int[]> moveHistory;


    public Player(String playerName, String symbol, Movement movementStyle){
        this.playerName = playerName;
        this.playerSymbol = symbol;
        this.movementStyle = movementStyle;
        this.moveHistory = new LinkedList<>();
    }

    public void takeTurn(GameBoard gameBoard){
        int[] nextMove = movementStyle.getNextMove(gameBoard, this);

        if (moveHistory.size() == 3){
            // Get oldest movement (head of list)
            // Update observer to display special symbol on board at oldest symbol location
            // Get next requested location
            // Remove oldest movement
            // Update observer
        }
        else{
            // Get next requested movement
            // Update observer to update board display
        }
    }
}
