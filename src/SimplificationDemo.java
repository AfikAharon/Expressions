/**
 * a SimplificationDemo class.
 *
 * Test class for the bonus part
 *
 * @author Afik Aharon.
 */
public class SimplificationDemo {
    /**
     * The mai function create a Expression call the function moreSimplify,
     * and print the expression.
     * @param args a empty array
     */
    public static void main(String [] args)  {
        try {
            // create a Expression
            Expression e = new Minus(new Mult(2, "x"), new Mult(5, "x"));
            // Print the Expression ((2.0 * x) - (5.0 * x))
            System.out.println(e.toString());
            // Print the Expression after simplify (-(3.0 * x))
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            // create a Expression
            e = new Plus(new Mult(2, "x"), new Plus("x", 3));
            // Print the Expression ((2.0 * x) + (x + 3.0))
            System.out.println(e.toString());
            // Print the Expression after simplify ((3.0 * x) + 3.0)
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            // create a Expression
            e = new Minus(new Mult(2, "x"), new Minus("x", 3));
            // Print the Expression ((2.0 * x) - (x - 3.0))
            System.out.println(e.toString());
            // Print the Expression after simplify  (x + 3.0)
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            // create a Expression
            e = new Mult(new Mult(2, "x"), new Mult(2, "x"));
            // Print the Expression ((2.0 * x) * (2.0 * x))
            System.out.println(e.toString());
            // Print the Expression after simplify (4.0 * (x^2.0))
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            e = new Mult(new Mult(2, "x"), new Plus("x", 3));
            // Print the Expression ((2.0 * x) * (x + 3.0))
            System.out.println(e.toString());
            // Print the Expression after simplify  ((2.0 * (x^2.0)) + (6.0 * x))
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            // create a Expression
            e = new Neg(new Neg(new Neg(new Neg("X"))));
            // Print the Expression (-(-(-(-x))))
            System.out.println(e.toString());
            // Print the Expression after simplify x
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            // create a Expression
            e = new Plus(new Mult(2, "x"), new Plus(2,
                         new Plus(1, new Plus(2, new Mult(4, "x")))));
            // Print the Expression ((2.0 * x) + (2.0 + (1.0 + (2.0 + (4.0 * x)))))
            System.out.println(e.toString());
            // Print the Expression after simplify ((6.0 * x) + 5.0)
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            // create a Expression
            e = new Minus(new Mult(2, "x"), new Minus(2,
                         new Plus(1, new Plus(2, new Mult(4, "x")))));
            // Print the Expression ((2.0 * x) - (2.0 - (1.0 + (2.0 + (4.0 * x)))))
            System.out.println(e.toString());
            // Print the Expression after simplify ((6.0 * x) + 1.0)
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            // create a Expression
            e = new Minus(new Mult(4, "x"), new Plus(3, new Mult(2, "x")));
            // Print the Expression ((4.0 * x) - (3.0 + (2.0 * x)))
            System.out.println(e.toString());
            // Print the Expression after simplify ((2.0 * x) - 3.0)
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            e = new Mult(new Mult(2, "x"), new Plus(5, "x"));
            // Print the Expression ((2.0 * x) * (5.0 + x))
            System.out.println(e.toString());
            // Print the Expression after simplify ((2.0 * (x^2.0)) + (10.0 * x))
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            e = new Mult(new Mult(2, "x"), new Minus(5, "x"));
            // Print the Expression (2.0 * x) * (5.0 - x))
            System.out.println(e.toString());
            // Print the Expression after simplify ((10.0 * x) - (2.0 * (x^2.0)))
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            e = new Pow(new Pow(new Pow("x", "y"), "x"), "z");
            // Print the Expression (2.0 * x) * (5.0 - x))
            System.out.println(e.toString());
            // Print the Expression after simplify ((10.0 * x) - (2.0 * (x^2.0)))
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            e = new Pow(new Plus("x", "y"), new Neg("z"));
            // Print the Expression ((x^y) / (x^z))
            System.out.println(e.toString());
            // Print the Expression after simplify (1.0 / ((x + y)^z))
            System.out.println(e.moreSimplify().toString());
            System.out.println();
            e = new Div(new Pow("x", "y"), new Pow("x", "z"));
            // Print the Expression ((x+y)^(-z))
            System.out.println(e.toString());
            // Print the Expression after simplify (x^(y - z))
            System.out.println(e.moreSimplify().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
