package tictactoeTTE;

import tictactoeTTE.Movement.MoveResult;
import tictactoeTTE.Players.Player;

import java.util.LinkedList;

public class GameBoard {

    private int MAX_ROWS;
    private int MAX_COLUMNS;
    private String[][] boardLayout;
    private LinkedList<int[]> player1History;
    private LinkedList<int[]> player2History;
    private int boardSize;

    public GameBoard(int size) {
        boardLayout = new String[size][size];
        this.MAX_ROWS = size;
        this.MAX_COLUMNS = size;
        player1History = new LinkedList<>();
        player2History = new LinkedList<>();
        boardSize = boardLayout.length;
    }

    public MoveResult doMovement(int row, int column, Player player){

        LinkedList<int[]> history = (player.getSymbol().equals("X")) ? player1History : player2History;

        boolean removedSymbol = false;
        int removedRow = -1;
        int removedColumn = -1;

        if (history.size() == boardSize){
            int[] oldestSymbol = history.poll();
            boardLayout[oldestSymbol[0]][oldestSymbol[1]] = null;

            removedSymbol = true;
            removedRow = oldestSymbol[0];
            removedColumn = oldestSymbol[1];
        }

        boardLayout[row][column] = player.getSymbol();
        history.add(new int[]{row, column});

        return new MoveResult(true, removedSymbol, removedRow, removedColumn);
    }

    public int[] getOldestMove(Player player){
        LinkedList<int[]> opponentHistory = (player.getSymbol().equals("X")) ? player1History : player2History;
        if (opponentHistory.size() == boardSize){
            return opponentHistory.peek();
        }
        return null;
    }

    public int[] getOpponentOldestMove(Player player){
        LinkedList<int[]> opponentHistory = (player.getSymbol().equals("X")) ? player2History : player1History;
        if (opponentHistory.size() == boardSize){
            return opponentHistory.peek();
        }
        return null;
    }

    public Boolean isValidSpot(int row, int column){
        if ( row < 0 || row >= MAX_ROWS || column < 0 || column >= MAX_COLUMNS){
            return false;
        }
        return boardLayout[row][column] == null;
    }

    public String[][] getBoardLayout() {
        return boardLayout.clone();
    }

    public Boolean checkIfWinner(Player player){
        // Get player symbol
        String playerSymbol = player.getSymbol();

        // Check if symbol is in cells row by row
        if (checkRows(playerSymbol)) { return true; }

        // Check if symbol is in cells column by column
        if (checkColumns(playerSymbol)) { return true; }

        // Check if symbol is diagonal present from top left to bottom right
        if (checkDiagLtoR(playerSymbol)) { return true; }

        // Check if symbol is diagonal present from top right to bottom left
        if (checkDiagRtoL(playerSymbol)) { return true; }

        return false;
    }

    private Boolean checkRows(String playerSymbol){
        int correctSymbolCount = 0;
        for (int i = 0; i < MAX_ROWS; i++){
            for (int j = 0; j < MAX_COLUMNS; j++){
                if (playerSymbol.equals(boardLayout[i][j])){
                    correctSymbolCount++;
                    if (correctSymbolCount == boardSize){ return true;}
                }
            }
            correctSymbolCount = 0;
        }
        return false;
    }

    private Boolean checkColumns(String playerSymbol){
        int correctSymbolCount = 0;
        for (int i = 0; i < MAX_COLUMNS; i++){
            for (int j = 0; j < MAX_ROWS; j++){
                if (playerSymbol.equals(boardLayout[j][i])){
                    correctSymbolCount++;
                    if (correctSymbolCount == boardSize){ return true;}
                }
            }
            correctSymbolCount = 0;
        }
        return false;
    }

    private Boolean checkDiagLtoR(String playerSymbol){
        int correctSymbolCount = 0;

        for (int i = 0; i < boardSize; i++){
            if (playerSymbol.equals(boardLayout[i][i])){
                correctSymbolCount++;
            }
        }
        return correctSymbolCount == boardSize;
    }

    private Boolean checkDiagRtoL(String playerSymbol){
        int correctSymbolCount = 0;

        for (int i = 0; i < boardSize; i++){
            if (playerSymbol.equals(boardLayout[i][boardSize-1-i])){
                correctSymbolCount++;
            }
        }
        return correctSymbolCount == boardSize;
    }

    public void displayBoard(){
        for (int w = 0; w < MAX_COLUMNS + 1; w++){
            if (w == 0){
                System.out.print("  ");
            }
            else if (w == MAX_COLUMNS-1){
                System.out.print(w - 1 + "   ");
            }
            else {
                System.out.print(w - 1 + "   ");
            }
        }

        System.out.println();

        for (int i = 0; i < MAX_ROWS; i++){
            System.out.print(i + " ");
            for (int j = 0; j < MAX_COLUMNS; j++){
                if ( j == MAX_COLUMNS-1){
                    if(boardLayout[i][j] == null){
                        System.out.print("*");
                    }
                    else {
                        System.out.print(boardLayout[i][j]);
                    }
                }
                else{
                    if(boardLayout[i][j] == null){
                        System.out.print("*" + " | ");
                    }
                    else {
                        System.out.print(boardLayout[i][j] + " | ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
