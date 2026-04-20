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

    public SwingTicTacToe(Tictactoe game) {
        this.tictactoe = game;
        this.cells = new GameBoardCell[3][3];

        this.tictactoe.registerObserver(this);

        buildUi(3, 3);
        redrawFromModel();
    }

    @Override
    public void moveMade(int row, int column, String symbol){
        cells[row][column].setSymbol(symbol);
        cells[row][column].setForeground(symbol.equals("X") ? Color.CYAN : Color.ORANGE);
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

        // Double-click anywhere on the grid to reset
        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    resetGame();
                }
            }
        });

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
        PlayerFactory playerFactory = new PlayerFactory();
        GameBoard board = new GameBoard();
        Player playerOne = playerFactory.createHumanPlayer("Joe", "X");
        Player playerTwo = playerFactory.createComputerPlayer("O");
        Tictactoe game = new Tictactoe(board, playerOne, playerTwo);
        new SwingTicTacToe(game).show();
    }

}
