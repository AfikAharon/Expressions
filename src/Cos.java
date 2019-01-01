/**
 * a Cos Class.
 * <p>
 * The class calculate the Cos value of Expression, inherent
 * from the UnaryExpression Class and implements to
 * Expression class.
 *
 * @author Afik Aharon.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Constructor for the Cos class.
     *
     * @param exp a given string of Variable.
     */
    public Cos(Expression exp) {
        super.setExpression(exp);
    }

    /**
     * Constructor for the Cos class :
     * take a string and cast to Expression.
     *
     * @param var a given string of Variable.
     */
    public Cos(String var) {
        super.setExpression(new Var(var));
    }

    /**
     * Constructor for the Cos class :
     * take a number and cast to Expression.
     *
     * @param num a given number of Variable.
     */
    public Cos(double num) {
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
        // if the number divide with 90 and the result is odd return 0.
        if (((exValue / 90) + 1) % 2 == 0) {
            return 0;
        }
        return Math.cos(Math.toRadians(exValue));
    }

    /**
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    @Override
    public String operator() {
        return "cos";
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
        return new Cos(super.getExpression().assign(var, expression));
    }

    /**
     * The function Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var derivative by variable.
     * @return the new Expression after the differentiate.
     */
    public Expression differentiate(String var) {
        return new Neg(new Mult(new Sin(super.getExpression()), super.getExpression().differentiate(var)));
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
            double exValue =  super.getExpression().evaluate();
            return new Num(new Cos(super.getExpression()).evaluate());
        // If an Exception throws simplify the Expression with Variables.
        } catch (Exception e) {
            Expression expre = super.getExpression().simplify();
            return new Cos(expre);
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
     *
     * @return new Expression after Simplify.
     */
    public Expression moreSimplify() {
        return new Cos(super.getExpression().moreSimplify());
    }
}
