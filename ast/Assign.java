package ast;
import compiler.Failure;

public class Assign extends StmtExpr {

    /** The variable that we are assigning to.
     */
    private String lhs;

    /** The expression whose value will be saved.
     */
    private Expr rhs;

    /** Default constructor.
     */
    public Assign(String lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /** This attribute should be filled in during static analysis to
     *  record the type of the right hand side of this assignment.
     *  This information will be useful for the purposes of code
     *  generation.  The type attribute is set to null when an Assign
     *  node is first created to indicate that the type has yet to be
     *  determined.
     */
    protected Type type = null;

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, (type==null) ? "Assign" : ("Assign " + type));
        out.indent(n+1, "\"" + lhs + "\"");
        rhs.indent(out, n+1);
    }

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */  
    public Type typeOf(Context ctxt, TypeEnv locals)
        throws Failure {
        TypeEnv envOf = null;
        Type defType = null;
        try {
            this.type = this.rhs.typeOf(ctxt, locals);
        }
        catch ( Failure f ) {
            ctxt.report( f );
        }
        //find the lhs variable definition
        envOf = ctxt.findVar( this.lhs, locals );
        defType = envOf.getType();
        // non numeric type mismatch
        if( defType.isNumeric() && Type.BOOLEAN == this.type || 
            Type.BOOLEAN == defType && this.type.isNumeric() ) {
            ctxt.report( new Failure("AssignTypes") ); 
        }
        
        //both types numeric 
        if( defType.isNumeric() && type.isNumeric() ) {
            if( defType == this.type) {
                return this.type;
            } 
            //type cast numeric mismatch
            else if ( Type.INT == defType ) {
                this.rhs = new DoubleToInt(this.rhs);                
            } 
            else {
                this.rhs = new IntToDouble(this.rhs);
            }
        }
        return this.type = defType;
    }
   
}







