package com.Classes;

public class Intruccions {
    private int line, column;
    private String lexeme;

    public Intruccions(int line, int column, String lexeme) {
        this.line = line;
        this.column = column;
        this.lexeme = lexeme;
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
}
