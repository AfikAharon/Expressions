import java.util.List;
import java.util.Map;

/**
 * a Expression interface.
 *
 * @author Afik Aharon.
 */
public interface Expression {
    /**
     * Evaluates the expression using the variable values provided
     * in the assignment, and returns the result.
     * If the expression contains a variable which is not in the assignment,
     * an exception is thrown.
     *
     * @param assignment a map with variable and theirs values.
     * @return the calculated expression.
     * @throws Exception If the expression contains a variable which is not in the assignment.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the new number after calculating.
     * @throws Exception If the Expression contain a var.
     */
    double evaluate() throws Exception;

    /**
     * The function returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * The function returns a nice string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    String toString();

    /**
     * The function Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        The variable string for indication.
     * @param expression the provided expression
     * @return an new expression after the assign
     */
    Expression assign(String var, Expression expression);

    /**
     * The function Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var derivative by variable.
     * @return the new Expression after the differentiate.
     */
    Expression differentiate(String var);

    /**
     * The function simplify the Expression and returned a
     * simplified version of the current expression.
     *
     * @return simplified version of the current expression.
     */
    Expression simplify();

    /**
     * The function more simplify the Expression and returned a
     * simplified version of the current expression.
     *
     * @return new Expression after Simplify.
     */
    Expression moreSimplify();

    /**
     * The function the function return the inner Expression in the class.
     *
     * @param indication true to get the left Expression false to get the left Expression.
     * @return the left or the right Expression.
     */
    Expression getExpressions(boolean indication);

    /**
     * The function take Expression with 2 Expressions,
     * and swap the sides of the expressions and return a new Expression.
     *
     * @param e Expression for the swap.
     * @return new swap Expression.
     */
    Expression swapSides(Expression e);
}