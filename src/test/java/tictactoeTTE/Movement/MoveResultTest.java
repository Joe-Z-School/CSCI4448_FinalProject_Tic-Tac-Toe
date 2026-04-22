package tictactoeTTE.Movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoeTTE.GameBoard;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class MoveResultTest {
    GameBoard gameBoard;
    PlayerFactory playerFactory;

    Player humanPlayer;
    String humanPlayerName = "Joe";
    String playerOneSymbol = "X";

    Player computerPlayer;
    String playerTwoSymbol = "O";
    String computerDifficulty = "Simple";

    int DEFAULT_BOARD_SIZE = 3;


    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(DEFAULT_BOARD_SIZE);
        playerFactory = new PlayerFactory();
        humanPlayer = playerFactory.createHumanPlayer(humanPlayerName, playerOneSymbol);
        computerPlayer = playerFactory.createComputerPlayer(playerTwoSymbol,computerDifficulty);
    }

    @Test
    void isSuccessfulMoveTest(){
        MoveResult moveMade = gameBoard.doMovement(1,1, humanPlayer);

        assertTrue(moveMade.isSuccessfulMove());
    }

    @Test
    void isSuccessfulMoveFailTest(){
        gameBoard.doMovement(1,1, humanPlayer);
        MoveResult failDuplicateMove = gameBoard.doMovement(1,1, humanPlayer);

        assertFalse(failDuplicateMove.isSuccessfulMove());
    }
}
