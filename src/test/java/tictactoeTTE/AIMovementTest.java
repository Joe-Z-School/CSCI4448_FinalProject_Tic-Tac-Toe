package tictactoeTTE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AIMovementTest {

    GameBoard gameBoard;
    PlayerFactory playerFactory;

    String humanPlayerName = "Joe";
    String playerOneSymbol = "X";
    String playerTwoSymbol = "O";
    Player computerPlayer;
    Player humanPlayer;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
        playerFactory = new PlayerFactory();
        humanPlayer = playerFactory.createHumanPlayer(humanPlayerName, playerOneSymbol);
        computerPlayer = playerFactory.createComputerPlayer(playerTwoSymbol);
    }

    @Test
    void computerBlocksHumanTest(){
        gameBoard.doMovement(0,0, humanPlayer);
        gameBoard.doMovement(1,0, computerPlayer);
        gameBoard.doMovement(0,1, humanPlayer);

        gameBoard.displayBoard();

        computerPlayer.takeTurn(gameBoard);

        gameBoard.displayBoard();

        String[][] boardLayout = gameBoard.getBoardLayout();

        assertEquals(computerPlayer.getSymbol(), boardLayout[0][2]);
    }

    @Test
    void computerPrioritiesWinTest(){
        gameBoard.doMovement(1,1, humanPlayer);
        gameBoard.doMovement(2,0, computerPlayer);
        gameBoard.doMovement(0,1, humanPlayer);
        gameBoard.doMovement(2,1, computerPlayer);
        gameBoard.doMovement(0,0, humanPlayer);

        gameBoard.displayBoard();

        computerPlayer.takeTurn(gameBoard);

        gameBoard.displayBoard();

        String[][] boardLayout = gameBoard.getBoardLayout();

        assertEquals(computerPlayer.getSymbol(), boardLayout[2][2]);

    }

    @Test
    void computerPicksFirstEmptySpotTest(){
        gameBoard.doMovement(1,1, humanPlayer);
        gameBoard.displayBoard();

        computerPlayer.takeTurn(gameBoard);
        gameBoard.displayBoard();
        String[][] boardLayout = gameBoard.getBoardLayout();
        assertEquals(computerPlayer.getSymbol(), boardLayout[0][0]);
    }


}