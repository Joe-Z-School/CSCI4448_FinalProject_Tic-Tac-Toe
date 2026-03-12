package tictactoeTTE;

import org.junit.jupiter.api.Test;

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

        assertEquals(expectedCounter, counter);
    }

}