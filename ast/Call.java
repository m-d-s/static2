package ast;
import compiler.Failure;

public class Call extends StmtExpr {

    /** The name of the function that is being called.
     */
    private String name;

    /** The sequence of expressions provided as arguments.
     */
    private Expr[] args;

    /** Default constructor.
     */
    public Call(String name, Expr[] args) {
        this.name = name;
        this.args = args;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Call");
        out.indent(n+1, "\"" + name + "\"");
        for (int i=0; i<args.length; i++) {
            args[i].indent(out, n+1);
        }
    }

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */ 
     public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        //use 'name' to retrieve the function environment
        FunctionEnv env = ctxt.functions.find(this.name, ctxt.functions);
        Function function;
        
        //if the function was declared in the environment
        if( null != env) {
            function = env.getFunction();
            //compare the argument list and return the functions type
            return function.compareParams(this.args, ctxt, locals);
        }        
        
        throw new Failure("FunctionDefined");
        
    }

}
