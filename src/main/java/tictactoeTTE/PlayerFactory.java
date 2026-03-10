package tictactoeTTE;

public class PlayerFactory {

    public Player createPlayer(String playerName) {
        if (playerName.equalsIgnoreCase("AI")){
            return new Player("AI", new AIMovement());
        }
        else {
            return new Player(playerName, new HumanMovement());
        }
    }
}
