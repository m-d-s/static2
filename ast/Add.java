package ast;
import compiler.Failure;

/** Abstract syntax for the addition operator, +.
 */
public class Add extends ArithBinExpr {

    /** Default constructor.
     */
    public Add(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "Add"; }
}
