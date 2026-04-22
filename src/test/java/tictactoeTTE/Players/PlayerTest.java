package tictactoeTTE.Players;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {

    PlayerFactory playerFactory = new PlayerFactory();

    Player humanPlayer;
    Player computerPlayer;
    String humanPlayerName = "Joe";
    String DEFAULT_HUMAN_SYMBOL = "X";
    String DEFAULT_COMPUTER_SYMBOL = "O";
    String computerDifficulty = "Simple";

    @Test
    void isHumanTest(){
        computerPlayer = playerFactory.createComputerPlayer(DEFAULT_COMPUTER_SYMBOL, computerDifficulty);

        assertFalse(computerPlayer.isHuman());
        assertTrue(computerPlayer.isComputer());
    }

    @Test
    void isComputerTest(){
        humanPlayer = playerFactory.createHumanPlayer(DEFAULT_HUMAN_SYMBOL, humanPlayerName);

        assertFalse(humanPlayer.isComputer());
        assertTrue(humanPlayer.isHuman());
    }
}
