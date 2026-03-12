package tictactoeTTE;

import org.junit.jupiter.api.Test;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void testConstructor() {
        GameBoard gameBoard = new GameBoard();
        String[][] gameBoardLayout = gameBoard.getBoardLayout();
        int counter = 0;
        for (int row = 0; row < gameBoardLayout.length; row++) {
            for (int col = 0; col < gameBoardLayout[row].length; col++) {
                if (gameBoardLayout[row][col] == null){
                    counter++;
                }
            }
        }
        int expectedCounter = 9;
        gameBoard.displayBoard();

        assertEquals(expectedCounter, counter);
    }

    @Test
    void doMovementTest(){
        GameBoard gameBoard = new GameBoard();
        PlayerFactory playerFactory = new PlayerFactory();
        Player playerOne = playerFactory.createHumanPlayer("Joe", "X");
        gameBoard.displayBoard();

        gameBoard.doMovement(0,0,playerOne);
        gameBoard.doMovement(1,0,playerOne);
        gameBoard.doMovement(1,1,playerOne);
        gameBoard.displayBoard();

        gameBoard.doMovement(2,1,playerOne);
        gameBoard.displayBoard();

        String[][]  gameBoardLayout = gameBoard.getBoardLayout();

        assertTrue(gameBoardLayout[0][0] == null);

    }

}