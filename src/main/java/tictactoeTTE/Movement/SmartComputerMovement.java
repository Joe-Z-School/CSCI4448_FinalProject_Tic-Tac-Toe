package tictactoeTTE.Movement;

import tictactoeTTE.GameBoard;
import tictactoeTTE.Players.Player;

import java.util.Random;

public class SmartComputerMovement extends Movement {

    private final Random rand = new Random();


    // Goal is to check for winning spot first, then check to block, lastly default to random available spot
    public int[] getNextMove(GameBoard board, Player playerMoving) {
        String mySymbol = playerMoving.getSymbol();
        String opponentSymbol = mySymbol.equals("X") ? "O" : "X";

        int[] myOldest = board.getOldestMove(playerMoving);
        int[] winningChoice = findMove(board, mySymbol, myOldest);
        if (winningChoice != null) {
            return winningChoice;
        }

        int[] opponentOldest = board.getOpponentOldestMove(playerMoving);
        int[] blockingChoice = findMove(board, opponentSymbol, opponentOldest);
        if (blockingChoice != null) {
            return blockingChoice;
        }

        return getAvailableMoves(board).get(rand.nextInt(getAvailableMoves(board).size()));
    }

    private int[] findMove(GameBoard board, String symbol, int[] aboutToBeRemoved) {
        String[][] layoutOfBoard = board.getBoardLayout();
        int boardSize = layoutOfBoard.length;
        int targetCounter = boardSize - 1;

        int[] checkedRows = checkRows(boardSize, layoutOfBoard, symbol, targetCounter);
        if (checkedRows != null) {
            if (aboutToBeRemoved == null || aboutToBeRemoved[0] != checkedRows[0]) {
                return checkedRows;
            }
        }

        int[] checkedColumns = checkColumns(boardSize, layoutOfBoard, symbol, targetCounter);
        if (checkedColumns != null) {
            if (aboutToBeRemoved == null || aboutToBeRemoved[1] != checkedColumns[1]) {
                return checkedColumns;
            }
        }

        int[] checkedDiagLtoR = checkDiagLtoR(boardSize, layoutOfBoard, symbol, targetCounter);
        if (checkedDiagLtoR != null) {
            if (aboutToBeRemoved == null || aboutToBeRemoved[0] != aboutToBeRemoved[1]) {
                return checkedDiagLtoR;
            }
        }

        int[] checkedDiagRtoL = checkDiagRtoL(boardSize, layoutOfBoard, symbol, targetCounter);
        if (checkedDiagRtoL != null) {
            if (aboutToBeRemoved == null || aboutToBeRemoved[0] + aboutToBeRemoved[1] != boardSize - 1) {
                return checkedDiagRtoL;
            }
        }

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
        int diagLtoRCount = 0;
        int diagLtoREmptyIndex = -1;
        for (int i = 0; i < boardSize; i++) {
            if (symbol.equals(layout[i][i])) diagLtoRCount++;
            else if (layout[i][i] == null) diagLtoREmptyIndex = i;
        }
        if (diagLtoRCount == targetCounter && diagLtoREmptyIndex != -1) return new int[]{diagLtoREmptyIndex, diagLtoREmptyIndex};

        return null;
    }

    private int[] checkDiagRtoL(int boardSize, String[][] layout, String symbol, int targetCounter) {    // 4. Check Anti-Diagonal (Top-Right to Bottom-Left)
        int diagRtoLCount = 0;
        int diagRtoLEmptyIndex = -1;
        for (int i = 0; i < boardSize; i++) {
            int col = boardSize - 1 - i;
            if (symbol.equals(layout[i][col])) diagRtoLCount++;
            else if (layout[i][col] == null) diagRtoLEmptyIndex = i;
        }
        if (diagRtoLCount == targetCounter && diagRtoLEmptyIndex != -1)
            return new int[]{diagRtoLEmptyIndex, boardSize - 1 - diagRtoLEmptyIndex};

        return null;
    }
}
