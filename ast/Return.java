package ast;
import compiler.Failure;

/** Abstract syntax for return statements.
 */
public class Return extends Stmt {

    /** The value that should be returned (or else null).
     */
    private Expr exp;
 
    /** Default constructor.
     */
    public Return(Expr exp) {
        this.exp = exp;
    }

    private Type type = null;

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Return");
        if (exp==null) {
            out.indent(n, "[no return value]");
        } else {
            exp.indent(out, n+1);
        }
    }

    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        type = exp.typeOf(ctxt, locals);
        return locals;
    }

}
