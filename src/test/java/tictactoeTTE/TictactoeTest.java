package tictactoeTTE;

import org.junit.jupiter.api.Test;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

public class TictactoeTest {
    String playerOneSymbol = "X";
    String playerTwoSymbol = "O";

    @Test
    public void testTicTacToeWithTwoComputers() {
        PlayerFactory playerFactory = new PlayerFactory();
        Player playerOne = playerFactory.createComputerPlayer(playerOneSymbol);
        Player playerTwo = playerFactory.createComputerPlayer(playerTwoSymbol);
        GameBoard board = new GameBoard();
        Tictactoe game = new Tictactoe(board, playerOne, playerTwo);

        game.play();

        Boolean gameIsOverResult = game.isOver();

        assert(gameIsOverResult);
    }
}
