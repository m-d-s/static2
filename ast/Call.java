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

    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        FunctionEnv env = ctxt.functions.find(name, ctxt.functions);
        Function function;
        if( null == env) {
            ctxt.report( new Failure("FunctionDefined") );
        }
        
        /*
        function = env.getFunction;
        
        while(decl == null && functions != null) {
            decl = functions
        }
      
        TypeEnv inner = locals;
        for (int i=0; i<args.length; i++) {
            inner = args[i].check(ctxt, inner);
        }
        */
       return locals; 
    }

}
