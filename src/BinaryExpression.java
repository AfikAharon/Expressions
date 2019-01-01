import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.ArrayList;

/**
 * * a BinaryExpression class.
 * an abstract class representing a binary expression.
 *
 * @author Afik Aharon.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression leftExpression;
    private Expression rightExpression;

    /**
     *
     * @return The leftExpression member.
     */
    public Expression getLeftExpression() {
        return leftExpression;
    }

    /**
     *
     * @return The rightExpression member.
     */
    public Expression getRightExpression() {
        return rightExpression;
    }

    /**
     * Set function for the leftExpression.
     *
     * @param leftEx a given Expression
     */
    public void setLeftExpression(Expression leftEx) {
        this.leftExpression = leftEx;
    }

    /**
     * Set function for the leftExpression.
     *
     * @param rightEx a given Expression
     */
    public void setRightExpression(Expression rightEx) {
        this.rightExpression = rightEx;
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
    protected abstract double doMathOperation(double leftEx, double rightEx) throws Exception;

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
        return doMathOperation(this.leftExpression.evaluate(assignment), this.rightExpression.evaluate(assignment));
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
        double leftValue = this.leftExpression.evaluate();
        double rightValue = this.rightExpression.evaluate();
        return doMathOperation(leftValue, rightValue);
    }

    /**
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    protected abstract String operator();

    /**
     * The function returns a nice string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    public String toString() {
        String operator = this.operator();
        // The operator Log appears before parentheses.
        if (operator.equals("log")) {
            String st = operator + "(" + this.leftExpression.toString() + ", " + this.rightExpression.toString() + ")";
            return st;
        } else {
            String st = "(" + this.leftExpression.toString() + operator + this.rightExpression.toString() + ")";
            return st;
        }
    }

    /**
     * The function take Expression with 2 Expressions,
     * and swap the sides of the expressions and return a new Expression.
     *
     * @param e Expression for the swap.
     * @return new swap Expression.
     */
    public Expression swapSides(Expression e) {
        Expression tempL = e.getExpressions(true);
        Expression tempR = e.getExpressions(false);
        // put a temp string for the swap.
        Expression tempE = e.assign(tempL.toString(), new Var("$"));
        tempE = tempE.assign(tempR.toString(), tempL);
        tempE = tempE.assign("$", tempR);
        return tempE;
    }

    /**
     * The function returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        Set<String> set = new TreeSet<String>();
        set.addAll(this.leftExpression.getVariables());
        set.addAll(this.rightExpression.getVariables());
        variables.addAll(set);
        return variables;
    }
}
