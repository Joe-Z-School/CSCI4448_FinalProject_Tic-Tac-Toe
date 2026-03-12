package tictactoeTTE;

import tictactoeTTE.Players.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Movement implements IMove{


    protected List<int[]> getAvailableMoves(GameBoard board){
        List<int[]> availableMoves = new ArrayList<>();
        String[][] layout = board.getBoardLayout();

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if (layout[i][j] == null) {
                    availableMoves.add(new int[]{i, j});
                }
            }
        }

        return availableMoves;
    };


    public abstract int[] getNextMove(GameBoard board, Player player);
}
