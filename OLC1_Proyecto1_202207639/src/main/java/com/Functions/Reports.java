package com.Functions;

import com.Classes.Token;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Reports {

    public boolean tokensReport(ArrayList<Token> tokens){
        StringBuilder html = new StringBuilder();
        html.append("<table border='1'>");

        html.append("<tr>");
        html.append("<th>").append("Id").append("</th>");
        html.append("<th>").append("Line").append("</th>");
        html.append("<th>").append("Column").append("</th>");
        html.append("<th>").append("Lexeme").append("</th>");
        html.append("<th>").append("Regular Expression").append("</th>");
        html.append("<th>").append("Token Type").append("</th>");
        html.append("</tr>");

        for (Token token : tokens){
            html.append("<tr>");
            html.append("<th>").append(token.getId()).append("</th>");
            html.append("<th>").append(token.getLine()).append("</th>");
            html.append("<th>").append(token.getColumn()).append("</th>");
            html.append("<th>").append(token.getLexeme()).append("</th>");
            html.append("<th>").append(token.getRegularExpression()).append("</th>");
            html.append("<th>").append(token.getTokenType()).append("</th>");
            html.append("</tr>");
        }

        html.append("</table>");

        try {
            FileWriter writer = new FileWriter("TablaTokens.html");
            writer.write(html.toString());
            writer.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
