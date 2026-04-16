package tictactoeTTE.Players;

import tictactoeTTE.GameBoard;
import tictactoeTTE.Movement.MoveResult;
import tictactoeTTE.Movement.Movement;

public abstract class Player {
    private String playerName;
    private String playerSymbol;
    private Movement movementStyle;


    public Player(String playerName, String symbol, Movement movementStyle){
        this.playerName = playerName;
        this.playerSymbol = symbol;
        this.movementStyle = movementStyle;
    }

    public int[] getMove(GameBoard gameBoard){
        return movementStyle.getNextMove(gameBoard, this);
    }

    public String getSymbol(){ return playerSymbol; }
    public String getPlayerName(){ return playerName; }

    public boolean isHuman(){ return false; }

    public boolean isComputer(){return false; }
}
