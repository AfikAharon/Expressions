/**
 * a Minus Class.
 *
 * The class calculate with the operator Minus, inherent
 * from the BinaryExpression Class and implements to
 * Expression class.
 *
 * @author Afik Aharon.
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     *
     * Constructor for the Minus class :
     * take number and string and make them to
     * Expressions.
     *
     * @param var a given string of Variable.
     * @param num a given number.
     */
    public Minus(String var, double num) {
        super.setLeftExpression(new Var(var));
        super.setRightExpression(new Num(num));
    }

    /**
     *
     * Constructor for the Minus class :
     * take number and string and make them to
     * Expressions.
     *
     * @param num a given number.
     * @param var a given string of Variable.
     */
    public Minus(double num, String var) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(new Var(var));
    }

    /**
     *
     * Constructor for the Minus class :
     * take two Expression and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param rightE the right Expression.
     */
    public Minus(Expression leftE, Expression rightE) {
        super.setLeftExpression(leftE);
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Minus class :
     * take Expression and String and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param var a given string of Variable.
     */
    public Minus(Expression leftE, String var) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Var(var));
    }

    /**
     *
     * Constructor for the Minus class :
     * take Expression and String and adds them to the methods.
     *
     * @param varLeft a given string of Variable.
     * @param rightE the right Expression
     */
    public Minus(String varLeft, Expression rightE) {
        super.setLeftExpression(new Var(varLeft));
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Minus class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param numRight a given right number.
     */
    public Minus(Expression leftE, double numRight) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Num(numRight));
    }

    /**
     *
     * Constructor for the Minus class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param num a given left number
     * @param rightE the right Expression
     */
    public Minus(double num, Expression rightE) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(rightE);
    }

    /**
     *
     * Constructor for the Minus class :
     * take two given strings and adds them to the methods.
     *
     * @param leftVar a given left string
     * @param rightVar a given left string
     */
    public Minus(String leftVar, String rightVar) {
        super.setLeftExpression(new Var(leftVar));
        super.setRightExpression(new Var(rightVar));
    }

    /**
     * Constructor for the Minus class :
     * take two given numbers and adds them to the methods.
     *
     * @param numLeft a given left number.
     * @param numRight a given right number.
     *
     */
    public Minus(double numLeft, double numRight) {
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
       return leftEx - rightEx;
    }

    /**
     *
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    @Override
    public String operator() {
        return " - ";
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
        return new Minus(super.getLeftExpression().assign(var, expression),
                super.getRightExpression().assign(var, expression));
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
        return new Minus(super.getLeftExpression().differentiate(var) , super.getRightExpression().differentiate(var));
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
            return new Num(new Minus(super.getLeftExpression(), super.getRightExpression()).evaluate());
        // If an Exception throws simplify the Expression with Variables.
        } catch (Exception e) {
            Expression leftEx = super.getLeftExpression().simplify();
            Expression rightEx = super.getRightExpression().simplify();
            // If the Expression is of the form of (0-x) return -x
            if (leftEx.toString().equals("0.0")) {
                return new Neg(rightEx);
            }
            // If the Expression is of the form of (x-0) return x
            if (rightEx.toString().equals("0.0")) {
                return leftEx;
            }
            // If the left Expression equal to the right Expression return 0.
            if (rightEx.toString().equals(leftEx.toString())
                    || rightEx.swapSides(rightEx).toString().equals(leftEx.toString())) {
                return new Num(0);
            }
            // otherwise return the new Expression after the simplify.
            return new Minus(leftEx, rightEx);
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
     * The simplification:
     * 1. (x -(-(x + 1)) -> (x+(x+1))
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
            // If the Expression is of form (x -(-(x + 1)) do recursion and return (x+(x+1))
            if (isNeg(right)) {
                return new Plus(left, right.getExpressions(true)).moreSimplify();

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
            return new Minus(left, right);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    /**
     *
     * The function simplify Expression if the left Expression is a Var.
     * The simplification:
     * 1. (x-(2x+3)->(-(x+3))
     * 2. (x+(3+2x))->(-(2x+3))
     * 3. (x-(x+3))->(-3)
     * 4. (x-(3+x))->(-3)
     * 5. (x-(2x-3))->(3-x)
     * 6. (x-(3-2x))->(3x-3)
     * 7. (x-(x-3))->3
     * 8. (x-(3-x)->(2x-3)
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
                // return (x-2x)->(-x)
                return new Neg(new Mult(right.getExpressions(true).evaluate() - 1, left)).simplify();
            }
        }
        // If the right Expression is Plus.
        if (isPlus(right)) {
            // if the right left inner  Expression is Mult.
            if (isMult(right.getExpressions(true))) {
                if (left.toString().equals(right.getExpressions(true).getExpressions(false).toString())) {
                    // return (x-(2x+3)->(-(x+3))
                    return new Neg(new Plus(
                            new Mult(right.getExpressions(true).getExpressions(false).evaluate() - 1,
                            left), right.getExpressions(false))).simplify();
                }
            }
            // if the right right inner  Expression is Mult.
            if (isMult(right.getExpressions(false))) {
                if (left.toString().equals(right.getExpressions(false).getExpressions(false).toString())) {
                    // return (x+(3+2x))->(-(2x+3))
                    return new Neg(new Plus(
                            new Mult(right.getExpressions(false).getExpressions(false).evaluate() - 1,
                            left), right.getExpressions(true))).simplify();
                }
            }
            // if the right left inner  Expression is variable.
            if (isVar(right.getExpressions(true))
                    && left.toString().equals(right.getExpressions(true).toString())) {
                // return (x-(x+3))->(-3)
                return new Neg(right.getExpressions(false)).simplify();
            }
            // if the right right inner  Expression is variable.
            if (isVar(right.getExpressions(false))
                    && left.toString().equals(right.getExpressions(false).toString())) {
                //return (x-(3+x))->(-3)
                return new Neg(right.getExpressions(false)).simplify();
            }
        }
        // If the right Expression is Minus.
        if (isMinus(right)) {
            // If the right left inner  Expression is Mult.
            if (isMult(right.getExpressions(true))) {
                if (left.toString().equals(right.getExpressions(true).getExpressions(false).toString())) {
                    // return (x-(2x-3))->(3-x)
                    return new Minus(right.getExpressions(false),
                            new Mult(right.getExpressions(true).getExpressions(true).evaluate()
                                    - 1, left)).simplify();
                }
            }
            // If the right right inner  Expression is Mult.
            if (isMult(right.getExpressions(false))) {
                if (left.toString().equals(right.getExpressions(false).getExpressions(false).toString())) {
                    // return (x-(3-2x))->(3x-3)
                    return new Minus(new Mult(right.getExpressions(true).getExpressions(false).evaluate()
                            + 1, left), right.getExpressions(true)).simplify();
                }
            }
            // if the right left inner  Expression is variable.
            if (isVar(right.getExpressions(true))
                    && left.toString().equals(right.getExpressions(true).toString())) {
                // return (x-(x-3))->3
                return right.getExpressions(false).simplify();
            }
            // if the right right inner  Expression is variable.
            if (isVar(right.getExpressions(false))
                    && left.toString().equals(right.getExpressions(false).toString())) {
                // return (x-(3-x)->(2x-3)
                return new Minus(new Mult(2, left), right.getExpressions(true)).simplify();
            }
        }
        // otherwise return null
        return null;
    }

    /**
     *
     * The function simplify Expression if the left Expression is a Mult.
     * The simplification:
     * 1. (2x-x)->x
     * 2. (4x-2x)->(2x)
     * 3. (5x-7x)-> (-2x)
     * 4. (4x -(2x+3))->(2x-3)
     * 5. (2x - (4x+3))->(-(2x+3))
     * 6. (4x-(3+2x))->(2x-3)
     * 7. (2x-(3+4x))-> (-(2x+3))
     * 8. (3x-(x+3))-> (2x-3)
     * 9. (3x-(3+x))->(2x-3)
     * 10. (4x -(2x -3))->(2x+3)
     * 11. (2x -(3-2x))-> (4x-3)
     * 12. (4x -(3-2x))->(6x-3)
     * 13. (3x- (3-x))->(4x+3)
     *
     * @param left the left expression class
     * @param right the right expression class
     * @return the simplify Expression or null if the expression is simplify.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    public Expression simplifyIfLeftIsMult(Expression left, Expression right) throws Exception {
        // if the right Expression is variable
        if (isVar(right)) {
            if (right.toString().equals(left.getExpressions(false).toString())) {
                // return (2x-x)->x
                return new Mult(left.getExpressions(true).evaluate() - 1, right).simplify();
            }
        }
        // if the right Expression is Mult
        if (isMult(right)) {
            // If the variables are egual.
            if (left.getExpressions(false).toString().equals(right.getExpressions(false).toString())) {
                // check if the the coefficient of the left Mult big than the right Mult
                if (left.getExpressions(true).evaluate()
                        > right.getExpressions(true).getExpressions(true).evaluate()) {
                    // return (4x-2x)->(2x)
                    return new Mult(new Num(left.getExpressions(true).evaluate()
                            - right.getExpressions(true).evaluate()), left.getExpressions(false));
                } else {
                    // return (5x-7x)-> (-2x)
                    return new Neg(new Mult(
                            new Num(right.getExpressions(true).evaluate()
                                    - left.getExpressions(true).evaluate()), left.getExpressions(false))).simplify();
                }

            }
        }
        // If the right Expression is Plus
        if (isPlus(right)) {
            // if the right left inner  Expression is Mult.
            if (isMult(right.getExpressions(true))) {
                // If the variables are egual.
                if (left.getExpressions(false).toString().equals(right.getExpressions(true)
                        .getExpressions(false).toString())) {
                    // check if the the coefficient of the left Mult big than the right left inner Mult
                    if (left.getExpressions(true).evaluate()
                            > right.getExpressions(true).getExpressions(true).evaluate()) {
                        // return (4x -(2x+3))->(2x-3)_
                        return new Minus(new Mult(new Num(left.getExpressions(true).evaluate()
                                - right.getExpressions(true).getExpressions(true).evaluate()),
                                left.getExpressions(false)), right.getExpressions(false)).simplify();
                    } else {
                        // return (2x - (4x+3))->(-(2x+3))
                        return new Neg(new Plus(new Mult(
                                right.getExpressions(true).getExpressions(true).evaluate()
                                        - left.getExpressions(true).evaluate(), left.getExpressions(false)),
                                right.getExpressions(false))).simplify();
                    }
                }
            }
            // if the right right inner  Expression is Mult.
            if (isMult(right.getExpressions(false))) {
                // If the variables are egual.
                if (left.getExpressions(false).toString()
                        .equals(right.getExpressions(false).getExpressions(false).toString())) {
                    // check if the the coefficient of the left Mult big than the right right inner Mult
                    if (left.getExpressions(true).evaluate()
                            > right.getExpressions(false).getExpressions(true).evaluate()) {
                        // return (4x-(3+2x))->(2x-3)
                        return new Minus(new Mult(new Num(left.getExpressions(true).evaluate()
                                - right.getExpressions(false).getExpressions(true).evaluate()),
                                left.getExpressions(false)), right.getExpressions(false)).simplify();
                    } else {
                        // return (2x-(3+4x))-> (-(2x+3))
                        return new Neg(new Plus(new Mult(
                                right.getExpressions(false).getExpressions(true).evaluate()
                                        - left.getExpressions(true).evaluate(), left.getExpressions(false)),
                                right.getExpressions(false))).simplify();
                    }
                }
            }
            // if the right left inner  Expression is Var.
            if (isVar(right.getExpressions(true))) {
                // If the variables are egual.
                if (left.getExpressions(false).toString().equals(right.getExpressions(true).toString())) {
                    // return (3x-(x+3))-> (2x-3)
                    return new Minus(new Mult(left.getExpressions(true).evaluate() - 1
                            , right.getExpressions(true)), right.getExpressions(false)).simplify();
                }
            }
            // if the right right inner  Expression is Var.
            if (isVar(right.getExpressions(false))) {
                // If the variables are egual.
                if (left.getExpressions(false).toString().equals(right.getExpressions(false).toString())) {
                    // return (3x-(3+x))->(2x-3)
                    return new Neg(new Plus(new Mult(left.getExpressions(true).evaluate() - 1
                            , right.getExpressions(false)), right.getExpressions(true))).simplify();
                }
            }
        }
        // If the right Expression is Minus
        if (isMinus(right)) {
            // if the right left inner  Expression is Mult.
            if (isMult(right.getExpressions(true))) {
                if (left.getExpressions(false).toString().equals(right.getExpressions(true)
                        .getExpressions(false).toString())) {
                    // check if the the coefficient of the left Mult big than the right right inner Mult
                    if (left.getExpressions(true).evaluate()
                            > right.getExpressions(true).getExpressions(true).evaluate()) {
                        // return (4x -(2x -3))->(2x+3)
                        return new Plus(new Mult(new Num(left.getExpressions(true).evaluate()
                                - right.getExpressions(true).getExpressions(true).evaluate()),
                                left.getExpressions(false)), right.getExpressions(false)).simplify();
                    } else {
                        // return (4x -(3-2x))->(6x-3)
                        return new Minus(right.getExpressions(false), new Mult(
                                right.getExpressions(true).getExpressions(true).evaluate()
                                - left.getExpressions(true).evaluate(),
                                left.getExpressions(false))).simplify();
                    }
                }
            }
            // if the right right inner  Expression is Mult.
            if (isMult(right.getExpressions(false))) {
                if (left.getExpressions(false).toString().equals(right.getExpressions(false)
                        .getExpressions(false).toString())) {
                    // return (2x -(3-2x))-> (4x-3)
                    return new Minus(new Mult(left.getExpressions(true).evaluate()
                            + right.getExpressions(false).getExpressions(true).evaluate()
                            , left.getExpressions(false)), right.getExpressions(true)).simplify();
                }
            }
            // if the right left inner  Expression is Var.
            if (isVar(right.getExpressions(true))) {
                if (left.getExpressions(false).toString().equals(right.getExpressions(true).toString())) {
                    // return (3x- (x-3))->(2x+3)
                    return new Plus(new Mult(left.getExpressions(true).evaluate() - 1
                            , right.getExpressions(true)), right.getExpressions(false)).simplify();
                }
            }
            // if the right right inner  Expression is Var.
            if (isVar(right.getExpressions(false))) {
                if (left.getExpressions(false).toString().equals(right.getExpressions(false).toString())) {
                    // return (3x- (3-x))->(4x+3)
                    return new Minus(new Mult(left.getExpressions(true).evaluate() + 1
                            , left.getExpressions(false)), right.getExpressions(true)).simplify();
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
     * 1. (1-(2+x))->(-(x+1))
     * 2. (3-(2+x))->(1+x)
     * 3. (1-(x+2))->(-(x+1))
     * 4. (3-(x+2))->(1+x)
     * 5. (1-(2-x))->(x-1)
     * 6. (3-(2-x))->(x+1)
     * 7. (1 - (x-1))->(2-x)
     *
     * @param left the left expression class
     * @param right the right expression class
     * @return the simplify Expression or null if the expression is simplify.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    public Expression simplifyIfLeftIsNum(Expression left, Expression right) throws Exception {
        // If the right Expression is Mult
        if (isMult(right)) {
            // The Expression is simplify
            return new Minus(left, right);
        }
        // If the right Expression is Mult
        if (isPlus(right)) {
            // if the right left inner  Expression is Num.
            if (isNum(right.getExpressions(true))) {
                // check if the left number smaller than the right left inner number
                if (left.evaluate() < right.getExpressions(true).evaluate()) {
                    // return (1-(2+x))->(-(x+1))
                    return new Neg(new Plus(right.getExpressions(true).evaluate() - left.evaluate()
                            , right.getExpressions(false))).simplify();
                // return (3-(2+x))->(1+x)
                } else {
                    return new Minus(left.evaluate() - right.getExpressions(true).evaluate()
                            , right.getExpressions(false)).simplify();
                }
            }
            // if the right right inner  Expression is Num.
            if (isNum(right.getExpressions(false))) {
                // check if the left number smaller than the right right inner number
                if (left.evaluate() < right.getExpressions(false).evaluate()) {
                    // return (1-(x+2))->(-(x+1))
                    return new Neg(new Plus(right.getExpressions(false).evaluate() - left.evaluate()
                            , right.getExpressions(true))).simplify();
                // return (3-(x+2))->(1+x)
                } else {
                    return new Minus(left.evaluate() - right.getExpressions(false).evaluate()
                            , right.getExpressions(true)).simplify();
                }
            }
        }
        // If the right Expression is Minus
        if (isMinus(right)) {
            // if the right left inner  Expression is Num.
            if (isNum(right.getExpressions(true))) {
                // check if the left number smaller than the right left inner number
                if (left.evaluate() < right.getExpressions(false).evaluate()) {
                    // return (1-(2-x))->(x-1)
                    return new Minus(right.getExpressions(false),
                            right.getExpressions(true).evaluate() - left.evaluate());
                // return (3-(2-x))->(x+1)
                } else {
                    return new Plus(left.evaluate() - right.getExpressions(true).evaluate(),
                            right.getExpressions(false)).simplify();
                }
            }
            // if the right right inner  Expression is Num.
            if (isNum(right.getExpressions(false))) {
                // return (1 - (x-1))->(2-x)
                return new Minus(left.evaluate() + right.getExpressions(false).evaluate(),
                        right.getExpressions(true)).simplify();
            }
        }
        // otherwise return null
        return null;
    }
}
