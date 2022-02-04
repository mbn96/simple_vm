package com.mbn.compiler.lexer;

@SuppressWarnings("ClassCanBeRecord")
public class SimpleToken implements Token {
    private final TokenType type;

    public SimpleToken(TokenType type) {
        this.type = type;
    }

    @Override
    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return "SimpleToken{" +
                "type=" + type +
                '}';
    }
}
