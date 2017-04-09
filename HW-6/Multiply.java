package expression;

/**
 * Created by DanonOfficial on 03.04.2017.
 */
public class Multiply extends AbstractOperator implements TripleExpression {
    public Multiply(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected int operation(int a, int b) {
        return a * b;
    }
}
