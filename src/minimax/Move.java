package minimax;

public class Move {
    private int row;
    private int col;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
