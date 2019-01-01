import java.util.Map;
import java.util.TreeMap;

/**
 * a ExpressionTest class
 *
 * test for the expression functions.
 *
 * @author Afik Aharon.
 */
public class ExpressionsTest {
    /**
     *
     * test for the expression functions.
     *
     * @param args a empty array.
     */
    public static void main(String [] args)  {
        try {
            Expression e = new Plus(new Mult(2, "x"),
                    new Plus(new Sin(new Mult(4, "y"))
                            , new Pow("e", "x")));
            System.out.println(e);
            Map<String, Double> assignment = new TreeMap<String, Double>();
            assignment.put("x", 2.0);
            assignment.put("y", 0.25);
            assignment.put("e", 2.71);
            //Prints the value of the expression with the assignment map.
            System.out.println(e.evaluate(assignment));
            //Print the differentiated expression according to X
            System.out.println(e.differentiate("x"));
            //Print the value of the differentiated expression
            System.out.println(e.differentiate("x").evaluate(assignment));
            // Print the simplified differentiated expression.
            System.out.println(e.differentiate("x").simplify());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
