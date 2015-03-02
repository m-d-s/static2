package ast;
import compiler.Failure;

/** Abstract syntax for a variable introduction that
 *  specifies an initial value (via an expression)
 *  for a variable that is brought in to scope via a
 *  VarDecl.
 */
public class InitVarIntro extends VarIntro {

    /** An expression whose that will be evaluated
     *  to provide the initial value for this variable.
     */
    private Expr exp;

    /** Default constructor.
     */
    public InitVarIntro(String name, Expr exp) {
        super(name);
        this.exp = exp;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "InitVarIntro");
        out.indent(n+1, "\"" + name + "\"");
        exp.indent(out, n+1);
    }

    /** Extend the global environment in the given context with an entry
     *  for the variable that is introduced here, using the given type.
     */
    void extendGlobalEnv(Context ctxt, Type type)
      throws Failure {
        if (TypeEnv.find(name, ctxt.globals)!=null) {
            ctxt.report(new Failure("GlobalsUnique"));
        }
        Type actual = exp.typeOf(ctxt, null);
        if (type.equals(Type.INT) && actual.equals(Type.DOUBLE)) {
            exp = new DoubleToInt(exp);
        } else if (type.equals(Type.DOUBLE) && actual.equals(Type.INT)) {
            exp = new IntToDouble(exp);
        } else if (!type.equals(actual)) {
            ctxt.report(new Failure("InitVarEntryType"));
        }
        ctxt.globals = new TypeEnv(name, type, ctxt.globals);
    }
}
