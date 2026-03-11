package tictactoeTTE.Players;

import tictactoeTTE.AIMovement;
import tictactoeTTE.HumanMovement;

public class PlayerFactory {

    public Player createPlayer(String playerName, String symbol) {
        if (playerName.equalsIgnoreCase("AI")){
            return new Player("AI", symbol, new AIMovement());
        }
        else {
            return new Player(playerName, symbol, new HumanMovement());
        }
    }
}
