package tictactoeTTE.Players;

import tictactoeTTE.GameBoard;
import tictactoeTTE.Movement;

public abstract class Player {
    private String playerName;
    private String playerSymbol;
    private Movement movementStyle;


    public Player(String playerName, String symbol, Movement movementStyle){
        this.playerName = playerName;
        this.playerSymbol = symbol;
        this.movementStyle = movementStyle;
    }

    public void takeTurn(GameBoard gameBoard){
        int[] nextMove = movementStyle.getNextMove(gameBoard, this);

        Boolean moveSuccessful = gameBoard.doMovement(nextMove[0], nextMove[1], this);
    }

    public String getSymbol(){ return playerSymbol; }
    public String getPlayerName(){ return playerName; }
}
