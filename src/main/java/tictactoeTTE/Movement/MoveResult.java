package tictactoeTTE.Movement;

public class MoveResult {
    private final boolean symbolRemoved;
    private final int removedRow;
    private final int removedColumn;
    private final boolean successfulMove;

    public MoveResult(boolean wasMoveSuccessful, boolean wasSymbolRemoved, int rowRemoved, int columnRemoved) {
        this.symbolRemoved = wasSymbolRemoved;
        this.removedRow = rowRemoved;
        this.removedColumn = columnRemoved;
        this.successfulMove = wasMoveSuccessful;
    }

    public boolean wasSymbolRemoved(){
        return symbolRemoved;
    }
    public int getRemovedRow(){
        return removedRow;
    }
    public int getRemovedColumn(){
        return removedColumn;
    }
    public boolean isSuccessfulMove(){
        return successfulMove;
    }
}
