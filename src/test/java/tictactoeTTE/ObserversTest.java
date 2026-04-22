package tictactoeTTE;

import org.junit.jupiter.api.Test;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;
import tictactoeTTE.ui.SwingTicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;

public class ObserversTest {
    PlayerFactory playerFactory = new PlayerFactory();

    @Test
    void testRegisterObserver(){
        int sizeOfBoard = 3;
        int expectedCountOfObservers = 1;
        GameBoard board = new GameBoard(sizeOfBoard);
        Player player1 = playerFactory.createHumanPlayer("Joe", "X");
        Player player2 = playerFactory.createComputerPlayer("O", "Simple");
        Tictactoe game = new Tictactoe(board, player1, player2);

        SwingTicTacToe gameUI = new SwingTicTacToe(game, sizeOfBoard, true, player1, player2);

        game.sendMoveSignal(0,0, player1);

        assertEquals(expectedCountOfObservers, game.getObservers().size());

    }

    @Test
    void testRemoveObserver(){
        int sizeOfBoard = 3;
        int expectedCountOfObservers = 0;
        GameBoard board = new GameBoard(sizeOfBoard);
        Player player1 = playerFactory.createHumanPlayer("Joe", "X");
        Player player2 = playerFactory.createComputerPlayer("O", "Simple");
        Tictactoe game = new Tictactoe(board, player1, player2);

        SwingTicTacToe gameUI = new SwingTicTacToe(game, sizeOfBoard, true, player1, player2);

        game.sendMoveSignal(0,0, player1);

        game.removeObserver(gameUI);

        assertEquals(expectedCountOfObservers, game.getObservers().size());

    }

}
