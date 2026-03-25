package tictactoeTTE.Movement;

import tictactoeTTE.GameBoard;
import tictactoeTTE.Players.Player;

public interface IMove {
    int[] getNextMove(GameBoard board, Player player);
}
