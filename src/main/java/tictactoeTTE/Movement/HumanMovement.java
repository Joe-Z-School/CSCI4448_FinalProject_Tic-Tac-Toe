package tictactoeTTE.Movement;

import tictactoeTTE.GameBoard;
import tictactoeTTE.Players.Player;
import java.util.Scanner;

public class HumanMovement extends Movement {
    private final Scanner in = new Scanner(System.in);

    public int[] getNextMove(GameBoard board, Player playerMoving) {
        int rowChosen = -1;
        int columnChosen = -1;

        while(true) {
            System.out.print("Enter a row: ");
            rowChosen = in.nextInt();
            System.out.print("Enter a column: ");
            columnChosen = in.nextInt();

            if (board.isValidSpot(rowChosen, columnChosen)) {
                break;
            }
            System.out.println("Invalid spot chosen! Please try again.");
        }

        return new int[]{rowChosen, columnChosen};
    }
}
