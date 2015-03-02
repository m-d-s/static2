package ast;
import compiler.Failure;

/** Abstract syntax for the <= comparison operator.
 */
public class Lte extends RelBinExpr {

    /** Default constructor.
     */
    public Lte(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "Lte"; }
}
