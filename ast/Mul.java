package ast;
import compiler.Failure;

/** Abstract syntax for the multiplication operator, *.
 */
public class Mul extends ArithBinExpr {

    /** Default constructor.
     */
    public Mul(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "Mul"; }
}
