package ast;
import compiler.Failure;

/** Abstract syntax for a variable introduction (i.e., the part of
 *  a variable declaration that introduces the name and an optional
 *  initialization expression for a new variable.
 */
public class VarIntro {

    /** The name of the variable that is being introduced.
     */
    protected String name;

    /** Default constructor.
     */
    public VarIntro(String name) {
        this.name = name;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "VarIntro");
        out.indent(n+1, "\"" + name + "\"");
    }

    /** Extend the global environment in the given context with an entry
     *  for the variable that is introduced here, using the given type.
     */
    void extendGlobalEnv(Context ctxt, Type type)
      throws Failure {
        ctxt.report(new Failure("GlobalsInitialized"));
    }
}
