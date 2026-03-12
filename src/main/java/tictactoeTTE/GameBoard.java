package tictactoeTTE;

import tictactoeTTE.Players.Player;

import java.util.LinkedList;

public class GameBoard {

    private int MAX_ROWS = 3;
    private int MAX_COLUMNS = 3;
    private String[][] boardLayout;
    private LinkedList<int[]> player1History;
    private LinkedList<int[]> player2History;

    public GameBoard() {
        boardLayout = new String[MAX_ROWS][MAX_COLUMNS];
        player1History = new LinkedList<>();
        player2History = new LinkedList<>();
    }

    public Boolean doMovement(int row, int column, Player player){
        if (!isValidSpot(row, column)){ return false;}

        LinkedList<int[]> history = (player.getSymbol().equals("X")) ? player1History : player2History;

        if (history.size() == 3){
            int[] oldestSymbol = history.poll();
            boardLayout[oldestSymbol[0]][oldestSymbol[1]] = null;
        }

        boardLayout[row][column] = player.getSymbol();
        history.add(new int[]{row, column});

        return true;
    }

    private Boolean isValidSpot(int row, int column){
        return boardLayout[row][column] == null;
    }

    public String[][] getBoardLayout() {
        return boardLayout.clone();
    }
}
