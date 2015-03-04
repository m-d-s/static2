package ast;
import compiler.Failure;

/** Abstract syntax for floating point literals.
 */
public class FloatLit extends Expr {

    /** The lexeme for this literal.
     */
    private String lexeme;

    /** Default constructor.
     */
    public FloatLit(String lexeme) {
        this.lexeme = lexeme;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "FloatLit(" + lexeme + ")");
    }

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        return Type.DOUBLE;
    }

}
