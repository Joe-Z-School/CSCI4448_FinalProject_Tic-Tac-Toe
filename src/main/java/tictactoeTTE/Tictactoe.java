package tictactoeTTE;

import tictactoeTTE.Movement.MoveResult;
import tictactoeTTE.Players.Player;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

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

    public void handleMove(int row, int col) {
        if (gameIsOver) return;

        MoveResult resultOfMove = board.doMovement(row, col, currentPlayer);
        if (resultOfMove.isSuccessfulMove()) {
            if (resultOfMove.wasSymbolRemoved()) {
                sendRemovalSignal(resultOfMove.getRemovedRow(), resultOfMove.getRemovedColumn());
            }

            sendMoveSignal(row, col, currentPlayer);

            if (board.checkIfWinner(currentPlayer)) {
                gameIsOver = true;
                sendGameOverSignal(currentPlayer);
            } else {
                switchTurn();

                if (currentPlayer.isComputer()) {
                    int computerMoveDisplayDelay = 1500;

                    Timer computerDelayTimer = new Timer(computerMoveDisplayDelay, action -> {
                        int[] computerMove = currentPlayer.getMove(board);
                        handleMove(computerMove[0], computerMove[1]);
                    });

                    computerDelayTimer.setRepeats(false);
                    computerDelayTimer.start();
                }
            }
        }
    }

    public GameBoard getGameBoard() {return this.board;}

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
            observer.moveMade(row, column, player.getSymbol());
        }
    }

    @Override
    public void sendRemovalSignal(int row, int column){
        for (IGameObserver observer : observers){
            observer.symbolRemoved(row, column);
        }
    }

    @Override
    public void sendGameOverSignal(Player winningPlayer){
        for (IGameObserver observer : observers){
            observer.gameOver(winningPlayer);
        }
    }
}