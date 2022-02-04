package com.mbn.interpreter;

import com.mbn.compiler.lexer.TokenType;
import com.mbn.compiler.parser.Node;
import com.mbn.compiler.parser.Nodes;

public class Interpreter {

    private final Node ast;

    public Interpreter(Node ast) {
        this.ast = ast;
    }

    public double solve() {
        return visit_node(ast);
    }

    private double visit_node(Node ast) {
        return switch (ast.type()) {
            case NT_EXPR -> visit_expr_node((Nodes.ExprNode) ast);
            case NT_TERM -> visit_term_node((Nodes.TermNode) ast);
            case NT_FACTOR -> visit_factor_node((Nodes.FactorNode) ast);
        };
    }

    private double visit_factor_node(Nodes.FactorNode node) {
        return switch (node.getFactorType()) {
            case MINUS -> -visit_node((Node) node.getValue());
            case PAREN -> visit_node((Node) node.getValue());
            case NUMBER -> (double) node.getValue();
        };
    }

    private double visit_term_node(Nodes.TermNode node) {
        double left = visit_node(node.getLeft());
        double right = visit_node(node.getRight());
        if (node.getOp() == TokenType.TT_MULTI) {
            return left * right;
        } else {
            return left / right;
        }
    }

    private double visit_expr_node(Nodes.ExprNode node) {
        double left = visit_node(node.getLeft());
        double right = visit_node(node.getRight());
        if (node.getOp() == TokenType.TT_PLUS) {
            return left + right;
        } else {
            return left - right;
        }
    }

}
