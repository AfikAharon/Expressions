import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.ArrayList;

/**
 * a UnaryExpression class.
 * <p>
 * an abstract class representing an unary expression.
 *
 * @author Afik Aharon.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    /**
     * @return The leftExpression member.
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Set function for the expression member.
     *
     * @param exp a given Expression
     */
    public void setExpression(Expression exp) {
        this.expression = exp;
    }

    /**
     * The function get Expression Value, do the operation Class
     * and return the new number.
     *
     * @param exValue the Expression value.
     * @return the value of the number after the operation.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    protected abstract double doMathOperation(double exValue) throws Exception;

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
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return doMathOperation(this.expression.evaluate(assignment));
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the new number after calculating.
     * @throws Exception If the Expression contain a var.
     */
    @Override
    public double evaluate() throws Exception {
        return doMathOperation(this.expression.evaluate());
    }

    /**
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    protected abstract String operator();

    /**
     * The function take Expression with 2 Expressions,
     * and swap the sides of the expressions and return a new Expression.
     *
     * @param e Expression for the swap.
     * @return new swap Expression.
     */
    public Expression swapSides(Expression e) {
        return e;
    }

    /**
     * The function returns a nice string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    public String toString() {
        String operator = this.operator();
        if (operator.equals("-")) {
            return "(" + operator + this.expression.toString() + ")";
        }
        return operator + "(" + this.expression.toString() + ")";
    }

    /**
     * The function returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        Set<String> set = new TreeSet<String>();
        set.addAll(this.expression.getVariables());
        variables.addAll(set);
        return variables;
    }
}