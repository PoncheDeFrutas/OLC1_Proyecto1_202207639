package com.Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {

    private String lexeme;
    private ArrayList<Tree> children;
    private ArrayList<String> Instruccions = new ArrayList<String>();

    private List<String> Ignore = Arrays.asList("SP", "ST", "TD", "D", "VD", "AD", "AV", "EXL", "EXS", "FC",
            "CD", "PS", "GS", "GP", "PL", "P", "EX", "EXC", "CS");

    public Tree(String lexeme){
        this.lexeme = lexeme;
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
        for (Tree child : seed.children){
            this.saveTree(child);
        }
        if (!Ignore.contains(seed.lexeme)) {
            Instruccions.add(seed.lexeme);
        }
    }

    public void printInstruccions(){
        for (String instruccion : Instruccions){
            System.out.println(instruccion);
        }
    }

    public ArrayList<String> getInstruccions(Tree seed){
        this.Instruccions = new ArrayList<String>();
        saveTree(seed);
        return this.Instruccions;
    }
}