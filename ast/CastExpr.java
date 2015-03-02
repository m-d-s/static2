package ast;
import compiler.Failure;

/** An abstract base class for cast expressions (i.e.,
 *  expressions that take an operand of one type and
 *  convert the resulting value in to a different type).
 */
public abstract class CastExpr extends Expr {

    /** The operand for the cast.
     */
    protected Expr exp;

    /** Default constructor.
     */
    public CastExpr(Expr exp) {
        this.exp = exp;
    }
}
