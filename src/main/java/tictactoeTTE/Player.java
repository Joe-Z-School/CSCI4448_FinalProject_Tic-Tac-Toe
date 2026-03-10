package tictactoeTTE;

public class Player {
    private String playerName;
    private IMove movement;


    public Player(String playerName, IMove movementStyle){
        this.playerName = playerName;
        this.movement = movementStyle;
    }
}
