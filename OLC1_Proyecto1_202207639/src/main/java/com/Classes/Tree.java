package com.Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {

    private boolean fail;
    private int line, column;
    private String lexeme;
    private ArrayList<Tree> children;
    private ArrayList<Intruccions> Instruccions = new ArrayList<Intruccions>();

    private List<String> Ignore = Arrays.asList("SP", "ST", "TD", "D", "VD", "AD", "AV", "EXL", "EXS", "FC",
            "CD", "PS", "GS", "GP", "PL", "P", "EX", "EXC", "CS", "ERR");

    public Tree(String lexeme, int line, int column){
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
        this.children = new ArrayList<>();
    }

    public void addChild(Tree child){
        this.children.add(child);
    }

    public void printTree(Tree seed){
        for (Tree child : seed.children){
            this.printTree(child);
        }
        System.out.println(seed.lexeme);
    }

    public void saveTree(Tree seed){
        if (seed == null) {
            return;
        } else if(seed.lexeme.equals("ERR")){
            return;
        }
        for (Tree child : seed.children){
            this.saveTree(child);
        }
        if (!Ignore.contains(seed.lexeme)){
            Instruccions.add(new Intruccions(seed.line, seed.column, seed.lexeme));
        }
    }

    public ArrayList<Intruccions> getInstruccions(Tree seed){
        this.Instruccions = new ArrayList<Intruccions>();
        saveTree(seed);
        return this.Instruccions;
    }

    public void printInstruccions(){
        for (Intruccions instruccion : Instruccions){
            System.out.println(instruccion.getLexeme() + " " + instruccion.getLine() + " " + instruccion.getColumn());
        }
    }


}