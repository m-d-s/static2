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
    
     /** Calculate the type of this expression, using the given context
      *  and type environment.
      */
     public Type typeOf(Context ctxt, TypeEnv locals)
            throws Failure {

        Type leftType = null, rightType = null;    
       try {
            // Find the type of the left operand
            leftType = this.left.typeOf(ctxt, locals); 
            // Find the type of the right operand
            rightType = this.right.typeOf(ctxt, locals);
            //error checks
            this.checkForVoidReturn(leftType, rightType);
            this.checkForGlobalCall(ctxt);
            //if either type is BOOLEAN, report an error
            if( !leftType.isNumeric() || !rightType.isNumeric() ) {
                ctxt.report( new Failure( "ArithBinArgsNumeric" ) );
            }
        } catch ( Failure f ) {
            ctxt.report(f);
        }
        
         // Check for numeric type mismatch and cast accordingly
        if ( Type.INT == leftType && Type.DOUBLE == rightType ) {
            this.left =  new IntToDouble(this.left);
        }
        else if(Type.DOUBLE == leftType && Type.INT == rightType) {
            this.right = new IntToDouble(this.right);
        }
        
        if( Type.DOUBLE == leftType || Type.DOUBLE == rightType ) {
            return this.type=Type.DOUBLE;
        }
        return this.type = Type.INT;
    }

}
