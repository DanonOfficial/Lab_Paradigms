package expression;

/**
 * Created by DanonOfficial on 03.04.2017.
 */
abstract public class AbstractOperator implements TripleExpression {
    protected TripleExpression first, second;

    AbstractOperator(TripleExpression first, TripleExpression second) {
        this.first = first;
        this.second = second;
    }

    abstract protected int operation(int a, int b);

    public int evaluate(int x, int y, int z) {
        return operation(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
