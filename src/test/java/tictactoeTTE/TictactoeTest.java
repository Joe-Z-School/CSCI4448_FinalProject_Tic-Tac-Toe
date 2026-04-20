package tictactoeTTE;

import org.junit.jupiter.api.Test;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

public class TictactoeTest {
    String playerOneSymbol = "X";
    String playerTwoSymbol = "O";
    String computerDifficulty = "Smart";
    int DEFAULT_BOARD_SIZE = 3;


    @Test
    public void testTicTacToeWithTwoComputers() {
        PlayerFactory playerFactory = new PlayerFactory();
        Player playerOne = playerFactory.createComputerPlayer(playerOneSymbol,computerDifficulty);
        Player playerTwo = playerFactory.createComputerPlayer(playerTwoSymbol,computerDifficulty);
        GameBoard board = new GameBoard(DEFAULT_BOARD_SIZE);
        Tictactoe game = new Tictactoe(board, playerOne, playerTwo);


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
}
