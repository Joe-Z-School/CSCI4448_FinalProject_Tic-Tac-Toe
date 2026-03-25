package tictactoeTTE.Movement;

import tictactoeTTE.GameBoard;
import tictactoeTTE.Players.Player;

public class AIMovement extends Movement {


    public int[] getNextMove(GameBoard board, Player playerMoving) {
        String mySymbol = playerMoving.getSymbol();
        String opponentSymbol = mySymbol.equals("X") ? "O" : "X";

        int[] winningChoice = findMove(board, mySymbol);
        if (winningChoice != null) {
            return winningChoice;
        }

        int[] blockingChoice = findMove(board, opponentSymbol);
        if (blockingChoice != null) {
            return blockingChoice;
        }

        return getAvailableMoves(board).getFirst();
    }

    private int[] findMove(GameBoard board, String symbol) {
        String[][] layoutOfBoard = board.getBoardLayout();
        int boardSize = layoutOfBoard.length;
        int targetCounter = boardSize - 1;

        int[] checkedRows = checkRows(boardSize, layoutOfBoard, symbol, targetCounter);
        if (checkedRows != null) return checkedRows;

        int[] checkedColumns = checkColumns(boardSize, layoutOfBoard, symbol, targetCounter);
        if (checkedColumns != null) return checkedColumns;

        int[] checkedDiagLtoR = checkDiagLtoR(boardSize, layoutOfBoard, symbol, targetCounter);
        if (checkedDiagLtoR != null) return checkedDiagLtoR;

        int[] checkedDiagRtoL = checkDiagRtoL(boardSize, layoutOfBoard, symbol, targetCounter);
        if (checkedDiagRtoL != null) return checkedDiagRtoL;

        return null;
    }

    private int[] checkRows(int boardSize, String[][] layout, String symbol, int targetCounter) {
        for (int i = 0; i < boardSize; i++) {
            int symbolCount = 0;
            int emptyCol = -1;
            for (int j = 0; j < boardSize; j++) {
                if (symbol.equals(layout[i][j])) symbolCount++;
                else if (layout[i][j] == null) emptyCol = j;
            }
            if (symbolCount == targetCounter && emptyCol != -1) return new int[]{i, emptyCol};
        }
        return null;
    }

    private int[] checkColumns(int boardSize, String[][] layout, String symbol, int targetCounter) {
        for (int j = 0; j < boardSize; j++) {
            int symbolCount = 0;
            int emptyRow = -1;
            for (int i = 0; i < boardSize; i++) {
                if (symbol.equals(layout[i][j])) symbolCount++;
                else if (layout[i][j] == null) emptyRow = i;
            }
            if (symbolCount == targetCounter && emptyRow != -1) return new int[]{emptyRow, j};
        }
        return null;
    }

    private int[] checkDiagLtoR(int boardSize, String[][] layout, String symbol, int targetCounter) {  // 3. Check Main Diagonal (Top-Left to Bottom-Right)
        int diag1Count = 0;
        int diag1EmptyIndex = -1;
        for (int i = 0; i < boardSize; i++) {
            if (symbol.equals(layout[i][i])) diag1Count++;
            else if (layout[i][i] == null) diag1EmptyIndex = i;
        }
        if (diag1Count == targetCounter && diag1EmptyIndex != -1) return new int[]{diag1EmptyIndex, diag1EmptyIndex};

        return null;
    }

    private int[] checkDiagRtoL(int boardSize, String[][] layout, String symbol, int targetCounter) {    // 4. Check Anti-Diagonal (Top-Right to Bottom-Left)
        int diag2Count = 0;
        int diag2EmptyIndex = -1;
        for (int i = 0; i < boardSize; i++) {
            int col = boardSize - 1 - i;
            if (symbol.equals(layout[i][col])) diag2Count++;
            else if (layout[i][col] == null) diag2EmptyIndex = i;
        }
        if (diag2Count == targetCounter && diag2EmptyIndex != -1)
            return new int[]{diag2EmptyIndex, boardSize - 1 - diag2EmptyIndex};

        return null;
    }
}
