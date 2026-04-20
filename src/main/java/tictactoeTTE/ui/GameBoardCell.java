package tictactoeTTE.ui;

import javax.swing.*;
import java.awt.*;

public class GameBoardCell extends JPanel {
    private String symbol = "";

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if(symbol.isEmpty()){ return;}

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int fontSize = (int) (Math.min(getWidth(), getHeight()) * .80);
        g2d.setFont(new Font("Arial", Font.BOLD, fontSize));

        FontMetrics fm = g2d.getFontMetrics();

        int width = (getWidth() - fm.stringWidth(symbol)) / 2;
        int height = (((getHeight() - fm.stringWidth(symbol)) / 2) + fm.getAscent() ) - 18;
        g2d.drawString(symbol, width, height);
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
        repaint();
    }
}
