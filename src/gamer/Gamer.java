package gamer;

public abstract class Gamer {
    protected String sign;
    abstract boolean shot(int x, int y);
    abstract boolean win();
}
