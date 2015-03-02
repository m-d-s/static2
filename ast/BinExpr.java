package ast;
import compiler.Failure;

/** An abstract base class for binary expressions (i.e.,
 *  expressions that have a left and a right operand).
 */
public abstract class BinExpr extends Expr {

    /** The left subexpression.
     */
    protected Expr left;

    /** The right subexpression.
     */
    protected Expr right;

    /** Default constructor.
     */
    public BinExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    /** This attribute will be filled in during static analysis to record
     *  the type of the arguments for this operator (both left and right
     *  arguments are required to have the same type).  This information
     *  will be useful for situations in code generation where we need
     *  to distinguish between using an integer or a floating point
     *  version of an operation.  The type attribute is set to null when
     *  a BinExpr node is first created to indicate that the type has yet
     *  to be determined.
     */
    protected Type type = null;

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, (type==null) ? label() : (label() + " " + type));
        left.indent(out, n+1);
        right.indent(out, n+1);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    abstract String label();
}
