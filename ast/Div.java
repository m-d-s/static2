package ast;
import compiler.Failure;

/** Abstract syntax for the division operator, /.
 */
public class Div extends ArithBinExpr {

    /** Default constructor.
     */
    public Div(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "Div"; }
}
