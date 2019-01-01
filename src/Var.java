import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * a Var class.
 *
 * @author Afik Aharon.
 */
public class Var implements Expression {
    private String variable;

    /**
     * construct a Var num.
     *
     * @param v given Var.
     */
    public Var(String v) {
        this.variable = v;
    }

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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        } else {
            throw new Exception("One of the Expression is a variable");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the new number after calculating.
     * @throws Exception If the Expression contain a var.
     */
    public double evaluate() throws Exception {
        Map<String, Double> emptyMap = new TreeMap<String, Double>();
        return evaluate(emptyMap);
    }

    /**
     * The function returns a nice string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    public String toString() {
        return this.variable;
    }

    /**
     * The function returns a nice string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list.add(this.variable);
        return list;
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
        if (var.equals(this.variable)) {
            return expression;
        } else {
            return this;
        }
    }

    /**
     * The function Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var derivative by variable.
     * @return the new Expression after the differentiate.
     */
    public Expression differentiate(String var) {
        if (this.variable.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /**
     * The function simplify the Expression and returned a
     * simplified version of the current expression.
     *
     * @return simplified version of the current expression.
     */
    public Expression simplify() {
        return this;
    }

    /**
     * The function more simplify the Expression and returned a
     * simplified version of the current expression.
     *
     * @return new Expression after Simplify.
     */
    public Expression moreSimplify() {
        return this;
    }

    /**
     * The function return the inner Expression in the class.
     * The indication variable is for the binary class
     *
     * @param indication The indication variable is for the binary class.
     * @return the Expression.
     */
    public Expression getExpressions(boolean indication) {
        return this;
    }

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
}
