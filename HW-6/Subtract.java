package expression;

/**
 * Created by DanonOfficial on 03.04.2017.
 */
public class Subtract extends AbstractOperator implements TripleExpression {
    public Subtract(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected int operation(int a, int b) {
        return a - b;
    }
}
