package tictactoeTTE.ui;

import tictactoeTTE.*;
import tictactoeTTE.Players.Player;
import tictactoeTTE.Players.PlayerFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SwingTicTacToe implements IGameObserver{
    public static final Color BACKGROUND_COLOR = new Color(10, 10, 10);
    public static final Color COLOR_BORDER_LIGHT_GRAY = new Color(200, 200, 200);
    public static final int CELL_DIMENSION = 90;

    private final GameBoardCell[][] cells;

    private final Tictactoe tictactoe;
    private boolean turnBeingTaken = false;
    private final boolean showHints;
    private final Player player1;
    private final Player player2;

    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JPanel gridPanel = new JPanel();


    public SwingTicTacToe(Tictactoe game, int size, boolean showHints, Player player1, Player player2) {
        this.tictactoe = game;
        this.cells = new GameBoardCell[size][size];
        this.showHints = showHints;
        this.player1 = player1;
        this.player2 = player2;

        this.tictactoe.registerObserver(this);

        buildUi(size, size);
        redrawFromModel();
    }

    @Override
    public void moveMade(int row, int column, String symbol){
        cells[row][column].setSymbol(symbol);

        for(GameBoardCell[] rowCells : cells){
            for(GameBoardCell cell : rowCells){
                cell.setFading(false);
            }
        }

        if (this.showHints){
            int[] nextX = tictactoe.getGameBoard().getOldestMove(player1);
            int[] nextO = tictactoe.getGameBoard().getOldestMove(player2);

            if (nextX != null) cells[nextX[0]][nextX[1]].setFading(true);
            if (nextO != null) cells[nextO[0]][nextO[1]].setFading(true);
        }

        if (symbol.equals("O") || tictactoe.isOver()) {
            turnBeingTaken = false;
        }
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

    /*
    buildUI was written with the help of Generative AI and the request to help create a UI that
    accepts mouse clicks, changes colors, and adds a symbol to the cells via MVC pattern
     */
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
                        if (turnBeingTaken || tictactoe.isOver()) {
                            return;
                        }

                        if (tictactoe.getGameBoard().isValidSpot(rowSelected, columnSelected)) {
                            turnBeingTaken = true;
                            tictactoe.handleMove(rowSelected, columnSelected);
                        }
                        else {
                            System.out.println("Invalid spot selected");
                        }
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
        String DEFAULT_PLAYER_NAME = "Player 1";
        String DEFAULT_DIFFICULTY = "Simple";

        JTextField nameField = new JTextField("Joe");
        JComboBox<String> sizeBox = new JComboBox<>(new String[]{"3x3", "4x4", "5x5"});
        JComboBox<String> difficultyBox = new JComboBox<>(new String[]{"Simple", "Smart"});
        JCheckBox showHints = new JCheckBox("Show removal hints", true);

        JPanel startPanel = createStartupPanel(nameField, sizeBox, difficultyBox, showHints);

        int result = JOptionPane.showConfirmDialog(null, startPanel,
                "Game Setup", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String playerName = nameField.getText().trim().isEmpty() ? DEFAULT_PLAYER_NAME : nameField.getText().trim();
            int boardSize = Integer.parseInt(((String)sizeBox.getSelectedItem()).substring(0, 1));
            String difficultyChoice = difficultyBox.getSelectedItem().toString().trim().isEmpty() ? DEFAULT_DIFFICULTY :  difficultyBox.getSelectedItem().toString();

            PlayerFactory playerFactory = new PlayerFactory();
            GameBoard board = new GameBoard(boardSize);
            Player playerOne = playerFactory.createHumanPlayer(playerName, DEFAULT_PLAYER_ONE_SYMBOL);
            Player playerTwo = playerFactory.createComputerPlayer(DEFAULT_PLAYER_TWO_SYMBOL, difficultyChoice);
            boolean hintsEnabled = showHints.isSelected();

            Tictactoe game = new Tictactoe(board, playerOne, playerTwo);
            new SwingTicTacToe(game, boardSize,hintsEnabled, playerOne, playerTwo).show();
        }

    }

    /*
    Startup panel created using the help of Generative AI with the request to make a
    swing panel that displays a text box containing a short description of the game,
    an ability to select game board size from predetermined sizes, the ability to
    select the difficulty of the computer player, and the ability to show hints or not.
     */
    private static JPanel createStartupPanel(JTextField nameField, JComboBox<String> sizeBox, JComboBox<String> difficultyBox, JCheckBox showHints) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Game Description
        JLabel description = new JLabel("<html><div style='text-align: center;'>" +
                "<h2>Tic-Tac-Toe: Till-The-End</h2>" +
                "Each player only keeps their last 3 moves on the board.<br>" +
                "When you place your 4th piece, the 1st one disappears!" +
                "</div></html>");
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(description, gbc);

        // Name Input
        gbc.gridwidth = 1; gbc.gridy = 1;
        panel.add(new JLabel("Player Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Board Size
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Board Size:"), gbc);
        gbc.gridx = 1;
        panel.add(sizeBox, gbc);

        // Difficulty
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Computer Difficulty:"), gbc);
        gbc.gridx = 1;
        panel.add(difficultyBox, gbc);

        // Hints
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(showHints, gbc);

        return panel;
    }

}
