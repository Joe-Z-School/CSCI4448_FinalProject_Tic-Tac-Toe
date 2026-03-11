package tictactoeTTE;

import tictactoeTTE.Players.Player;

public interface IMove {
    int[] getNextMove(GameBoard board, Player player);
}
