package org.wiztools.bc;

import java.math.BigDecimal;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author subwiz
 */
public class MyExprVisitor extends ExprBaseVisitor<BigDecimal> {
    
    private BigDecimal finalOut = null;

    @Override
    public BigDecimal visitNumber(ExprParser.NumberContext ctx) {
        BigDecimal out = new BigDecimal(ctx.NUMBER().getText());
        finalOut = out;
        return out;
    }

    @Override
    public BigDecimal visitMulDiv(ExprParser.MulDivContext ctx) {
        BigDecimal left = visit(ctx.expr(0));
        BigDecimal right = visit(ctx.expr(1));
        if(ctx.op.getType() == ExprParser.MUL) {
            BigDecimal out = left.multiply(right);
            finalOut = out;
            return out;
        }
        else {
            BigDecimal out = left.divide(right);
            finalOut = out;
            return out;
        }
    }

    @Override
    public BigDecimal visitAddSub(ExprParser.AddSubContext ctx) {
        BigDecimal left = visit(ctx.expr(0));
        BigDecimal right = visit(ctx.expr(1));
        if(ctx.op.getType() == ExprParser.ADD) {
            BigDecimal out = left.add(right);
            finalOut = out;
            return out;
        }
        else {
            BigDecimal out = left.subtract(right);
            finalOut = out;
            return out;
        }
    }

    @Override
    public BigDecimal visitParens(ExprParser.ParensContext ctx) {
        BigDecimal out = visit(ctx.expr());
        finalOut = out;
        return out;
    }

    @Override
    public BigDecimal visit(ParseTree pt) {
        super.visit(pt);
        return finalOut;
    }
    
}
