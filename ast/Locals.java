package ast;
import compiler.Failure;

/** Abstract syntax for local variable declarations.
 */
public class Locals extends InitStmt {

    /** The type of the declared variables.
     */
    private Type type;

    /** The names of the declared variables.
     */
    private VarIntro[] vars;

    /** Default constructor.
     */
    public Locals(Type type, VarIntro[] vars) {
        this.type = type;
        this.vars = vars;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Locals");
        out.indent(n+1, type.toString());
        for (int i=0; i<vars.length; i++) {
           vars[i].indent(out, n+1);
        }
    }

//    public TypeEnv check(Context ctxt, TypeEnv locals)
//        throws Failure {
//
//    }

}
