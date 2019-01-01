/**
 * a Log Class.
 *
 * The class calculate Log of Expression, inherent
 * from the BinaryExpression Class and implements to
 * Expression class.
 *
 * @author Afik Aharon.
 */
public class Log extends BinaryExpression implements Expression {
    /**
     *
     * Constructor for the Log class :
     * take number and string and make them to
     * Expressions.
     *
     * @param var a given string of Variable.
     * @param num a given number.
     */
    public Log(String var, double num) {
        super.setLeftExpression(new Var(var));
        super.setRightExpression(new Num(num));
    }

    /**
     *
     * Constructor for the Log class :
     * take number and string and make them to
     * Expressions.
     *
     * @param num a given number.
     * @param var a given string of Variable.
     */
    public Log(double num, String var) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(new Var(var));
    }

    /**
     *
     * Constructor for the Log class :
     * take two Expression and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param rightE the right Expression.
     */
    public Log(Expression leftE, Expression rightE) {
        super.setLeftExpression(leftE);
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Log class :
     * take Expression and String and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param var a given string of Variable.
     */
    public Log(Expression leftE, String var) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Var(var));
    }

    /**
     *
     * Constructor for the Log class :
     * take Expression and String and adds them to the methods.
     *
     * @param varLeft a given string of Variable.
     * @param rightE the right Expression
     */
    public Log(String varLeft, Expression rightE) {
        super.setLeftExpression(new Var(varLeft));
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Log class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param numRight a given right number.
     */
    public Log(Expression leftE, double numRight) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Num(numRight));
    }

    /**
     *
     * Constructor for the Log class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param num a given left number
     * @param rightE the right Expression
     */
    public Log(double num, Expression rightE) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Log class :
     * take two given strings and adds them to the methods.
     *
     * @param leftVar a given left string
     * @param rightVar a given left string
     */
    public Log(String leftVar, String rightVar) {
        super.setLeftExpression(new Var(leftVar));
        super.setRightExpression(new Var(rightVar));
    }

    /**
     * Constructor for the Log class :
     * take two given numbers and adds them to the methods.
     *
     * @param numLeft a given left number.
     * @param numRight a given right number.
     *
     */
    public Log(double numLeft, double numRight) {
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
        // check if the Math operation is legal.
        if (leftEx > 0 && rightEx > 0 && Math.log(leftEx) != 0) {
            return Math.log(rightEx) / Math.log(leftEx);
        } else {
            throw new Exception("Math Error divide with zero");
        }
    }

    /**
     *
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    @Override
    public String operator() {
        return "log";
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
        return new Log(super.getLeftExpression().assign(var, expression)
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
        Expression numerator = super.getRightExpression().differentiate(var);
        Expression denominator = new Mult(super.getRightExpression(), new Log(new Var("e"), super.getLeftExpression()));
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
            return new Num(new Log(super.getLeftExpression(), super.getRightExpression()).evaluate());
        // If an Exception throws simplify the Expression with Variables.
        } catch (Exception e) {
            Expression leftEx = super.getLeftExpression().simplify();
            Expression rightEx = super.getRightExpression().simplify();
            // If the Expression is of the form of log(x,x) return 1
            if (leftEx.toString().equals(rightEx.toString())
                    || leftEx.swapSides(leftEx).toString().equals(rightEx.toString())) {
                return new Num(1);
            }
            return new Log(leftEx, rightEx);
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
     *
     * @return new Expression after Simplify.
     */
    public Expression moreSimplify() {
        return new Log(super.getLeftExpression().moreSimplify(), super.getRightExpression().moreSimplify());
    }
}

