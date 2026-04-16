package tictactoeTTE;

import tictactoeTTE.Players.Player;

public interface IGameObserver {

    void moveMade(int row, int column, String symbol);
    void symbolRemoved(int row, int column);
    void gameOver(Player winner);
}
