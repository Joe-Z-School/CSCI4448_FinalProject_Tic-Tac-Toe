package tictactoeTTE.Players;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerFactoryTest {

    PlayerFactory playerFactory = new PlayerFactory();

    @Test
    void createHumanPlayerTest() {
        String playerOneName = "playerOne";
        String playerOneSymbol = "X";
        Player humanPlayer = playerFactory.createHumanPlayer(playerOneName, playerOneSymbol);

        assertEquals(playerOneName, humanPlayer.getPlayerName());
    }

    @Test
    void createComputerPlayerTest() {
        String expectedComputerName = "Computer";
        String compPlayerSymbol = "O";
        String computerDifficulty = "Simple";
        Player computerPlayer = playerFactory.createComputerPlayer(compPlayerSymbol,computerDifficulty);

        assertEquals(expectedComputerName, computerPlayer.getPlayerName());
    }
}