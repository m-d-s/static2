package ast;
import compiler.Failure;

/** Abstract syntax for empty statements.
 */
public class Empty extends Stmt {

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Empty");
    }

    //not much to do, just return locals
    public TypeEnv check(Context ctxt, TypeEnv locals) {
        return locals;
    }

}
