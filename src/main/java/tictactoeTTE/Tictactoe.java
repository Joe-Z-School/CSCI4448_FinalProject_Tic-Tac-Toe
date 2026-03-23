package tictactoeTTE;

import tictactoeTTE.Players.Player;

import java.util.ArrayList;
import java.util.List;

public class Tictactoe implements IGame{

    private Boolean gameIsOver = false;
    private GameBoard board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private List<IGameObserver> observers =  new ArrayList<>();

    public Tictactoe(GameBoard board, Player playerOne, Player playerTwo) {
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne; // Default to player one starting
    }

    public void play() {
        while (!gameIsOver) {
            board.displayBoard();
            currentPlayer.takeTurn(board);
            if (board.checkIfWinner(currentPlayer)) {
                board.displayBoard();
                gameIsOver = true;
                System.out.println("GAME OVER");
                System.out.println("Winner: " + currentPlayer.getPlayerName());
            } else {
                switchTurn();
            }
        }
    }

    public Boolean isOver(){ return gameIsOver; }

    private void switchTurn() {
        currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;
    }

    @Override
    public void registerObserver(IGameObserver observer) {observers.add(observer);}

    @Override
    public void removeObserver(IGameObserver observer) {observers.remove(observer);}

    @Override
    public void sendMoveSignal(int row, int column, Player player){
        for (IGameObserver observer : observers){
            // send message of move
        }
    };

    @Override
    public void sendRemovalSignal(int row, int column){
        for (IGameObserver observer : observers){
            // send message of symbol removed
        }
    };



}