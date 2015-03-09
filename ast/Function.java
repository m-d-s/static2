package ast;
import compiler.Failure;

/** Abstract syntax for function definitions.
 */
public class Function extends Defn {

    /** The return type of this function (or null for
     *  a procedure/void function).
     */
    private Type retType;

    /** The name of this function.
     */
    private String name;

    /** The formal parameters for this function.
     */
    private Formal[] formals;

    /** The body of this function.
     */
    private Stmt body;

    /** Default constructor.
     */
    public Function(Type retType, String name, Formal[] formals, Stmt body) {
        this.retType = retType;
        this.name = name;
        this.formals = formals;
        this.body = body;
    }

    /** Print an indented description of this definition.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Function");
        out.indent(n+1, (retType==null) ? "void" : retType.toString());
        out.indent(n+1, "\"" + name + "\"");
        for (int i=0; i<formals.length; i++) {
            formals[i].indent(out, n+1);
        }
        body.indent(out, n+1);
    }

    /** Extend the environments in the given context with entries from
     *  this definition.
     */
    void extendGlobalEnv(Context ctxt)
      throws Failure {
        // Check that there is no previously defined function with
        // the same name:
        if (FunctionEnv.find(name, ctxt.functions)!=null) {
            ctxt.report(new Failure("FunctionNamesUnique"));
        }
  
        // Extend the function environment with a new entry for this
        // definition:
        ctxt.functions = new FunctionEnv(name, this, ctxt.functions);
    }

    /** Check that this is a valid function definition.
     */
    void check(Context ctxt)
      throws Failure {
        // Check for duplicate names in the formal parameter list:
        if (Formal.containsRepeats(formals)) {
            ctxt.report(new Failure("FormalsUnique"));
        }
  
        // Build an environment for this function's local variables:
        TypeEnv locals = TypeEnv.empty;
        for (int i=0; i<formals.length; i++) {
            locals = formals[i].extend(locals);
        }
  
        // Type check the body of this function:
        ctxt.retType = this.retType;
        body.check(ctxt, locals);
    }

    Type compareParams( Expr[] args, Context ctxt, TypeEnv locals ) {
        int numArgs = args.length, numFormal = formals.length;
        Type argType = null, formalType = null;
        //if the array lengths dont match
        if( numArgs != numFormal ) {
            ctxt.report(new Failure("CallNumberOfArgs"));
        } 
        //loop through each array to compare types
        for( int i = 0; i < numArgs && i < numFormal; ++i ) {
            try{
                argType = args[i].typeOf(ctxt, locals);
                formalType = formals[i].getType();
                //cast numeric types to match
                if( Type.INT == argType && Type.DOUBLE == formalType ) {
                    args[i] = new IntToDouble(args[i]);
                }
                else if( Type.DOUBLE == argType && Type.INT == formalType ) {
                    args[i] = new DoubleToInt(args[i]);
                }
                else if( argType != formalType ) {
                    ctxt.report( new Failure("FormalTypeMismatch") );
                }
            }catch(Failure f) {
                ctxt.report(f);
            }
        }
        return retType;
    } 
    
    //compare the passed type with retType member
    boolean compareRetType(Type src) {
        return retType == src;
    }
    
    //find length of Formals array
    int getNumFormals() {
        return formals.length;
    }  
}






