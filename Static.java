//- CS 321 Languages and Compiler Design I ----------------------------
//
//  Test program for Homework 4, static analysis.  The program below
//  uses the parser from Homework 3, together with new code added in
//  this assignment, to perform both syntax and static analysis on a
//  given source program.

import ast.*;
import compiler.*;

public class Static {
    public static void main(String[] args) {
        Handler handler = new Handler();
        Defn[]  program = null;
        new Parser(System.in);
        try {
            // Parse an input program:
            program = Parser.Top();

            // Run static analysis on the program:
            new Context(handler).check(program);

        } catch (ParseException e) {
            handler.report(new Failure("Syntax Error"));
        } catch (Failure f) {
            handler.report(f);
        }

        // Output the annotated abstract syntax tree:
        if (handler.hasErrors()) {
            handler.dump();
        } else {
            new IndentOutput(System.out).indent(program);
        }
    }
}

//---------------------------------------------------------------------
