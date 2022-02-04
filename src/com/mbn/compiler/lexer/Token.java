package com.mbn.compiler.lexer;

public interface Token {
    TokenType type();

    default boolean isOneOf(TokenType... types) {
        if (types.length == 1) return type() == types[0];

        for (TokenType t : types) {
            if (t == type()) return true;
        }
        return false;
    }
}
