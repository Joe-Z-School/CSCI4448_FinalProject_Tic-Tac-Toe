package tictactoeTTE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

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
    void testConstructor() {
        String[][] gameBoardLayout = gameBoard.getBoardLayout();
        int boardCellCounter = 0;
        for (int row = 0; row < gameBoardLayout.length; row++) {
            for (int col = 0; col < gameBoardLayout[row].length; col++) {
                if (gameBoardLayout[row][col] == null){
                    boardCellCounter++;
                }
            }
        }
        int expectedCellCount = 9;
        gameBoard.displayBoard();

        assertEquals(expectedCellCount, boardCellCounter);
    }

    @Test
    void doMovementTest(){
        gameBoard.doMovement(0,0, humanPlayer);
        gameBoard.displayBoard();

        String[][] gameBoardLayout = gameBoard.getBoardLayout();

        assertEquals(humanPlayer.getSymbol(), gameBoardLayout[0][0]);

    }

    @Test
    void isValidMoveTest(){
        gameBoard.doMovement(0,0, humanPlayer);
        gameBoard.displayBoard();

        Boolean occupiedSpot = gameBoard.isValidSpot(0,0);
        Boolean outsideBoardSpot = gameBoard.isValidSpot(4,4);
        Boolean validSpot = gameBoard.isValidSpot(1,1);

        assertFalse(occupiedSpot);
        assertFalse(outsideBoardSpot);
        assertTrue(validSpot);

    }

    @Test
    void playerFirstSymbolRemovedTest(){
        gameBoard.displayBoard();

        gameBoard.doMovement(0,0,humanPlayer);
        gameBoard.doMovement(1,0,humanPlayer);
        gameBoard.doMovement(1,1,humanPlayer);
        gameBoard.displayBoard();

        gameBoard.doMovement(2,1,humanPlayer);
        gameBoard.displayBoard();

        String[][]  gameBoardLayout = gameBoard.getBoardLayout();

        assertNull(gameBoardLayout[0][0]);
    }

    @Test
    void checkForRowWinnerTest(){
        gameBoard.displayBoard();
        gameBoard.doMovement(0,0,humanPlayer);
        gameBoard.doMovement(0,1,humanPlayer);
        gameBoard.doMovement(0,2,humanPlayer);
        gameBoard.displayBoard();

        assertTrue(gameBoard.checkIfWinner(humanPlayer));
        assertFalse(gameBoard.checkIfWinner(computerPlayer));
    }

    @Test
    void checkForColumnWinnerTest(){
        gameBoard.displayBoard();
        gameBoard.doMovement(0,1,humanPlayer);
        gameBoard.doMovement(1,1,humanPlayer);
        gameBoard.doMovement(2,1,humanPlayer);
        gameBoard.displayBoard();

        assertTrue(gameBoard.checkIfWinner(humanPlayer));
        assertFalse(gameBoard.checkIfWinner(computerPlayer));
    }

    @Test
    void checkForDiagLtoRWinnerTest(){
        gameBoard.displayBoard();
        gameBoard.doMovement(0,0,humanPlayer);
        gameBoard.doMovement(1,1,humanPlayer);
        gameBoard.doMovement(2,2,humanPlayer);
        gameBoard.displayBoard();

        assertTrue(gameBoard.checkIfWinner(humanPlayer));
        assertFalse(gameBoard.checkIfWinner(computerPlayer));
    }

    @Test
    void checkForDiagRtoLWinnerTest(){
        gameBoard.displayBoard();
        gameBoard.doMovement(0,2,humanPlayer);
        gameBoard.doMovement(1,1,humanPlayer);
        gameBoard.doMovement(2,0,humanPlayer);
        gameBoard.displayBoard();

        assertTrue(gameBoard.checkIfWinner(humanPlayer));
        assertFalse(gameBoard.checkIfWinner(computerPlayer));
    }

}