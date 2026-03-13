package tictactoeTTE;

import tictactoeTTE.Players.Player;

public interface IGameObserver {

    void moveMade(int row, int column, Player player);
    void symbolRemoved(int row, int column);
    void playerWin(Player winner);
}
