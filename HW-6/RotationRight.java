package expression;

/**
 * Created by DanonOfficial on 03.04.2017.
 */
public class RotationRight extends AbstractOperator implements TripleExpression {
    public RotationRight(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected int operation(int a, int b) {
        return a >> b;
    }
}
