package tictactoeTTE;

import org.junit.jupiter.api.Test;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TictactoeTest {
    String playerOneSymbol = "X";
    String playerTwoSymbol = "O";
    String computerDifficulty = "Smart";
    int DEFAULT_BOARD_SIZE = 3;
    PlayerFactory playerFactory = new PlayerFactory();
    Player playerOne = playerFactory.createComputerPlayer(playerOneSymbol,computerDifficulty);
    Player playerTwo = playerFactory.createComputerPlayer(playerTwoSymbol,computerDifficulty);
    GameBoard board = new GameBoard(DEFAULT_BOARD_SIZE);
    Tictactoe game = new Tictactoe(board, playerOne, playerTwo);


    @Test
    public void testTicTacToeWithTwoComputers() {

        while(true) {
            int[] playerOneMove = playerOne.getMove(board);
            game.handleMove(playerOneMove[0],playerOneMove[1]);
            board.displayBoard();
            if (game.isOver()) {
                break;
            }
            int[] playerTwoMove = playerTwo.getMove(board);
            game.handleMove(playerTwoMove[0],playerTwoMove[1]);
            board.displayBoard();
            if (game.isOver()) {
                break;
            }
        }

        Boolean gameIsOverResult = game.isOver();
        assert(gameIsOverResult);
    }

    @Test
    void testGetGameBoard() {
        assertEquals(board, game.getGameBoard());
    }

    @Test
    void testComputerSleepTimer() throws InterruptedException {
        board.doMovement(0,0, playerOne);
        board.doMovement(2,2, playerTwo);

        game.handleMove(0,1);

        Thread.sleep(3000);

        game.getGameBoard().displayBoard();

        String[][] gameBoardLayout = board.getBoardLayout();

        assertEquals(playerTwo.getSymbol(), gameBoardLayout[0][2]);
    }
}
