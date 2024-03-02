package com.Classes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Tree {
    private String lexeme;
    private ArrayList<Tree> children;

    public Tree(String lexeme){
        this.lexeme = lexeme;
        this.children = new ArrayList<>();
    }

    public void addChild(Tree child){
        this.children.add(child);
    }

    public void printTree(Tree seed){
        for (Tree child : seed.children){
            printTree(child);
        }
        System.out.println(seed.lexeme);
    }
}
