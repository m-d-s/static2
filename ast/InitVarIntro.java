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
        //check for redeclared global variables
        if (TypeEnv.find(name, ctxt.globals)!=null) {
            ctxt.report(new Failure("GlobalsUnique"));
        }
        //check for global variables being initialized with function calls
        if( exp instanceof Call ) {
            ctxt.report( new Failure("GlobalsNoCalls") );
        }
        //retrieve current expression type
        Type actual = exp.typeOf(ctxt, null);
        //check for type inconsistancies  
        this.typeCheck(ctxt, type, actual);
        //add declaration to the global scope
        ctxt.globals = new TypeEnv(name, type, ctxt.globals);
    }

    /**Extend the given local environment with an entr for the variable 
     * that is introduced here using the given type
     */
    TypeEnv extendLocalEnv( Context ctxt, Type type, TypeEnv locals ) {
        Type actual = null;
        try{
            actual = this.exp.typeOf(ctxt, locals);
        }catch( Failure f ) {
            ctxt.report(f);
        }
        this.typeCheck(ctxt, type, actual);
        return locals = new TypeEnv(this.name, type, locals);
    }
   
    /**Check for type inconsistancies and cast numeric mismatches accordingly
     */
    void typeCheck(Context ctxt, Type type, Type actual) {
        //cast numeric types to match
        if ( Type.INT == type && Type.DOUBLE == actual ) {
            this.exp = new DoubleToInt(this.exp);
        } 
        else if ( Type.DOUBLE == type && Type.INT == actual ) {
            this.exp = new IntToDouble(this.exp);
        }
        //check for type mismatch 
        else if ( actual != type ) {
            ctxt.report(new Failure("InitVarEntryType"));
        }
    } 
}
