package tictactoeTTE.Players;

import tictactoeTTE.Movement.AIMovement;
import tictactoeTTE.Movement.HumanMovement;

public class PlayerFactory {
    private final String COMPUTER_DEFAULT_NAME = "Computer";

    public Player createHumanPlayer(String playerName, String symbol) {
            return new Human(playerName, symbol, new HumanMovement());
    }

    public Player createComputerPlayer(String symbol){
        return new Computer(COMPUTER_DEFAULT_NAME, symbol, new AIMovement());
    }
}
