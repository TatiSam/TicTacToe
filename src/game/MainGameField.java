package game;

import gamer.AI;
import gamer.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGameField extends JPanel {
    public static final String NOT_SIGN = "*";
    public static final String X_SIGN = "X";
    public static final String O_SIGN = "O";
    private static final int FIELD_SIZE = 450;
    private static int linesCount = 3;
    private boolean gameOver = false;
    private String gameOverMessage = "";
    private final int cellSize;
    private int x;
    private int y;
    private int filledCellsCount;
    public static MainGameField instance;
    public String[][] board;

    private MainGameField() {
        setVisible(false);
        cellSize = FIELD_SIZE / linesCount;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                x = e.getX() / cellSize;
                y = e.getY() / cellSize;
                if (!gameOver) {
                    modeAI();
                }
            }
        });
    }

    public static synchronized MainGameField getInstance() {
        if (instance == null)
            instance = new MainGameField();
        return instance;
    }

    public void startNewGame() {
        gameOver = false;
        gameOverMessage = "";
        filledCellsCount = 0;
        board = new String[linesCount][linesCount];
        repaint();
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                board[i][j] = NOT_SIGN;
            }
        }
        setVisible(true);
    }

    private void modeAI() {
        Player player = new Player(X_SIGN);
        AI ai = new AI(O_SIGN);
        if (!gameOver) {
            if (player.shot(x, y)) {
                filledCellsCount++;
                if (player.win()) {
                    gameOver = true;
                    gameOverMessage = "Player Win!";
                }
                if (isFieldFull()) {
                    gameOver = true;
                    gameOverMessage = "Draw!";
                }
                if (!gameOver) {
                    if(ai.shot(x, y)){
                        filledCellsCount++;
                    }
                    if (ai.win()) {
                        gameOver = true;
                        gameOverMessage = "AI Win!";
                    }
                }
                repaint();
            }
        }
    }

    public boolean isCellBusy(int x, int y) {
        return !board[x][y].equals(NOT_SIGN);
    }

    private boolean isFieldFull() {
        return filledCellsCount == linesCount*linesCount;
    }

    private boolean checkLine(int start_x, int start_y, int dx, int dy, String sign) {
        for (int i = 0; i < linesCount; i++) {
            if (!board[start_x + i * dx][start_y + i * dy].equals(sign))
                return false;
        }
        return true;
    }

    public boolean checkWin(String sign) {
        for (int i = 0; i < linesCount; i++) {
            if (checkLine(i, 0, 0, 1, sign)) return true;
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        if (checkLine(0, 0, 1, 1, sign)) return true;
        return checkLine(0, linesCount - 1, 1, -1, sign);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= linesCount; i++) {
            g.drawLine(0, i * this.cellSize, FIELD_SIZE, i * this.cellSize);
            g.drawLine(i * this.cellSize, 0, i * this.cellSize, FIELD_SIZE);
        }
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (isCellBusy(i, j)) {
                    if (board[i][j].equals(X_SIGN)) {
                        g.setColor(Color.RED);
                        g.drawLine((i * cellSize), (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize);
                        g.drawLine((i + 1) * cellSize, (j * cellSize), (i * cellSize), (j + 1) * cellSize);
                    }
                    if (board[i][j].equals(O_SIGN)) {
                        g.setColor(Color.BLUE);
                        g.drawOval((i * cellSize), (j * cellSize), cellSize, cellSize);
                    }
                }
            }
        }
        if (gameOver) {
            g.setColor(Color.BLACK);
            g.fillRect(0, FIELD_SIZE / 2, FIELD_SIZE, FIELD_SIZE / 8);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Helvetica", 10, 40));
            g.drawString(gameOverMessage, 0, 19 * FIELD_SIZE / 32);
        }
    }
}
