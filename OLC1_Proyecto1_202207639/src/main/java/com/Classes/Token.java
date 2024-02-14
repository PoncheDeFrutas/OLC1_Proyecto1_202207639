package com.Classes;

public class Token {
    private int id;
    private int line;
    private int column;
    private String lexeme;
    private String regularExpression;
    private TokenConstant tokenType;

    public Token(int id, int line, int column, String lexeme, String regularExpression, TokenConstant tokenType) {
        this.id = id;
        this.line = line;
        this.column = column;
        this.lexeme = lexeme;
        this.regularExpression = regularExpression;
        this.tokenType = tokenType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public TokenConstant getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenConstant tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", line=" + line +
                ", column=" + column +
                ", lexeme='" + lexeme + '\'' +
                ", regularExpression='" + regularExpression + '\'' +
                ", tokenType=" + tokenType +
                '}';
    }
}
