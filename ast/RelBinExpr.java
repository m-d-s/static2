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
        // Find the type of the left operand:
        Type leftType = left.typeOf(ctxt, locals);

        // Find the type of the right operand:
        Type rightType = right.typeOf(ctxt, locals);

        // Check that the right operand produces a value of type boolean:
        if (leftType.equals(Type.INT) && rightType.equals(Type.DOUBLE)) {
            left =  new IntToDouble(left);
        }
        else if(leftType.equals(Type.DOUBLE) && rightType.equals(Type.INT)) {
            right = new IntToDouble(right);
        }

        // Logical operators produce results of type boolean:
        return type=Type.BOOLEAN;
    }
}
