package tictactoeTTE.Movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoeTTE.GameBoard;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleComputerMovementTest {
    GameBoard gameBoard;
    PlayerFactory playerFactory;

    String humanPlayerName = "Joe";
    String playerOneSymbol = "X";
    String playerTwoSymbol = "O";
    String computerDifficulty = "Simple";
    Player computerPlayer;
    Player humanPlayer;
    int DEFAULT_BOARD_SIZE = 3;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(DEFAULT_BOARD_SIZE);
        playerFactory = new PlayerFactory();
        humanPlayer = playerFactory.createHumanPlayer(humanPlayerName, playerOneSymbol);
        computerPlayer = playerFactory.createComputerPlayer(playerTwoSymbol,computerDifficulty);
    }

    @Test
    void computerMovesRandomly(){

    }

}
