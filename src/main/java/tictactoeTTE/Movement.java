package tictactoeTTE;

import tictactoeTTE.Players.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Movement implements IMove{


    protected List<int[]> getAvailableMoves(GameBoard board){
        List<int[]> availableMoves = new ArrayList<>();
        // Get spots available
        return availableMoves;
    };


    public abstract int[] getNextMove(GameBoard board, Player player);
}
