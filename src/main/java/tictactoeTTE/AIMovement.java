package tictactoeTTE;

import tictactoeTTE.Players.Player;

import java.util.LinkedList;
import java.util.List;

public class AIMovement extends Movement {


    public int[] getNextMove(GameBoard board, Player playerMoving) {
        return null;
        /*
        Have the computer collect available spots
        Know which spots have been taken by the computer
        Know which spot will be removed upon placing next symbol if any
        Determine if computer can win horizontally
        Determine if computer can win vertically
        Determine if computer can win right to left diagonally
        Determine if computer can win left to right diagonally
         */
    }

    private List<int[]> getAvailableMoves(){
        List<int[]> availableMoves = new LinkedList<>();

        return availableMoves;
    }
}
