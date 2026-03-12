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

        LinkedList<int[]> history = (player.getSymbol().equals("X")) ? player1History : player2History;

        if (history.size() == 3){
            int[] oldestSymbol = history.poll();
            boardLayout[oldestSymbol[0]][oldestSymbol[1]] = null;
        }

        boardLayout[row][column] = player.getSymbol();
        history.add(new int[]{row, column});

        return true;
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
    /*
      0   1   2
    0 * | * | *
    1 * | * | *
    2 * | * | *

     */

    private Boolean checkRows(String playerSymbol){
        int correctSymbolCount = 0;
        for (int i = 0; i < MAX_ROWS; i++){
            for (int j = 0; j < MAX_COLUMNS; j++){
                if (boardLayout[i][j].equals(playerSymbol)){
                    correctSymbolCount++;
                    if (correctSymbolCount == 3){ return true;}
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
                if (boardLayout[j][i].equals(playerSymbol)){
                    correctSymbolCount++;
                    if (correctSymbolCount == 3){ return true;}
                }
            }
            correctSymbolCount = 0;
        }
        return false;
    }

    private Boolean checkDiagLtoR(String playerSymbol){
        int correctSymbolCount = 0;
        int boardSize = boardLayout.length;

        for (int i = 0; i < boardSize; i++){
            if (boardLayout[i][i].equals(playerSymbol)){
                correctSymbolCount++;
            }
        }
        return correctSymbolCount == 3;
    }

    private Boolean checkDiagRtoL(String playerSymbol){
        int correctSymbolCount = 0;
        int boardSize = boardLayout.length;

        for (int i = 0; i < boardSize; i++){
            if (boardLayout[i][boardSize-1-i].equals(playerSymbol)){
                correctSymbolCount++;
            }
        }
        return correctSymbolCount == 3;
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
    }
}
