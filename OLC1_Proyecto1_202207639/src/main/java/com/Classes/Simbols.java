package com.Classes;

import java.util.ArrayList;

public class Simbols {
    private String name;
    private String type;
    private String Svalue;
    private float Fvalue;
    private ArrayList<String> ASvalue;
    private ArrayList<Float> AFvalue;
    private int line;
    private int column;

    private Simbols(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.Svalue = builder.Svalue;
        this.Fvalue = builder.Fvalue;
        this.ASvalue = builder.ASvalue;
        this.AFvalue = builder.AFvalue;
        this.line = builder.line;
        this.column = builder.column;
    }

    public static class Builder {
        private String name;
        private String type;
        private String Svalue;
        private float Fvalue;
        private ArrayList<String> ASvalue;
        private ArrayList<Float> AFvalue;
        private int line;
        private int column;

        public Builder(String name, String type, int line, int column) {
            this.name = name;
            this.type = type;
            this.line = line;
            this.column = column;
        }

        public Builder setSvalue(String Svalue) {
            this.Svalue = Svalue;
            return this;
        }

        public Builder setFvalue(float Fvalue) {
            this.Fvalue = Fvalue;
            return this;
        }

        public Builder setASvalue(ArrayList<String> ASvalue) {
            this.ASvalue = ASvalue;
            return this;
        }

        public Builder setAFvalue(ArrayList<Float> AFvalue) {
            this.AFvalue = AFvalue;
            return this;
        }

        public Simbols build() {
            return new Simbols(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSvalue() {
        return Svalue;
    }

    public void setSvalue(String svalue) {
        Svalue = svalue;
    }

    public float getFvalue() {
        return Fvalue;
    }

    public void setFvalue(float fvalue) {
        Fvalue = fvalue;
    }

    public ArrayList<String> getASvalue() {
        return ASvalue;
    }

    public void setASvalue(ArrayList<String> ASvalue) {
        this.ASvalue = ASvalue;
    }

    public ArrayList<Float> getAFvalue() {
        return AFvalue;
    }

    public void setAFvalue(ArrayList<Float> AFvalue) {
        this.AFvalue = AFvalue;
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Simbols{");
        if (name != null) {
            result.append("name='").append(name).append("', ");
        }
        if (type != null) {
            result.append("type='").append(type).append("', ");
        }
        if (Svalue != null) {
            result.append("Svalue='").append(Svalue).append("', ");
        }
        if (Fvalue != 0.0f) {
            result.append("Fvalue=").append(Fvalue).append(", ");
        }
        if (ASvalue != null) {
            result.append("ASvalue=").append(ASvalue).append(", ");
        }
        if (AFvalue != null) {
            result.append("AFvalue=").append(AFvalue).append(", ");
        }
        result.append("line=").append(line).append(", ");
        result.append("column=").append(column);
        result.append('}');
        return result.toString();
    }
}