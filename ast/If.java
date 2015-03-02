package ast;
import compiler.Failure;

/** Abstract syntax for if-then-else statements.
 */
public class If extends Stmt {

    /** The test expression.
     */
    private Expr test;

    /** The true branch.
     */
    private Stmt ifTrue;

    /** The false branch.
     */
    private Stmt ifFalse;

    /** Default constructor.
     */
    public If(Expr test, Stmt ifTrue, Stmt ifFalse) {
        this.test = test;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    /** A special version of the constructor that can be used
     *  when there is no false branch (i.e., no "else" clause),
     *  in which case the null value is used.
     */
    public If(Expr test, Stmt ifTrue) {
        this(test, ifTrue, null);
      }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "If");
        test.indent(out, n+1);
        ifTrue.indent(out, n+1);
        if (ifFalse==null) {
            out.indent(n, "[no else branch]");
        } else {
            ifFalse.indent(out, n+1);
        }
    }

    public TypeEnv check(Context ctxt, TypeEnv locals) 
        throws Failure {
        TypeEnv tTE = locals, fTE = locals;
        try {
            if (!test.typeOf(ctxt, locals).equals(Type.BOOLEAN)) {
                ctxt.report( new Failure( "IfBoolean" ));
            }
        }catch (Failure f) {
            ctxt.report(f);
        }
        tTE = ifTrue.check(ctxt, tTE);
        fTE = ifFalse.check(ctxt, fTE);
        return locals;
    }
}
