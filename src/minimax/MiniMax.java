package minimax;

import static game.MainGameField.*;

public class MiniMax {

    static boolean isMovesLeft(String[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].equals(NOT_SIGN))
                    return true;
        return false;
    }

    public static int evaluate(String[][] board) {
        for(int row = 0; row < 3; row++) {
            if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])) {
                if (board[row][0].equals(O_SIGN))
                    return +10;
                else if (board[row][0].equals(X_SIGN))
                    return -10;
            }
        }
        for(int col = 0; col < 3; col++) {
            if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])) {
                if (board[0][col].equals(O_SIGN))
                    return +10;
                else if (board[0][col].equals(X_SIGN))
                    return -10;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            if (board[0][0].equals(O_SIGN))
                return +10;
            else if (board[0][0].equals(X_SIGN))
                return -10;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            if (board[0][2].equals(O_SIGN))
                return +10;
            else if (board[0][2].equals(X_SIGN))
                return -10;
        }
        return 0;
    }

    public static int minimax(String[][] board, int depth, boolean isMax) {
        int score = evaluate(board);
        if(score == 10 || score == -10)
            return score;
        if(!isMovesLeft(board))
            return 0;
        int best;
        if(isMax) {
            best = -1000;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (board[i][j].equals(NOT_SIGN)) {
                        board[i][j] = O_SIGN;
                        best = Math.max(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = NOT_SIGN;
                    }
                }
            }
        }
        else {
            best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals(NOT_SIGN)) {
                        board[i][j] = X_SIGN;
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = NOT_SIGN;
                    }
                }
            }
        }
        return best;
    }

    public static Move findBestMove(String[][] board) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.setRow(-1);
        bestMove.setCol(-1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(NOT_SIGN)) {
                    board[i][j] = O_SIGN;
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = NOT_SIGN;
                    if (moveVal > bestVal) {
                        bestMove.setRow(i);
                        bestMove.setCol(j);
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}
