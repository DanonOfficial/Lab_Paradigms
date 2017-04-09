package expression.parser;

/**
 * Created by DanonOfficial on 03.04.2017.
 */
public class Test {
    public static void main(String[] args) {
        final ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("1 << 5 + 3").evaluate(0, 0, 0));
        //System.out.println(parser.parse("1000").evaluate(2, 0, 0));
    }
}
