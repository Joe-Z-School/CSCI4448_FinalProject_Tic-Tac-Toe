package tictactoeTTE.Movement;

import tictactoeTTE.GameBoard;
import tictactoeTTE.Players.Player;

import java.util.Random;

public class SimpleComputerMovement extends Movement{

    private final Random rand = new Random();

    public int[] getNextMove(GameBoard board, Player player) {
        return getAvailableMoves(board).get(rand.nextInt(getAvailableMoves(board).size()));
    }
}
