package tictactoeTTE.Players;

import tictactoeTTE.Movement.Movement;
import tictactoeTTE.Movement.SimpleComputerMovement;
import tictactoeTTE.Movement.SmartComputerMovement;
import tictactoeTTE.Movement.HumanMovement;

public class PlayerFactory {
    private final String COMPUTER_DEFAULT_NAME = "Computer";

    public Player createHumanPlayer(String playerName, String symbol) {
            return new Human(playerName, symbol, new HumanMovement());
    }

    public Player createComputerPlayer(String symbol, String difficulty){
        Movement strategy = difficulty.equals("Smart") ? new SmartComputerMovement() : new SimpleComputerMovement();
        return new Computer(COMPUTER_DEFAULT_NAME, symbol, strategy);
    }
}
