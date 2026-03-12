package tictactoeTTE;

import tictactoeTTE.Players.Player;

public class HumanMovement extends Movement {

    public int[] getNextMove(GameBoard board, Player playerMoving) {
        int rowChosen = -1;
        int columnChosen = -1;

        while(true) {
            rowChosen = Integer.parseInt(System.console().readLine("Enter a row: "));
            columnChosen = Integer.parseInt(System.console().readLine("Enter a column: "));
            if (board.isValidSpot(rowChosen, columnChosen)) {
                break;
            }
            System.out.println("Invalid spot chosen! Please try again.");
        }

        return new int[]{rowChosen, columnChosen};
    }
}
