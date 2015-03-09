package ast;
import compiler.Failure;

/** An abstract base class for arithmetic binary expressions.
 */
public abstract class ArithBinExpr extends BinExpr {

    /** Default constructor.
     */
    public ArithBinExpr(Expr left, Expr right) {
        super(left, right);
    }

    public Type typeOf(Context ctxt, TypeEnv locals)
            throws Failure {

        Type leftType = null, rightType = null;    
       try {
            // Find the type of the left operand
            leftType = left.typeOf(ctxt, locals); 
            // Find the type of the right operand
            rightType = right.typeOf(ctxt, locals);
        } catch ( Failure f ) {
            ctxt.report(f);
        }
        
        //if either type is BOOLEAN, report an error
        if( Type.INT != leftType && Type.DOUBLE != leftType ||
            Type.INT != rightType && Type.DOUBLE != rightType ) {
            ctxt.report( new Failure( "ArithBinArgsNumeric" ) );
        }

        // Check to see if there is a numeric type mismatch
        if ( Type.INT == leftType && Type.DOUBLE == rightType ) {
            left =  new IntToDouble(left);
        }
        else if(Type.DOUBLE == leftType && Type.INT == rightType) {
            right = new IntToDouble(right);
        }
        //if either type is of type double, return type double
        if( Type.DOUBLE == leftType || Type.DOUBLE == rightType ) {
            return type=Type.DOUBLE;
        }
        return type=Type.INT;
    }

}
