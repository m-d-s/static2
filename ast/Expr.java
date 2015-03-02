package ast;
import compiler.Failure;

/** An abstract syntax base class for expressions.
 */
public abstract class Expr {

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public abstract void indent(IndentOutput out, int n);

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        throw new Failure("typeOf has not been implemented for this case yet");
    }
}
