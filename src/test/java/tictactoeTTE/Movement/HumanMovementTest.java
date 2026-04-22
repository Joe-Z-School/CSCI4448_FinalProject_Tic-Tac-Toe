package tictactoeTTE.Movement;

import org.junit.jupiter.api.Test;
import tictactoeTTE.GameBoard;
import tictactoeTTE.Players.Human;
import tictactoeTTE.Players.Player;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanMovementTest {

    @Test
    public void testHumanMovement() {
        String simulatedInput = "0\n0\n";
        int gameBoardSize = 3;
        int[] simulatedMoveArray = new int[]{0,0};

        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

        HumanMovement humanMovement = new HumanMovement();
        GameBoard gameBoard = new GameBoard(gameBoardSize);
        Player humanPlayer = new Human("Joe", "X", humanMovement);

        int[] humanNextMove = humanMovement.getNextMove(gameBoard, humanPlayer);

        boolean wasHumanMoveSameAsSimulated = Arrays.equals(humanNextMove, simulatedMoveArray);

        assertTrue(wasHumanMoveSameAsSimulated);
    }
}
