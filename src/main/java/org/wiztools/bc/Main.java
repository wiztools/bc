package org.wiztools.bc;

import java.math.BigDecimal;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author subwiz
 */
public class Main {
    
    static BigDecimal compute(String inputStr) {
        ANTLRInputStream input = new ANTLRInputStream(inputStr);
        ExprLexer lexer = new ExprLexer(input);
        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        ExprParser parser = new ExprParser(tokens);
        
        ParseTree tree = parser.prog();
        
        // Visitor:
        MyExprVisitor visitor = new MyExprVisitor();
        BigDecimal out = visitor.visit(tree);
        
        return out;
    }
    
    public static void main(String[] args) {
        
        String consoleInput;
        while((consoleInput=System.console().readLine()) != null) {
            if(consoleInput.trim().isEmpty()) {
                continue;
            } 
            BigDecimal out = compute(consoleInput);
            System.out.println(out);
        }
        
    }
}
