package ast;
import compiler.Failure;

/** Abstract syntax for a double to int cast operation.
 */
public class DoubleToInt extends CastExpr {

    /** Default constructor.
     */
    public DoubleToInt(Expr exp) {
        super(exp);
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "DoubleToInt");
        exp.indent(out, n+1);
    }

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        Type et = exp.typeOf(ctxt, locals);
        if (!et.equals(Type.DOUBLE)) {
            ctxt.report(new Failure("CastDouble"));
        }
        return Type.INT;
    }

}
