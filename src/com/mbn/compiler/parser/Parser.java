package com.mbn.compiler.parser;

import com.mbn.compiler.lexer.NumberToken;
import com.mbn.compiler.lexer.Token;
import com.mbn.compiler.lexer.TokenType;

import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int currentPos = -1;
    private Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        advance();
    }

    private void advance() {
        currentPos++;
        if (currentPos < tokens.size()) {
            currentToken = tokens.get(currentPos);
        } else {
            currentToken = null;
        }
    }

    private void moveBack() {
        currentPos--;
        if (currentPos < tokens.size() && currentPos >= 0) {
            currentToken = tokens.get(currentPos);
        } else {
            currentToken = null;
        }
    }

    public Node parse() {
        Node node = expr();

        if (currentToken != null && currentToken.type() != TokenType.TT_EOF) {
            throw new IllegalStateException("Illegal syntax: " + node);
        }

        return node;
    }

    private Node expr() {
        Node node = term();

        while (currentToken != null && currentToken.isOneOf(TokenType.TT_PLUS, TokenType.TT_MINUS)) {
            TokenType type = currentToken.type();
            advance();
            node = new Nodes.ExprNode(node, term(), type);
        }

        return node;
    }

    private Node term() {
        Node node = factor();

        while (currentToken != null && currentToken.isOneOf(TokenType.TT_DIV, TokenType.TT_MULTI)) {
            TokenType type = currentToken.type();
            advance();
            node = new Nodes.TermNode(node, factor(), type);
        }

        return node;
    }

    private Node factor() {
        Node node;
        if (currentToken.isOneOf(TokenType.TT_NUMBER)) {
            node = new Nodes.FactorNode(Nodes.FactorType.NUMBER, ((NumberToken) currentToken).getNum());
            advance();
        } else if (currentToken.isOneOf(TokenType.TT_MINUS)) {
            advance();
            node = new Nodes.FactorNode(Nodes.FactorType.MINUS, factor());
        } else if (currentToken.isOneOf(TokenType.TT_L_PAREN)) {
            advance();
//            node = new Nodes.FactorNode(Nodes.FactorType.PAREN, expr());
            node = expr();
            if (currentToken == null || (!currentToken.isOneOf(TokenType.TT_R_PAREN))) {
                throw new IllegalStateException("Illegal syntax: no closing paren.");
            }
            advance();
        } else {
            throw new IllegalStateException("Illegal syntax: Current token '" + currentToken + "' is not supported for factor.");
        }

        return node;
    }
}
