/**
 * a Mult Class.
 * <p>
 * The class calculate with the operator Minus, inherent
 * from the BinaryExpression Class and implements to
 * Expression class.
 *
 * @author Afik Aharon.
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * Constructor for the Mult class :
     * take number and string and make them to
     * Expressions.
     *
     * @param var a given string of Variable.
     * @param num a given number.
     */
    public Mult(String var, double num) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(new Var(var));
    }

    /**
     * Constructor for the Mult class :
     * take number and string and make them to
     * Expressions.
     *
     * @param num a given number.
     * @param var a given string of Variable.
     */
    public Mult(double num, String var) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(new Var(var));
    }

    /**
     * Constructor for the Mult class :
     * take two Expression and adds them to the methods.
     *
     * @param leftE  the left Expression.
     * @param rightE the right Expression.
     */
    public Mult(Expression leftE, Expression rightE) {
        super.setLeftExpression(leftE);
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Mult class :
     * take Expression and String and adds them to the methods.
     *
     * @param leftE the left Expression.
     * @param var   a given string of Variable.
     */
    public Mult(Expression leftE, String var) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Var(var));
    }

    /**
     * Constructor for the Mult class :
     * take Expression and String and adds them to the methods.
     *
     * @param varLeft a given string of Variable.
     * @param rightE  the right Expression
     */
    public Mult(String varLeft, Expression rightE) {
        super.setLeftExpression(new Var(varLeft));
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Mult class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param leftE    the left Expression.
     * @param numRight a given right number.
     */
    public Mult(Expression leftE, double numRight) {
        super.setLeftExpression(leftE);
        super.setRightExpression(new Num(numRight));
    }

    /**
     * Constructor for the Mult class :
     * take Expression and a given number and adds them to the methods.
     *
     * @param num    a given left number
     * @param rightE the right Expression
     */
    public Mult(double num, Expression rightE) {
        super.setLeftExpression(new Num(num));
        super.setRightExpression(rightE);
    }

    /**
     * Constructor for the Mult class :
     * take two given strings and adds them to the methods.
     *
     * @param leftVar  a given left string
     * @param rightVar a given left string
     */
    public Mult(String leftVar, String rightVar) {
        super.setLeftExpression(new Var(leftVar));
        super.setRightExpression(new Var(rightVar));
    }

    /**
     * Constructor for the Mult class :
     * take two given numbers and adds them to the methods.
     *
     * @param numLeft  a given left number.
     * @param numRight a given right number.
     */
    public Mult(double numLeft, double numRight) {
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
        return leftEx * rightEx;
    }

    /**
     * The function return the string operator Class.
     *
     * @return the string operator.
     */
    @Override
    public String operator() {
        return " * ";
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
        return new Mult(super.getLeftExpression().assign(var, expression),
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
        return new Plus(new Mult(super.getLeftExpression().differentiate(var), super.getRightExpression()),
                new Mult(super.getLeftExpression(), super.getRightExpression().differentiate(var)));
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
            return new Num(new Mult(super.getLeftExpression(), super.getRightExpression()).evaluate());
            // If an Exception throws simplify the Expression with Variables.
        } catch (Exception e) {
            Expression leftEx = super.getLeftExpression().simplify();
            Expression rightEx = super.getRightExpression().simplify();
            // If the Expression is of the form of (0*x) return 0
            if (rightEx.toString().equals("0.0") || leftEx.toString().equals("0.0")) {
                return new Num(0);
            }
            // If the Expression is of the form of (1.0*x) return x
            if (rightEx.toString().equals("1.0")) {
                return leftEx;
            }
            // If the Expression is of the form of (x*1.0) return x
            if (leftEx.toString().equals("1.0")) {
                return rightEx;
            }
            // otherwise return the new Expression after the simplify.
            return new Mult(leftEx, rightEx);
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
     * simplified version of the current expression, check if the left Expression
     * is a variable, Mult or Num after that call the functions simplifyIfLeftIsVar
     * simplifyIfLeftIsMult, simplifyIfLeftIsNum for more simplification.
     * The simplification:
     * 1. (x + y) -> ((x+y)^2)
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
            // If the left Expression equal to the right Expression return (x*x)->(x^2)
            if (left.toString().equals(right.toString()) || left.toString().equals(right.swapSides(right).toString())) {
                return new Pow(left, 2).simplify();
            }
            // otherwise return the same Expression
            return new Mult(left, right);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    /**
     * The function simplify Expression if the left Expression is a Var.
     * The simplification:
     * <p>
     * 1. (x*x)-> (x^2)
     * 2. (x*2x)->(2*(x^2))
     * 3. (x*(x+2))-> ((x^2)+2*x)
     * 4. (x*(2+x))-> ((x^2)+2*x)
     * 5. (x*(x-2))-> ((x^2)-2*x)
     * 6. (x*(2-x))-> (2*x-(x^2))
     *
     * @param left  the left expression class
     * @param right the right expression class
     * @return the simplify Expression or null if the expression is simplify.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    public Expression simplifyIfLeftIsVar(Expression left, Expression right) throws Exception {
        // if the right Expression is variable.
        if (isVar(right) && left.toString().equals(right.toString())) {
            // return (x*x)-> (x^2)
            return new Pow(left, 2).simplify();
        }
        // If the right Expression is Mult
        if (isMult(right) && left.toString().equals(right.getExpressions(false).toString())) {
            // return (x*2x)->(2*(x^2))
            return new Mult(right.getExpressions(true), new Pow(left, 2)).simplify();
        }
        // If the right Expression is Plus.
        if (isPlus(right)) {
            // if the right left inner  Expression is variable.
            if (isVar(right.getExpressions(true))
                    && left.toString().equals(right.getExpressions(true).toString())) {
                if (isNum(right.getExpressions(false))) {
                    // return (x*(x+2))-> ((x^2)+2*x)
                    return new Plus(new Pow(left, 2),
                            new Mult(right.getExpressions(false), left)).simplify();
                }
            }
            // if the right right inner  Expression is variable.
            if (isVar(right.getExpressions(false))
                    && left.toString().equals(right.getExpressions(false).toString())) {
                if (isNum(right.getExpressions(true))) {
                    // return (x*(2+x))-> ((x^2)+2*x)
                    return new Plus(new Pow(left, 2),
                            new Mult(right.getExpressions(true).evaluate(), left)).simplify();
                }
            }
        }
        // If the right Expression is Minus.
        if (isMinus(right)) {
            // if the right left inner  Expression is variable.
            if (isVar(right.getExpressions(true))
                    && left.toString().equals(right.getExpressions(true).toString())) {
                if (isNum(right.getExpressions(false))) {
                    // return (x*(x-2))-> ((x^2)-2*x)
                    return new Minus(new Pow(left, 2),
                            new Mult(right.getExpressions(false), left)).simplify();
                }
            }
            // if the right right inner  Expression is variable.
            if (isVar(right.getExpressions(false))
                    && left.toString().equals(right.getExpressions(false).toString())) {
                if (isNum(right.getExpressions(true))) {
                    // return (x*(2-x))-> (2*x-(x^2))
                    return new Minus(new Mult(right.getExpressions(true).evaluate(),
                            left), new Pow(left, 2)).simplify();
                }
            }
        }
        // otherwise return the null
        return null;
    }

    /**
     * The function simplify Expression if the left Expression is a Mult.
     * The simplification:
     * <p>
     * 1. ((2*x)*x)->(2*(x^2))
     * 2. ((2*x)*(2x))->(4*(x^2))
     * 3. (2x*(x+2))-> ((2*(x^2)+((4*x))
     * 4. (2x*(2x+2))-> ((4*(x^2)+((4*x))
     * 5. (2x*(2+2x))-> ((2*(x^2)+((4*x))
     * 6. (2x*(x-2))-> ((2*(x^2)-((4*x))
     * 7. (2x*(2-x))-> ((4*x)-(2*(x^2)))
     * 8. (2x*(2x-2))-> ((4*(x^2))-((4*x))
     * 9. (2x*(2-2x))-> (((4*x)-(4*(x^2)))
     *
     * @param left  the left expression class
     * @param right the right expression class
     * @return the simplify Expression or null if the expression is simplify.
     * @throws Exception an Exception throws when the function do a Math Error.
     */
    public Expression simplifyIfLeftIsMult(Expression left, Expression right) throws Exception {
        // if the right Expression is variable
        if (isVar(right) && left.getExpressions(false).toString().equals(right.toString())) {
            // return ((2*x)*x)->(2*(x^2))
            return new Mult(left.getExpressions(true),
                    new Pow(left.getExpressions(false), 2)).simplify();
        }
        // if the right Expression is Mult
        if (isMult(right)
                && left.getExpressions(false).toString().equals(right.getExpressions(false).toString())) {
            // return ((2*x)*(2x))->(4*(x^2))
            return new Mult(right.getExpressions(true).evaluate()
                    * left.getExpressions(true).evaluate(), new Pow(left.getExpressions(false),
                    2)).simplify();
        }
        // If the right Expression is Plus
        if (isPlus(right)) {
            // if the right left inner Expression is variable
            if (isVar(right.getExpressions(true))
                    && left.getExpressions(false).toString().equals(right.getExpressions(true).toString())) {
                if (isNum(right.getExpressions(false))) {
                    // return (2x*(x+2))-> ((2*(x^2)+((4*x))
                    return new Plus(new Mult(left.getExpressions(true),
                            new Pow(left.getExpressions(false), 2)),
                            new Mult(right.getExpressions(false).evaluate() * left.getExpressions(true).evaluate(),
                                    left.getExpressions(false))).simplify();
                }
            }
            // if the right right inner Expression is variable
            if (isVar(right.getExpressions(false))
                    && left.getExpressions(false).toString().equals(right.getExpressions(false).toString())) {
                if (isNum(right.getExpressions(true))) {
                    // return (2x*(2+x))-> ((2*(x^2)+((4*x))
                    return new Plus(new Mult(left.getExpressions(true),
                            new Pow(left.getExpressions(false), 2)),
                            new Mult(right.getExpressions(true).evaluate() * left.getExpressions(true).evaluate(),
                                    left.getExpressions(false))).simplify();
                }
            }
            // if the right left inner Expression is Mult
            if (isMult(right.getExpressions(true))
                    && left.getExpressions(false).toString()
                    .equals(right.getExpressions(true).getExpressions(false).toString())) {
                if (isNum(right.getExpressions(false))) {
                    // return (2x*(2x+2))-> ((4*(x^2)+((4*x))
                    return new Plus(new Mult(left.getExpressions(true).evaluate()
                            * right.getExpressions(true).getExpressions(true).evaluate(),
                            new Pow(left, 2)), new Mult(right.getExpressions(false), left)).simplify();
                }
            }
            // if the right right inner Expression is Mult
            if (isMult(right.getExpressions(false))
                    && left.getExpressions(false).toString().
                    equals(right.getExpressions(false).getExpressions(false).toString())) {
                if (isNum(right.getExpressions(true))) {
                    // return (2x*(2+2x))-> ((2*(x^2)+((4*x))
                    return new Plus(new Mult(left.getExpressions(true).evaluate()
                            * right.getExpressions(false).getExpressions(true).evaluate(),
                            new Pow(left, 2)),
                            new Mult(right.getExpressions(true).evaluate(), left)).simplify();
                }
            }
        }
        // If the right Expression is Minus
        if (isMinus(right)) {
            // if the right left inner Expression is variable
            if (isVar(right.getExpressions(true))
                    && left.getExpressions(false).toString().equals(right.getExpressions(true).toString())) {
                if (isNum(right.getExpressions(false))) {
                    // return (2x*(x-2))-> ((2*(x^2)-((4*x))
                    return new Minus(new Mult(left.getExpressions(true),
                            new Pow(left.getExpressions(false), 2)),
                            new Mult(right.getExpressions(false).evaluate()
                                    * left.getExpressions(true).evaluate(), left.getExpressions(false))).simplify();
                }
            }
            // if the right right inner Expression is variable
            if (isVar(right.getExpressions(false))
                    && left.getExpressions(false).toString().equals(right.getExpressions(false).toString())) {
                if (isNum(right.getExpressions(true))) {
                    // return (2x*(2-x))-> ((4*x)-(2*(x^2)))
                    return new Minus(new Mult(right.getExpressions(true).evaluate()
                            * left.getExpressions(true).evaluate(),
                            left.getExpressions(false)), new Mult(left.getExpressions(true),
                            new Pow(left.getExpressions(false), 2))).simplify();
                }
            }
            // if the right left inner Expression is Mult
            if (isMult(right.getExpressions(true))
                    && left.getExpressions(false).toString().equals(right.getExpressions(true).toString())) {
                if (isNum(right.getExpressions(false))) {
                    // return (2x*(2x-2))-> ((4*(x^2))-((4*x))
                    return new Minus(new Mult(left.getExpressions(true).evaluate()
                            * right.getExpressions(true).getExpressions(true).evaluate(),
                            new Pow(left, 2)), new Mult(right.getExpressions(false), left)).simplify();
                }
            }
            // if the right left inner Expression is Mult
            if (isMult(right.getExpressions(false))
                    && left.getExpressions(false).toString()
                    .equals(right.getExpressions(false).getExpressions(false).toString())) {
                if (isNum(right.getExpressions(true))) {
                    // return (2x*(2-2x))-> (((4*x)-(4*(x^2)))
                    return new Minus(new Mult(right.getExpressions(true).evaluate(), left),
                            new Mult(left.getExpressions(true).evaluate()
                                    * right.getExpressions(false).getExpressions(true).evaluate(),
                                    new Pow(left, 2))).simplify();
                }
            }
        }
        // otherwise return the null
        return null;
    }
}
