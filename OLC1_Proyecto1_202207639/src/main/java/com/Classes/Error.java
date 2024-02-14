package com.Classes;

public class Error {
    private int id;
    private int line;
    private int column;
    private String character;
    private String ErrorType;
    private String Description;

    public Error(int id, int line, int column, String character, String errorType, String description) {
        this.id = id;
        this.line = line;
        this.column = column;
        this.character = character;
        this.ErrorType = errorType;
        this.Description = description;
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

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getErrorType() {
        return ErrorType;
    }

    public void setErrorType(String errorType) {
        ErrorType = errorType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Error{" +
                "id=" + id +
                ", line=" + line +
                ", column=" + column +
                ", character='" + character + '\'' +
                ", ErrorType='" + ErrorType + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
