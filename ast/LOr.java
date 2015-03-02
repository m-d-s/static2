package ast;
import compiler.Failure;

/** Abstract syntax for the logical OR operator, ||.
 */
public class LOr extends LogBinExpr {

    /** Default constructor.
     */
    public LOr(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "LOr"; }
}
