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
            //if either type is BOOLEAN, report an error
            if( !leftType.isNumeric() || !rightType.isNumeric() ){
                ctxt.report(new Failure( "ArithBinArgsNumeric" ));
            }

        } catch ( Failure f ) {
            ctxt.report(f);
        }
        // Check to see if there is a numeric type mismatch
        if (leftType.equals(Type.INT) && rightType.equals(Type.DOUBLE)) {
            left =  new IntToDouble(left);
        }
        else if(leftType.equals(Type.DOUBLE) && rightType.equals(Type.INT)) {
            right = new IntToDouble(right);
        }
        //TODO: are you sure?
        // Arithmetic binary operators produce results of type INTEGER
        return type=Type.INT;
    }

}
