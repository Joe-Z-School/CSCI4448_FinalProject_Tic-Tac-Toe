package tictactoeTTE;

public class GameBoard {

    private int MAX_ROWS = 3;
    private int MAX_COLUMNS = 3;

    private int[][] boardLayout;

    public GameBoard() {
        boardLayout = new int[MAX_ROWS][MAX_COLUMNS];
    }
}
