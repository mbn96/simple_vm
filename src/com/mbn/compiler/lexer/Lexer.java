package com.mbn.compiler.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private static final String DIGITS = "0123456789";
    private static final String DIGITS_DOT = DIGITS + '.';
    private static final String WHITE_SPACE = " \t";


    private final String src;
    private char currentChar;
    private int currentPos = -1;
    private final List<Token> tokens = new ArrayList<>();


    public Lexer(String src) {
        this.src = src;
        advance();
    }

    private void advance() {
        currentPos++;
        if (currentPos < src.length()) {
            currentChar = src.charAt(currentPos);
        } else {
            currentChar = '\0';
        }
    }

    private void appendToken(Token token) {
        tokens.add(token);
    }

    public List<Token> getTokens() {
        while (currentChar != '\0') {
            if (currentChar == '+') {
                appendToken(new SimpleToken(TokenType.TT_PLUS));
                advance();
            } else if (currentChar == '-') {
                appendToken(new SimpleToken(TokenType.TT_MINUS));
                advance();
            } else if (currentChar == '*') {
                appendToken(new SimpleToken(TokenType.TT_MULTI));
                advance();
            } else if (currentChar == '/') {
                appendToken(new SimpleToken(TokenType.TT_DIV));
                advance();
            } else if (currentChar == '(') {
                appendToken(new SimpleToken(TokenType.TT_L_PAREN));
                advance();
            } else if (currentChar == ')') {
                appendToken(new SimpleToken(TokenType.TT_R_PAREN));
                advance();
            } else if (DIGITS.indexOf(currentChar) != -1) {
                getNumberToken();
            } else if (WHITE_SPACE.indexOf(currentChar) != -1) {
                advance();
            } else {
                throw new IllegalArgumentException("Char " + currentChar + " is not supported.");
            }
        }

        appendToken(new SimpleToken(TokenType.TT_EOF));
        return tokens;
    }

    private void getNumberToken() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentChar);
        advance();

        int dots = 0;

        while (currentChar != '\0' && DIGITS_DOT.indexOf(currentChar) != -1) {
            stringBuilder.append(currentChar);
            if (currentChar == '.') dots++;
            advance();
        }

        if (dots > 1) {
            throw new IllegalArgumentException("Token " + stringBuilder + " has more than one dots.");
        }

        appendToken(new NumberToken(Double.parseDouble(stringBuilder.toString())));
    }


}
