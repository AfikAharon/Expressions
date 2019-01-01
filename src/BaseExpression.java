import java.util.Map;

/**
 * a BaseExpression class class.
 * <p>
 * an abstract class representing a base expression.
 *
 * @author Afik Aharon.
 */
public abstract class BaseExpression {
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
    protected abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the new number after calculating.
     * @throws Exception If the Expression contain a var.
     */
    protected abstract double evaluate() throws Exception;

    /**
     * The function check if the given expression is of type Pow.
     *
     * @param expression a given expression
     * @return true if the expression is Of a type Pow, otherwise false.
     */
    protected boolean isPow(Expression expression) {
        return (expression instanceof Pow);
    }

    /**
     * The function check if the given expression is of type Mult.
     *
     * @param expression a given expression
     * @return true if the expression is Of a type Mult, otherwise false.
     */
    protected boolean isMult(Expression expression) {
        return (expression instanceof Mult);
    }

    /**
     * The function check if the given expression is of type Plus.
     *
     * @param expression a given expression
     * @return true if the expression is Of a type Plus, otherwise false.
     */
    protected boolean isPlus(Expression expression) {
        return (expression instanceof Plus);
    }

    /**
     * The function check if the given expression is of type Minus.
     *
     * @param expression a given expression
     * @return true if the expression is Of a type Minus, otherwise false.
     */
    protected boolean isMinus(Expression expression) {
        return (expression instanceof Minus);
    }

    /**
     * The function check if the given expression is of type Var.
     *
     * @param expression a given expression
     * @return true if the expression is Of a type Var, otherwise false.
     */
    protected boolean isVar(Expression expression) {
        return (expression instanceof Var);
    }

    /**
     * The function check if the given expression is of type Neg.
     *
     * @param expression a given expression
     * @return true if the expression is Of a type Var, otherwise false.
     */
    protected boolean isNeg(Expression expression) {
        return (expression instanceof Neg);
    }

    /**
     * The function check if the given expression is of type Num.
     *
     * @param expression a given expression
     * @return true if the expression is Of a type Num, otherwise false.
     */
    protected boolean isNum(Expression expression) {
        return (expression instanceof Num);
    }

    /**
     * The function check if a given number is a even number (even root or even number).
     *
     * @param pow a given number
     * @return true if the root is even otherwise false.
     */
    public boolean checkIfPowerIsEven(double pow) {
        double check = 0;
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                check = (double) i / (double) j;
                if (check == pow) {
                    // check the denominator is even.
                    if (j % 2 == 0) {
                        return true;
                    }
                    return false;

                }
            }
        }
        return false;
    }
}
