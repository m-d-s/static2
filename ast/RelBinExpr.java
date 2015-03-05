package ast;
import compiler.Failure;

/** An abstract base class for relational binary expressions.
 */
public abstract class RelBinExpr extends BinExpr {

    /** Default constructor.
     */
    public RelBinExpr(Expr left, Expr right) {
        super(left, right);
    }

    public Type typeOf(Context ctxt, TypeEnv locals)
            throws Failure {

        Type leftType = null, rightType = null;    
        // Find the type of the left operand
        leftType = left.typeOf(ctxt, locals);
        // Find the type of the right operand
        rightType = right.typeOf(ctxt, locals);

        if( leftType.isNumeric() && !rightType.isNumeric() ||
            !leftType.isNumeric() && rightType.isNumeric() ) {
            ctxt.report( new Failure( "RelBinArgs" ) );
            }
        
        // Check to see if there is a numeric type mismatch
        if (Type.INT == leftType && Type.DOUBLE == rightType) {
            left =  new IntToDouble(right);
        }
        else if(Type.DOUBLE == leftType && Type.INT == rightType) {
            right = new IntToDouble(right);
        }

        if( leftType == Type.INT ) {
            type = Type.INT;
        }
        else if ( leftType == Type.DOUBLE ) {
            type = Type.DOUBLE;
        }
        else {
            type = Type.BOOLEAN;
        }

       // Relationial binary operators produce results of type boolean
        return Type.BOOLEAN;
    }
}
