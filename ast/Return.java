package ast;
import compiler.Failure;

/** Abstract syntax for return statements.
 */
public class Return extends Stmt {

    /** The value that should be returned (or else null).
     */
    private Expr exp;
 
    /** Default constructor.
     */
    public Return(Expr exp) {
        this.exp = exp;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Return");
        if (exp==null) {
            out.indent(n, "[no return value]");
        } else {
            exp.indent(out, n+1);
        }
    }
    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        Type retType = ctxt.retType, type = null;   

        try{
            if( null != exp ) {
                type = exp.typeOf(ctxt, locals);
            }
        }catch( Failure f ) {
            ctxt.report( f );
        }
        
       //check for numeric type mismatch and cast accordingly
        if( Type.INT == type && Type.DOUBLE == retType ) {
            type = Type.DOUBLE;
            exp = new IntToDouble(exp);
        }
        else if( Type.DOUBLE == type && Type.INT == retType ) {
            type = Type.INT;
            exp = new DoubleToInt(exp);
        }
        
        //check if non void return is expected
        if( null == exp && null != retType ) {
            ctxt.report( new Failure("ReturnValueRequired") );
            return locals;
        }
        //check if void return type is expected
        else if( null != exp && null == retType ) {
            if( exp instanceof Call ) {
                ctxt.report( new Failure("CallReturnType") );
            }
            ctxt.report( new Failure("ReturnVoidRequired") );
            return locals;
        }
        //check for remaining return type mismatch
        else if( type != retType) {
            ctxt.report( new Failure( "ReturnType" ) );
        }

        return locals;
    }
}
