package ast;
import compiler.Failure;

/** Abstract syntax for the subtraction operator, -.
 */
public class Sub extends ArithBinExpr {

    /** Default constructor.
     */
    public Sub(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "Sub"; }
}
