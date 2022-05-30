package gamer;

import game.MainGameField;
import minimax.MiniMax;
import minimax.Move;

public class AI extends Gamer {
    private final MainGameField gameField;

    public AI(String sign){
        this.sign = sign;
        gameField = MainGameField.getInstance();
    }

    public boolean shot(int x, int y){
        Move move = MiniMax.findBestMove(gameField.board);
        gameField.board[move.getRow()][move.getCol()] = sign;
        return true;
    }

    public boolean win(){
        return gameField.checkWin(this.sign);
    }

    @Override
    public String toString() {
        return "AI{" +
                "sign='" + sign + '\'' +
                '}';
    }
}
