package tictactoeTTE.ui;

import tictactoeTTE.*;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class SwingTicTacToe implements IGameObserver{
    public static final Color BACKGROUND_COLOR = new Color(10, 10, 10);
    public static final Color COLOR_BORDER_LIGHT_GRAY = new Color(200, 200, 200);
    public static final int CELL_DIMENSION = 90;

    private final GameBoardCell[][] cells;

    private final Tictactoe tictactoe;

    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JPanel gridPanel = new JPanel();

    //private final JLabel[][] cells;

    public SwingTicTacToe(Tictactoe game, int size) {
        this.tictactoe = game;
        this.cells = new GameBoardCell[size][size];

        this.tictactoe.registerObserver(this);

        buildUi(size, size);
        redrawFromModel();
    }

    @Override
    public void moveMade(int row, int column, String symbol){
        cells[row][column].setSymbol(symbol);
        cells[row][column].setForeground(symbol.equals("X") ? Color.CYAN : Color.MAGENTA);
    }

    @Override
    public void symbolRemoved(int row, int column){
        cells[row][column].setSymbol("");
    }

    @Override
    public void gameOver(Player winner){
        JOptionPane.showMessageDialog(frame, "Winner: " + winner.getPlayerName());
    }

    private void buildUi(int rows, int cols) {
        gridPanel.setLayout(new GridLayout(rows, cols, 4, 4));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        gridPanel.setBackground(Color.BLACK);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                final int rowSelected = row;
                final int columnSelected = col;

                GameBoardCell cell = new GameBoardCell();
                cell.setOpaque(true);
                cell.setPreferredSize(new Dimension(CELL_DIMENSION, CELL_DIMENSION));
                cell.setBorder(new LineBorder(COLOR_BORDER_LIGHT_GRAY, 2));
                cell.setBackground(BACKGROUND_COLOR);

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tictactoe.handleMove(rowSelected, columnSelected);
                    }
                });

                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }

        JPanel bottom = new JPanel(new BorderLayout(8, 8));
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void resetGame() {

        redrawFromModel();
    }

    private void redrawFromModel() {
        paintEmptyGrid();
    }

    private void paintEmptyGrid() {
        for (GameBoardCell[] GameBoardCell : cells) {
            for (int c = 0; c < cells[0].length; c++) {
                GameBoardCell cell = GameBoardCell[c];
                cell.setSymbol("");
                cell.setBackground(BACKGROUND_COLOR);
                cell.setForeground(Color.BLACK);
                cell.setBorder(new LineBorder(COLOR_BORDER_LIGHT_GRAY, 2));
            }
        }
    }

    public void show() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public static void main(String[] args)  {
        String DEFAULT_PLAYER_ONE_SYMBOL = "X";
        String DEFAULT_PLAYER_TWO_SYMBOL = "O";

        String playerName = JOptionPane.showInputDialog(null, "Enter player name: ",
                "Player Setup", JOptionPane.QUESTION_MESSAGE);

        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player 1";
        }

        String[] boardSizes = {"3x3" , "4x4", "5x5"};
        String sizeChoice = (String) JOptionPane.showInputDialog(null, "Select Board Size:",
                "Setup", JOptionPane.QUESTION_MESSAGE, null, boardSizes, boardSizes[0]);
        int size = Integer.parseInt(sizeChoice.substring(0, 1));

        String[] difficulties = {"Simple", "Smart"};
        String difficultyChoice = (String) JOptionPane.showInputDialog(null, "Select Computer Difficulty:",
                "Setup", JOptionPane.QUESTION_MESSAGE, null, difficulties, difficulties[0]);

        PlayerFactory playerFactory = new PlayerFactory();
        GameBoard board = new GameBoard(size);
        Player playerOne = playerFactory.createHumanPlayer(playerName, DEFAULT_PLAYER_ONE_SYMBOL);
        Player playerTwo = playerFactory.createComputerPlayer(DEFAULT_PLAYER_TWO_SYMBOL, difficultyChoice);

        Tictactoe game = new Tictactoe(board, playerOne, playerTwo);
        new SwingTicTacToe(game, size).show();
    }

}
