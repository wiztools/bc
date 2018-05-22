package org.wiztools.bc;

import java.math.BigDecimal;
import java.math.MathContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author subwiz
 */
public class MyExprVisitor extends ExprBaseVisitor<BigDecimal> {
    
    private BigDecimal finalOut = null;

    @Override
    public BigDecimal visitNumber(ExprParser.NumberContext ctx) {
        finalOut = new BigDecimal(ctx.NUMBER().getText());
        return finalOut;
    }

    @Override
    public BigDecimal visitMulDiv(ExprParser.MulDivContext ctx) {
        BigDecimal left = visit(ctx.expr(0));
        BigDecimal right = visit(ctx.expr(1));
        if(ctx.op.getType() == ExprParser.MUL) {
            finalOut = left.multiply(right);
        }
        else {
            finalOut = left.divide(right, MathContext.DECIMAL128);
        }
        return finalOut;
    }

    @Override
    public BigDecimal visitAddSub(ExprParser.AddSubContext ctx) {
        BigDecimal left = visit(ctx.expr(0));
        BigDecimal right = visit(ctx.expr(1));
        if(ctx.op.getType() == ExprParser.ADD) {
            finalOut = left.add(right);
        }
        else {
            finalOut = left.subtract(right);
        }
        return finalOut;
    }

    @Override
    public BigDecimal visitPow(ExprParser.PowContext ctx) {
        BigDecimal left = visit(ctx.expr(0));
        BigDecimal right = visit(ctx.expr(1));
        try {
            finalOut = left.pow(right.intValueExact());
        } catch (ArithmeticException ex) {
            System.err.printf(
                "Power value not an integer: %s. bc supports only integer. Sorry :-(\n", right
            );
            throw new RuntimeException(ex);
        }
        return finalOut;
    }

    @Override
    public BigDecimal visitParens(ExprParser.ParensContext ctx) {
        finalOut = visit(ctx.expr());
        return finalOut;
    }

    @Override
    public BigDecimal visit(ParseTree pt) {
        super.visit(pt);
        return finalOut;
    }
    
}
