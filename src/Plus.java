/**
 * a Plus Class.
 * <p>
 * The class calculate with the operator Plus, inherent
 * from the BinaryExpression Class and implements to
 * Expression class.
 *
 * @author Afik Aharon.
 */
public class Plus extends BinaryExpression implements Expression {
    /**
     * Constructor for the Plus class :
     * take number and string and make them to
     * Expressions.
     *
     * @param var a given string of Variable.
     * @param num a given number.
     */
    public Plus(String var, double num) {
        super.setLeftExpression(new Var(var));
        super.setRightExpression(new Num(num));
    }

    /**
     * Constructor for the Plus class :
     * take number and string and make them to
     * Expressions.
     *
     * @param num a given number.
     * @param var a given string of Variable.
     */
    public Plus(double num, String var) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(new Var(var));
    }

    /**
     * Constructor for the Plus class :
     * take two Expression and adds them to the methods.
     *
     * @param leftE  the left Expression.
     * @param rightE the right Expression.
     */
    public Plus(Expression leftE, Expression rightE) {
        super.setLeftExpression(leftE);
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Plus class :
     * take Expression and String and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param var   a given string of Variable.
     */
    public Plus(Expression leftE, String var) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Var(var));
    }

    /**
     * Constructor for the Plus class :
     * take Expression and String and adds them to the methods.
     *
     * @param varLeft a given string of Variable.
     * @param rightE  the right Expression
     */
    public Plus(String varLeft, Expression rightE) {
        super.setLeftExpression(new Var(varLeft));
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Plus class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param leftE    the left Expression.
     * @param numRight a given right number.
     */
    public Plus(Expression leftE, double numRight) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Num(numRight));
    }

    /**
     * Constructor for the Plus class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param num    a given left number
     * @param rightE the right Expression
     */
    public Plus(double num, Expression rightE) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Plus class :
     * take two given strings and adds them to the methods.
     *
     * @param leftVar  a given left string
     * @param rightVar a given left string
     */
    public Plus(String leftVar, String rightVar) {
        super.setLeftExpression(new Var(leftVar));
        super.setRightExpression(new Var(rightVar));
    }

    /**
     * Constructor for the Plus class :
     * take two given numbers and adds them to the methods.
     *
     * @param numLeft  a given left number.
     * @param numRight a given right number.
     */
    public Plus(double numLeft, double numRight) {
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
        return leftEx + rightEx;
    }

    /**
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    @Override
    public String operator() {
        return " + ";
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
        return new Plus(super.getLeftExpression().assign(var, expression),
                super.getRightExpression().assign(var, expression));
    }

    /**
     * The function Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var derivative by variable.
     * @return the new Expression after the differentiate.
     */
    public Expression differentiate(String var) {
        return new Plus(super.getLeftExpression().differentiate(var), super.getRightExpression().differentiate(var));
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
            return new Num(new Plus(super.getLeftExpression(), super.getRightExpression()).evaluate());
            // If an Exception throws simplify the Expression with Variables.
        } catch (Exception e) {
            Expression leftEx = super.getLeftExpression().simplify();
            Expression rightEx = super.getRightExpression().simplify();
            // If the Expression is of the form of (0+x) return x
            if (leftEx.toString().equals("0.0")) {
                return rightEx;
            }
            // If the Expression is of the form of (x+0) return x
            if (rightEx.toString().equals("0.0")) {
                return leftEx;
            }
            // If the left Expression equal to the right Expression return 0.
            if (rightEx.toString().equals(leftEx.toString())
                    || rightEx.swapSides(rightEx).toString().equals(leftEx.toString())) {
                return new Mult(2, leftEx);
            }
            // otherwise return the new Expression after the simplify.
            return new Plus(leftEx, rightEx);
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
     *
     * The function more simplify the Expression and returned a
     * simplified version of the current expression, check if the left Expression
     * is a variable, Mult or Num after that call the functions simplifyIfLeftIsVar
     * simplifyIfLeftIsMult, simplifyIfLeftIsNum for more simplification.
     *  The simplification:
     *  1. (x +(-(x + 1)) -> (x-(x+1))
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
            // If the Expression is of form (x +(-(x + 1)) do recursion and return (x-(x+1))
            if (isNeg(right)) {
                return new Minus(left, right.getExpressions(true)).moreSimplify();
            }
            // If the left Expression is variable
            if (isVar(left)) {
                Expression simpEx = this.simplifyIfLeftIsVar(left, right);
                if (simpEx != null) {
                    return simpEx;
                }
            }
            // If the left Expression is Mult
            if (isMult(left)) {
                Expression simpEx = this.simplifyIfLeftIsMult(left, right);
                if (simpEx != null) {
                    return simpEx;
                }
            }
            // If the left Expression is Num
            if (isNum(left)) {
                Expression simpEx = this.simplifyIfLeftIsNum(left, right);
                if (simpEx != null) {
                    return simpEx;
                }
            }
            // otherwise return the same Expression
            return new Plus(left, right);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    /**
     *
     * The function simplify Expression if the left Expression is a Var.
     * The simplification:
     *
     * 1. (x + ( 2x + 3)) -> (3x + 3)
     * 2. (x + (3 + 2x)) -> (3x + 3)
     * 3. (x + (x+3)) -> (2x + 3)
     * 4. (x + (3+x)) -> (2x + 3)
     * 5. (x +(2x-1)) -> (3x -1)
     * 6. (x + (1-2x))->(1-x)
     * 7. (x+(x-2))->(2x-2)
     * 8. (x+(1-x)) -> 1
     *
     * @param left the left expression class
     * @param right the right expression class
     * @return the simplify Expression or null if the expression is simplify.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    public Expression simplifyIfLeftIsVar(Expression left, Expression right) throws Exception {
        // If the right Expression is Mult
        if (isMult(right)) {
            // If the variables are egual.
            if (left.toString().equals(right.getExpressions(false).toString())) {
                // return (x +  2x ) -> 3x
                return new Mult(new Num(1 + right.getExpressions(true).evaluate()), left).simplify();
            }
        }
        // If the right Expression is Plus.
        if (isPlus(right)) {
            // if the right left inner  Expression is Mult.
            if (isMult(right.getExpressions(true))) {
                // If the variables are egual.
                if (left.toString().equals(right.getExpressions(true).getExpressions(false).toString())) {
                    // return (x + ( 2x + 3)) -> (3x + 3)
                    return new Plus(new Mult(1
                            + right.getExpressions(true).getExpressions(false).evaluate(),
                            left), right.getExpressions(false)).simplify();
                }
            }
            // if the right right inner  Expression is Mult.
            if (isMult(right.getExpressions(false))) {
                // If the variables are egual.
                if (left.toString().equals(right.getExpressions(false).getExpressions(false).toString())) {
                    // return (x + (3 + 2x)) -> (3x + 3)
                    return new Plus(new Mult(1
                            + right.getExpressions(false).getExpressions(false).evaluate(),
                            left), right.getExpressions(true)).simplify();
                }
            }
            // if the right left inner  Expression is variable.
            if (isVar(right.getExpressions(true))
                    && left.toString().equals(right.getExpressions(true).toString())) {
                // return (x + (x+3)) -> (2x + 3)
                return new Plus(new Mult(2, left), right.getExpressions(false)).simplify();
            }
            // if the right right inner  Expression is variable.
            if (isVar(right.getExpressions(false))
                    && left.toString().equals(right.getExpressions(false).toString())) {
                // return (x + (3+x)) -> (2x + 3)
                return new Plus(new Mult(2, left), right.getExpressions(true)).simplify();
            }
        }
        // If the right Expression is variable return (x + x) -> 2x
        if (isVar(right) && left.toString().equals(right.toString())) {
            return new Mult(2, left).simplify();
        }
        // If the right Expression is Minus.
        if (isMinus(right)) {
            // If the right left inner  Expression is Mult.
            if (isMult(right.getExpressions(true))) {
                if (left.toString().equals(right.getExpressions(true).getExpressions(false).toString())) {
                    // return (x +(2x-1)) -> (3x -1)
                    return new Minus(new Mult(1
                            + right.getExpressions(true).getExpressions(true).evaluate(),
                            left), right.getExpressions(false)).simplify();
                }
            }
            // If the right right inner  Expression is Mult.
            if (isMult(right.getExpressions(false))) {
                if (left.toString().equals(right.getExpressions(false).getExpressions(false).toString())) {
                    // return (x + (1-2x))->(1-x)
                    return new Minus(right.getExpressions(true),
                            new Mult(right.getExpressions(true).getExpressions(false).evaluate() - 1,
                                    left)).simplify();
                }
            }
            // if the right left inner  Expression is variable.
            if (isVar(right.getExpressions(true))
                    && left.toString().equals(right.getExpressions(true).toString())) {
                // return (x+(x-2))->(2x-2)
                return new Minus(new Mult(2, left), right.getExpressions(false)).simplify();
            }
            // if the right right inner  Expression is variable.
            if (isVar(right.getExpressions(false))
                    && left.toString().equals(right.getExpressions(false).toString())) {
                // return (x+(1-x)) -> 1
                return right.getExpressions(true).simplify();
            }
        }
        // other wise return null
        return null;
    }

    /**
     *
     * The function simplify Expression if the left Expression is a Mult.
     * The simplification:
     *
     * 1. (2x+x) -> 3x
     * 2. (2x + 2x) -> 4x
     * 3. (2x +(2x +3)_->(4x+3)
     * 4. (2x+(3+2x))->(4x+3)
     * 5. (2x+(x+3))-> (3x+3)
     * 6. (2x+(3+x))-> (3x+3)
     *
     * @param left the left expression class
     * @param right the right expression class
     * @return the simplify Expression or null if the expression is simplify.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    public Expression simplifyIfLeftIsMult(Expression left, Expression right) throws Exception {
        // if the right Expression is variable
        if (isVar(right)) {
            // If the variables are egual.
            if (right.toString().equals(left.getExpressions(false).toString())) {
                // return (2x+x) -> 3x
                return new Mult(1 + left.getExpressions(true).evaluate(), right).simplify();
            }
        }
        // if the right Expression is Mult
        if (isMult(right)) {
            // If the variables are egual.
            if (left.getExpressions(false).toString().equals(right.getExpressions(false).toString())) {
                // return (2x + 2x) -> 4x
                return new Mult(new Num(left.getExpressions(true).evaluate()
                        + right.getExpressions(true).evaluate()), left.getExpressions(false)).simplify();
            }
        }
        // If the right Expression is Plus
        if (isPlus(right)) {
            // if the right left inner  Expression is Mult.
            if (isMult(right.getExpressions(true))) {
                if (left.getExpressions(false).toString().equals(right.getExpressions(true)
                        .getExpressions(false).toString())) {
                    // return (2x +(2x +3)_->(4x+3)
                    return new Plus(new Mult(left.getExpressions(true).evaluate()
                            + right.getExpressions(true).getExpressions(true).evaluate()
                            , left.getExpressions(false)), right.getExpressions(false)).simplify();
                }
            }
            // if the right right inner  Expression is Mult.
            if (isMult(right.getExpressions(false))) {
                if (left.getExpressions(false).toString().equals(right.getExpressions(false)
                        .getExpressions(false).toString())) {
                    // return (2x+(3+2x))->(4x+3)
                    return new Plus(new Mult(left.getExpressions(true).evaluate()
                            + right.getExpressions(false).getExpressions(true).evaluate()
                            , left.getExpressions(false)), right.getExpressions(true)).simplify();
                }
            }
            // if the right left inner  Expression is Var.
            if (isVar(right.getExpressions(true))) {
                if (left.getExpressions(false).toString().equals(right.getExpressions(true).toString())) {
                    // return (2x+(x+3))-> (3x+3)
                    return new Plus(new Mult(1 + left.getExpressions(true).evaluate()
                            , right.getExpressions(true)), right.getExpressions(false)).simplify();
                }
            }
            // if the right left inner  Expression is Var.
            if (isVar(right.getExpressions(false))) {
                if (left.getExpressions(false).toString().equals(right.getExpressions(false).toString())) {
                    // return (2x+(3+x))-> (3x+3)
                    return new Plus(new Mult(1 + left.getExpressions(true).evaluate()
                            , right.getExpressions(false)), right.getExpressions(true)).simplify();
                }
            }
        }
        // otherwise return null
        return null;
    }

    /**
     *
     * The function simplify Expression if the left Expression is a Num.
     * The simplification:
     *
     * 1. (3+(3+x))-> (x+6)
     * 2. (3+(x+3))-> (x+6)
     * 3. (3+(3-x))->(6-x)
     * 4. (3+(x-4))->(x-1)
     *
     * @param left the left expression class
     * @param right the right expression class
     * @return the simplify Expression or null if the expression is simplify.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    public Expression simplifyIfLeftIsNum(Expression left, Expression right) throws Exception {
        // If the right Expression is Mult
        if (isMult(right)) {
            // return the same Expression
            return new Plus(right, left);
        }
        // If the right Expression is Mult
        if (isPlus(right)) {
            // if the right left inner  Expression is Num.
            if (isNum(right.getExpressions(true))) {
                // (3+(3+x))-> (x+6)
                return new Plus(right.getExpressions(false), left.evaluate()
                        + right.getExpressions(true).evaluate()).simplify();
            }
            // if the right right inner  Expression is Num.
            if (isNum(right.getExpressions(false))) {
                // (3+(x+3))-> (x+6)
                return new Plus(right.getExpressions(true), left.evaluate()
                        + right.getExpressions(false).evaluate()).simplify();
            }
        }
        // If the right Expression is Minus
        if (isMinus(right)) {
            // if the right left inner  Expression is Num.
            if (isNum(right.getExpressions(true))) {
                // return (3+(3-x))->(6-x)
                return new Minus(left.evaluate() + right.getExpressions(true).evaluate(),
                        right.getExpressions(false)).simplify();
            }
            // if the right right inner  Expression is Num.
            if (isNum(right.getExpressions(false))) {
                // return (3+(x-4))->(x-1)
                return new Minus(right.getExpressions(true), right.getExpressions(false).evaluate()
                        - left.evaluate()).simplify();
            }
        }
        // otherwise return null
        return null;
    }
}
