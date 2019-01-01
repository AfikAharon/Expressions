/**
 * a Div Class.
 *
 * The class calculate with the operator Div, inherent
 * from the BinaryExpression Class and implements to
 * Expression class.
 *
 * @author Afik Aharon.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     *
     * Constructor for the Div class :
     * take number and string and make them to
     * Expressions.
     *
     * @param var a given string of Variable.
     * @param num a given number.
     */
    public Div(String var, double num) {
        super.setLeftExpression(new Var(var));
        super.setRightExpression(new Num(num));
    }

    /**
     *
     * Constructor for the Div class :
     * take number and string and make them to
     * Expressions.
     *
     * @param num a given number.
     * @param var a given string of Variable.
     */
    public Div(double num, String var) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(new Var(var));
    }

    /**
     *
     * Constructor for the Div class :
     * take two Expression and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param rightE the right Expression.
     */
    public Div(Expression leftE, Expression rightE) {
        super.setLeftExpression(leftE);
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Div class :
     * take Expression and String and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param var a given string of Variable.
     */
    public Div(Expression leftE, String var) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Var(var));
    }

    /**
     *
     * Constructor for the Div class :
     * take Expression and String and adds them to the methods.
     *
     * @param varLeft a given string of Variable.
     * @param rightE the right Expression
     */
    public Div(String varLeft, Expression rightE) {
        super.setLeftExpression(new Var(varLeft));
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Div class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param numRight a given right number.
     */
    public Div(Expression leftE, double numRight) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Num(numRight));
    }

    /**
     *
     * Constructor for the Div class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param num a given left number
     * @param rightE the right Expression
     */
    public Div(double num, Expression rightE) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Div class :
     * take two given strings and adds them to the methods.
     *
     * @param leftVar a given left string
     * @param rightVar a given left string
     */
    public Div(String leftVar, String rightVar) {
        super.setLeftExpression(new Var(leftVar));
        super.setRightExpression(new Var(rightVar));
    }

    /**
     * Constructor for the Div class :
     * take two given numbers and adds them to the methods.
     *
     * @param numLeft a given left number.
     * @param numRight a given right number.
     *
     */
    public Div(double numLeft, double numRight) {
        super.setLeftExpression(new Num(numLeft));
        super.setRightExpression(new Num(numRight));
    }

    /**
     *
     * The function get two Expressions Value, do the operation Class
     * and return the new number.
     *
     * @param leftEx the left Expression value.
     * @param rightEx the right Expression value.
     * @return the value of the two numbers after the operation.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    @Override
    public double doMathOperation(double leftEx, double rightEx) throws Exception {
        // check if the divisor number is not a zero.
        if (rightEx == 0) {
            throw new Exception("Math Error division by zero");
        }
        return leftEx / rightEx;
    }

    /**
     *
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    @Override
    public String operator() {
        return " / ";
    }

    /**
     *
     * The function Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var The variable string for indication.
     * @param expression the provided expression
     * @return an new expression after the assign
     */
    public Expression assign(String var, Expression expression) {
        return new Div(super.getLeftExpression().assign(var, expression)
                , super.getRightExpression().assign(var, expression));
    }

    /**
     *
     * The function Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var derivative by variable.
     * @return the new Expression after the differentiate.
     */
    public Expression differentiate(String var) {
        Expression numerator = new Minus(new Mult(super.getLeftExpression().differentiate(var)
                , super.getRightExpression()), new Mult(super.getLeftExpression()
                , super.getRightExpression().differentiate(var)));
        Expression denominator = new Pow(super.getRightExpression(), 2);
        return new Div(numerator, denominator);
    }

    /**
     * The function simplify the Expression and returned a
     *  simplified version of the current expression.
     * @return simplified version of the current expression.
     */
    public Expression simplify() {
        // Try to calculate the Expression if the Expression contain a variable an Exception throws.
        try {
            double left = super.getLeftExpression().evaluate();
            double right = super.getRightExpression().evaluate();
            return new Num(new Div(super.getLeftExpression(), super.getRightExpression()).evaluate());
        // If an Exception throws simplify the Expression with Variables.
        } catch (Exception e) {
            Expression leftEx = super.getLeftExpression().simplify();
            Expression rightEx = super.getRightExpression().simplify();
            // If the left Expression is of the form of (0.0) return 0
            if (leftEx.toString().equals("0.0")) {
                return new Num(0);
            }
            // If the Expressions are equals return 1
            if (leftEx.toString().equals(rightEx.toString())
                    || rightEx.swapSides(rightEx).toString().equals(leftEx.toString())) {
                return new Num(1);
            }
            // If the right Expression is of the form of (1.0) return left Expression
            if (rightEx.toString().equals("1.0")) {
                return leftEx;
            }
            // otherwise return the new Expression after the simplify.
            return new Div(leftEx, rightEx);
        }
    }

    /**
     * The function the function return the inner Expression in the class.
     *
     * @param indication true to get the left Expression false to get the left Expression.
     * @return the left or the right Expression.
     */
    public Expression getExpressions(boolean indication) {
        if (indication) {
            return super.getLeftExpression();
        } else {
            return super.getRightExpression();
        }
    }

    /**
     * The function more simplify the Expression and returned a
     *  simplified version of the current expression.
     *  The simplification:
     *  1. ((x^y)/(x^z)) -> (x^(y-z))
     *
     * @return new Expression after Simplify.
     */
    public Expression moreSimplify() {
        try {
            // Simplify the Expression.
            Expression simplifyEx = this.simplify();
            // do a recursion with the function moreSimplify
            Expression left = simplifyEx.getExpressions(true).moreSimplify();
            Expression right = simplifyEx.getExpressions(false).moreSimplify();
            // if the Expressions is Pow
            if (isPow(left) && isPow(right)) {
                // if the bases are equals
                if (left.getExpressions(true).toString().equals(right.getExpressions(true).toString())
                    || left.getExpressions(true).toString().equals(right.swapSides(right.getExpressions(true)).
                        toString())) {
                    // return ((x^y)/(x^z)) -> (x^(y-z))
                    return new Pow(left.getExpressions(true),
                            new Minus(left.getExpressions(false), right.getExpressions(false)));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
        //return new Div(super.getLeftExpression().moreSimplify(), super.getRightExpression().moreSimplify());
    }
}
