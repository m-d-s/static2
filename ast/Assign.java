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

   
    public Type typeOf(Context ctxt, TypeEnv locals)
        throws Failure {
        TypeEnv envOf = null;
        try {
            envOf = ctxt.findVar( lhs, locals );
            type = rhs.typeOf(ctxt, locals);
        }catch ( Failure f ) {
            ctxt.report( f );
        }
        // non numeric type mismatch
        if( ( Type.INT == envOf.getType() || Type.DOUBLE == envOf.getType()  ) && !type.isNumeric() ||
            Type.BOOLEAN == envOf.getType() && type.isNumeric() ) {
            ctxt.report( new Failure("AssignTypes") ); 
        }
        
        //both types numeric 
        if( ( Type.INT == envOf.getType() || Type.DOUBLE == envOf.getType() ) && type.isNumeric() ) {
            if( envOf.getType() == type) {
                return type;
            } 
            //type cast numeric mismatch
            else if ( Type.INT == envOf.getType() ) {
                rhs = new DoubleToInt(rhs);                
            } 
            else {
                rhs = new IntToDouble(rhs);
            }
        }
        return type = rhs.typeOf(ctxt, locals);
    }
   
}







