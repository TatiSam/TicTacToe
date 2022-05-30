package gamer;

import game.MainGameField;

public class Player extends Gamer{
    private final MainGameField gameField;

    public Player(String sign) {
        this.sign = sign;
        gameField = MainGameField.getInstance();
    }

    public String getSign() {
        return super.sign;
    }

    @Override
    public boolean shot(int x, int y) {
        if (!gameField.isCellBusy(x, y)){
            gameField.board[x][y] = sign;
            return true;
        }
        return false;
    }

    @Override
    public boolean win() {
        return gameField.checkWin(this.sign);
    }

    @Override
    public String toString() {
        return "Player{" +
                "sign='" + sign + '\'' +
                '}';
    }
}
