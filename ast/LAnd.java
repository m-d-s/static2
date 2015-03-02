package ast;
import compiler.Failure;

/** Abstract syntax for the logical AND operator, &&.
 */
public class LAnd extends LogBinExpr {

    /** Default constructor.
     */
    public LAnd(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "LAnd"; }
}
