package com.mbn.compiler.parser;

import com.mbn.compiler.lexer.TokenType;

public class Nodes {

    public static class ExprNode implements Node {
        private final Node left;
        private final Node right;
        private final TokenType op;

        public ExprNode(Node left, Node right, TokenType op) {
            if ((op != TokenType.TT_PLUS) && (op != TokenType.TT_MINUS)) {
                throw new IllegalArgumentException("ExprNode cannot have operators other than (PLUS|MINUS).");
            }

            this.left = left;
            this.right = right;
            this.op = op;
        }

        @Override
        public NodeType type() {
            return NodeType.NT_EXPR;
        }


        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public TokenType getOp() {
            return op;
        }

        @Override
        public String toString() {
            return "ExprNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", op=" + op +
                    '}';
        }
    }

    public static class TermNode implements Node {
        private final Node left;
        private final Node right;
        private final TokenType op;

        public TermNode(Node left, Node right, TokenType op) {
            if ((op != TokenType.TT_MULTI) && (op != TokenType.TT_DIV)) {
                throw new IllegalArgumentException("TermNode cannot have operators other than (MULTI|DIV).");
            }

            this.left = left;
            this.right = right;
            this.op = op;
        }

        @Override
        public NodeType type() {
            return NodeType.NT_TERM;
        }


        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public TokenType getOp() {
            return op;
        }

        @Override
        public String toString() {
            return "TermNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", op=" + op +
                    '}';
        }
    }

    public enum FactorType {
        MINUS,
        PAREN,
        NUMBER
    }

    public static class FactorNode implements Node {

        private final FactorType factorType;
        private final Object value;

        public FactorNode(FactorType factorType, Object value) {
            this.factorType = factorType;
            this.value = value;
        }

        @Override
        public NodeType type() {
            return NodeType.NT_FACTOR;
        }

        public FactorType getFactorType() {
            return factorType;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "FactorNode{" +
                    "factorType=" + factorType +
                    ", value=" + value +
                    '}';
        }
    }

}
