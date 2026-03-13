package tictactoeTTE;

import tictactoeTTE.Players.Player;

public interface IGame {
    void registerObserver(IGameObserver observer);
    void removeObserver(IGameObserver observer);
    void sendMoveSignal(int row, int column, Player player);
    void sendRemovalSignal(int row, int column);

}
