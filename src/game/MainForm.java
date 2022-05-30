package game;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private final MainGameField mainGameField = MainGameField.getInstance();

    public MainForm() {
        setTitle("Tic Tac Toe");
        setBounds(300, 300, 467, 525);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainGameField gameField = MainGameField.getInstance();
        JPanel buttonPanel = new JPanel(new GridLayout());
        add(gameField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        JButton btnStart = new JButton("Start New Game");
        JButton btnEnd = new JButton("End Game");
        buttonPanel.add(btnStart);
        buttonPanel.add(btnEnd);
        btnEnd.addActionListener(e -> System.exit(0));
        btnStart.addActionListener(e -> mainGameField.startNewGame());
        setVisible(true);
    }
}
