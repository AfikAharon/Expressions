/**
 * a Neg Class.
 * <p>
 * The class holds a negative Expression, inherent
 * from the UnaryExpression Class and implements to
 * Expression class.
 *
 * @author Afik Aharon.
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * Constructor for the Neg class.
     *
     * @param exp a given string of Variable.
     */
    public Neg(Expression exp) {
        super.setExpression(exp);
    }

    /**
     * Constructor for the Neg class :
     * take a string and cast to Expression.
     *
     * @param var a given string of Variable.
     */
    public Neg(String var) {
        super.setExpression(new Var(var));
    }

    /**
     * Constructor for the Neg class :
     * take a number and cast to Expression.
     *
     * @param num a given number of Variable.
     */
    public Neg(double num) {
        super.setExpression(new Num(num));
    }

    /**
     * The function Expression Value, do the operation Class
     * and return the new number.
     *
     * @param exValue  the left Expression value.
     * @return the value of the two numbers after the operation.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    @Override
    public double doMathOperation(double exValue) throws Exception {
        return (-1) * exValue;
    }

    /**
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    @Override
    public String operator() {
        return "-";
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
        return new Neg(super.getExpression().assign(var, expression));
    }

    /**
     * The function Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var derivative by variable.
     * @return the new Expression after the differentiate.
     */
    public Expression differentiate(String var) {
        return new Neg(super.getExpression().differentiate(var));
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
            double num = super.getExpression().evaluate();
            // If the Expression is of the form of (-0.0) return 0
            if (num == 0) {
                return new Num(num);
            } else {
                num = new Mult(-1, num).evaluate();
                return new Num(num);
            }
        // If an Exception throws simplify the Expression with Variables.
        } catch (Exception e) {
            return new Neg(super.getExpression().simplify());
        }
    }

    /**
     * The function return the inner Expression in the class.
     * The indication variable is for the binary class
     *
     * @param indication The indication variable is for the binary class.
     * @return the Expression.
     */
    public Expression getExpressions(boolean indication) {
        return super.getExpression();
    }

    /**
     * The function more simplify the Expression and returned a
     * simplified version of the current expression.
     * The simplification:
     * (-(-x))-> x
     *
     * @return new Expression after Simplify.
     */
    public Expression moreSimplify() {
        try {
            // Simplify the Expression.
            Expression simplifyEx = this.simplify();
            // do a recursion with the function moreSimplify
            Expression ex = simplifyEx.getExpressions(true).moreSimplify();
            super.setExpression(ex);
            // if the inner Expression is a Neg return (-(-x))-> x
            if (isNeg(ex)) {
                return ex.getExpressions(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
