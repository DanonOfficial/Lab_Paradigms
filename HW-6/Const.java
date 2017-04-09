package expression;

/**
 * Created by DanonOfficial on 03.04.2017.
 */
public class Const implements TripleExpression {
    private int value;

    public Const(int value) {
        this.value = value;
    }

    public int evaluate(int x, int y, int z) {
        return value;
    }
}
