package com.mbn.compiler.lexer;

@SuppressWarnings("ClassCanBeRecord")
public class NumberToken implements Token {

    private final double num;

    public NumberToken(double num) {
        this.num = num;
    }

    @Override
    public TokenType type() {
        return TokenType.TT_NUMBER;
    }

    public double getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "num=" + num +
                '}';
    }
}
