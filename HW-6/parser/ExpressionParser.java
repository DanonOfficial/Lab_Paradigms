package expression.parser;

import expression.*;

/**
 * Created by DanonOfficial on 03.04.2017.
 */
public class ExpressionParser implements Util.Parser {
    private String expression;
    private int index;
    private state status;
    private int number;

    ;
    private char variable;

    private void whitespacePass() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    private char getChar() {
        if (index < expression.length()) {
            index++;
            return expression.charAt(index - 1);
        }
        return '!';
    }

    private void nextOp() {
        whitespacePass();
        char temp = getChar();
        if (Character.isDigit(temp)) {
            status = state.num;
            StringBuilder num = new StringBuilder();
            while (Character.isDigit(temp)) {
                num.append(temp);
                temp = getChar();
            }
            index--;
            number = Integer.parseUnsignedInt(num.toString());
        } else if (temp == '-') {
            status = state.minus;
        } else if (temp == '+') {
            status = state.plus;
        } else if (temp == '*') {
            status = state.aster;
        } else if (temp == '/') {
            status = state.slash;
        } else if (temp == '(') {
            status = state.leftParenthesis;
        } else if (temp == ')') {
            status = state.rightParenthesis;
        } else if (temp == 'x' || temp == 'y' || temp == 'z') {
            status = state.var;
            variable = temp;
        } else if (index < expression.length() && expression.substring(index - 1, index + 1).equals("<<")) {
            status = state.rotationLeft;
            index++;
        } else if (index < expression.length() && expression.substring(index - 1, index + 1).equals(">>")) {
            status = state.rotationRight;
            index++;
        }
        whitespacePass();
    }


    private TripleExpression separation() {
        nextOp();
        TripleExpression res;
        if (status == state.num) {
            res = new Const((int) number);
            nextOp();
        } else if (status == state.var) {
            res = new Variable(Character.toString(variable));
            nextOp();
        } else if (status == state.minus) {
            res = new Subtract(new Const(0), separation());
        } else if (status == state.leftParenthesis) {
            res = rotations();
            nextOp();
        } else {
            res = null;
            //System.exit(0);
        }
        return res;
    }

    private TripleExpression mulAndDiv() {
        TripleExpression firstOp = separation();
        while (true) {
            if (status == state.aster) {
                firstOp = new Multiply(firstOp, separation());
            } else if (status == state.slash) {
                firstOp = new Divide(firstOp, separation());
            } else {
                return firstOp;
            }
        }
    }

    private TripleExpression addAndSub() {
        TripleExpression firstOp = mulAndDiv();
        while (true) {
            if (status == state.plus) {
                firstOp = new Add(firstOp, mulAndDiv());
            } else if (status == state.minus) {
                firstOp = new Subtract(firstOp, mulAndDiv());
            } else {
                return firstOp;
            }
        }
    }

    private TripleExpression rotations() {
        TripleExpression firstOp = addAndSub();
        while (true) {
            if (status == state.rotationRight) {
                firstOp = new RotationRight(firstOp, addAndSub());
            } else if (status == state.rotationLeft) {
                firstOp = new RotationLeft(firstOp, addAndSub());
            } else {
                return firstOp;
            }
        }
    }

    public TripleExpression parse(String expression) {
        this.expression = expression;
        return rotations();
    }

    private enum state {num, var, plus, minus, slash, aster, rotationLeft, rotationRight, leftParenthesis, rightParenthesis}

}
