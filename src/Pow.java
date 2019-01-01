/**
 * a Pow Class.
 * <p>
 * The class calculate the Pow of Expression, inherent
 * from the BinaryExpression Class and implements to
 * Expression class.
 *
 * @author Afik Aharon.
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Constructor for the Pow class :
     * take number and string and make them to
     * Expressions.
     *
     * @param var a given string of Variable.
     * @param num a given number.
     */
    public Pow(String var, double num) {
        super.setLeftExpression(new Var(var));
        super.setRightExpression(new Num(num));
    }

    /**
     * Constructor for the Pow class :
     * take number and string and make them to
     * Expressions.
     *
     * @param num a given number.
     * @param var a given string of Variable.
     */
    public Pow(double num, String var) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(new Var(var));
    }

    /**
     * Constructor for the Pow class :
     * take two Expression and adds them to the methods.
     *
     * @param leftE  the left Expression.
     * @param rightE the right Expression.
     */
    public Pow(Expression leftE, Expression rightE) {
        super.setLeftExpression(leftE);
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Pow class :
     * take Expression and String and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param var   a given string of Variable.
     */
    public Pow(Expression leftE, String var) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Var(var));
    }

    /**
     * Constructor for the Pow class :
     * take Expression and String and adds them to the methods.
     *
     * @param varLeft a given string of Variable.
     * @param rightE  the right Expression
     */
    public Pow(String varLeft, Expression rightE) {
        super.setLeftExpression(new Var(varLeft));
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Pow class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param leftE    the left Expression.
     * @param numRight a given right number.
     */
    public Pow(Expression leftE, double numRight) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Num(numRight));
    }

    /**
     * Constructor for the Pow class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param num    a given left number
     * @param rightE the right Expression
     */
    public Pow(double num, Expression rightE) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Pow class :
     * take two given strings and adds them to the methods.
     *
     * @param leftVar  a given left string
     * @param rightVar a given left string
     */
    public Pow(String leftVar, String rightVar) {
        super.setLeftExpression(new Var(leftVar));
        super.setRightExpression(new Var(rightVar));
    }

    /**
     * Constructor for the Pow class :
     * take two given numbers and adds them to the methods.
     *
     * @param numLeft  a given left number.
     * @param numRight a given right number.
     */
    public Pow(double numLeft, double numRight) {
        super.setLeftExpression(new Num(numLeft));
        super.setRightExpression(new Num(numRight));
    }

    /**
     * The function get two Expressions Value, do the operation Class
     * and return the new number.
     *
     * @param leftEx  the left Expression value.
     * @param rightEx the right Expression value.
     * @return the value of the two numbers after the operation.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    @Override
    public double doMathOperation(double leftEx, double rightEx) throws Exception {
        // Check if the base is odd and the pow is even root.
        if (leftEx < 0 && super.checkIfPowerIsEven(rightEx)) {
            int check = (int) rightEx;
            // check if the number is not an integer.
            if ((double) check != rightEx) {
                throw new Exception("Math error sqrt with a negative number have a even pow");
            }
        }
        // Check if the base is zero and the po is negative.
        if (leftEx == 0 && rightEx <= 0) {
            throw new Exception("Math error division by zero");
        }
        return Math.pow(leftEx, rightEx);
    }

    /**
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    @Override
    public String operator() {
        return "^";
    }

    /**
     * The function Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        The variable string for indication.
     * @param expression the provided expression
     * @return an new expression after the assign
     */
    public Expression assign(String var, Expression expression) {
        return new Pow(super.getLeftExpression().assign(var, expression)
                , super.getRightExpression().assign(var, expression));
    }

    /**
     * The function Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var derivative by variable.
     * @return the new Expression after the differentiate.
     */
    public Expression differentiate(String var) {
        Expression leftMult = new Pow(super.getLeftExpression(), super.getRightExpression());
        Expression right = new Mult(super.getRightExpression().differentiate(var)
                , new Log("e", super.getLeftExpression()));
        Expression left = new Mult(super.getLeftExpression().differentiate(var)
                , new Div(super.getRightExpression(), super.getLeftExpression()));
        return new Mult(leftMult, new Plus(left, right));
    }

    /**
     * The function simplify the Expression and returned a
     * simplified version of the current expression.
     *
     * @return simplified version of the current expression.
     */
    public Expression simplify() {
        // Try to calculate the Expression if the Expression contain a variable an Exception throws.
        try {
            double left = super.getLeftExpression().evaluate();
            double right = super.getRightExpression().evaluate();
            return new Num(new Pow(super.getLeftExpression(), super.getRightExpression()).evaluate());
            // If an Exception throws simplify the Expression with Variables.
        } catch (Exception e) {
            Expression leftEx = super.getLeftExpression().simplify();
            Expression rightEx = super.getRightExpression().simplify();
            return new Pow(leftEx, rightEx);
        }
    }

    /**
     * The function more simplify the Expression and returned a
     * simplified version of the current expression.
     * The simplification:
     * 1. (x^1.0)-> x
     * 2. (x^0.0)-> 1
     * 3. ((x^y)^z) -> (x^(y*z))
     * 4. (x^(-y))->(1.0/(x^y))
     *
     * @return new Expression after Simplify.
     */
    public Expression moreSimplify() {
        try {
            Expression simplifyEx = this.simplify();
            Expression left = simplifyEx.getExpressions(true).moreSimplify();
            Expression right = simplifyEx.getExpressions(false).moreSimplify();
            // If the pow is 1 return the Expression
            if (right.toString().equals("1.0")) {
                return left;
            }
            // If the pow is 0 return num 1
            if (right.toString().equals("0.0")) {
                return new Num(1);
            }
            // if the power is negative
            if (isNeg(right)) {
                // return (x^(-y))->(1.0/(x^y))
                return new Div(1, new Pow(left, right.getExpressions(true)));
            }
            // if the left is Pow
            if (isPow(left)) {
                // return ((x^y)^z) -> (x^(y*z))
                return new Pow(left.getExpressions(true), new Mult(left.getExpressions(false), right)).simplify();
            }
            return new Pow(super.getLeftExpression(), super.getRightExpression()).simplify();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
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

}
