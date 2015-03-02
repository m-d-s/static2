package ast;
import compiler.Failure;

/** Abstract syntax for while statements.
 */
public class While extends Stmt {

    /** The test expression.
     */
    private Expr test;

    /** The body of this loop.
     */
    private Stmt body;

    /** Default constructor.
     */
    public While(Expr test, Stmt body) {
        this.test = test;
        this.body = body;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "While");
        test.indent(out, n+1);
        body.indent(out, n+1);
    }

    /**
     * Type check this statement, using passed context and type
     * environment. Returns the passed type environment:wq
     */
    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        TypeEnv inner = locals;
        try {
            if (!test.typeOf(ctxt, locals).equals(Type.BOOLEAN))
                ctxt.report( new Failure( "WhileBoolean" ));
        }catch (Failure f) {
            ctxt.report(f);
        }
        inner = body.check(ctxt, inner);
        return locals;
    }

}
