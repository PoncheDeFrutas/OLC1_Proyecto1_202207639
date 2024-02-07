package com.Functions;

import com.Classes.Error;
import com.Classes.Token;
import com.Classes.TokenConstant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Reports {

    private ArrayList<Error> errors = new ArrayList<Error>();

    public boolean tokensReport(ArrayList<Token> tokens) {
        StringBuilder html = new StringBuilder();
        html.append("<table style='border-collapse: collapse; width: 100%; text-align: center;'>");

        html.append("<tr style='background-color: #f2f2f2;'>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Id").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Line").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Column").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Lexeme").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Regular Expression").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Token Type").append("</th>");
        html.append("</tr>");

        for (Token token : tokens) {
            if (token.getTokenType() != TokenConstant.ERROR) {
                html.append("<tr>");
                html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(token.getId()).append("</td>");
                html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(token.getLine()).append("</td>");
                html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(token.getColumn()).append("</td>");
                html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(token.getLexeme()).append("</td>");
                html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(token.getRegularExpression()).append("</td>");
                html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(token.getTokenType()).append("</td>");
                html.append("</tr>");
            } else {
                errors.add(new Error(errors.size(), token.getLine(), token.getColumn(), token.getLexeme(), "Lexical Error", "The token does not match with any regular expression"));
            }

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

    public boolean errorsReport() {
        StringBuilder html = new StringBuilder();
        html.append("<table style='border-collapse: collapse; width: 100%; text-align: center;'>");

        html.append("<tr style='background-color: #f2f2f2;'>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Id").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Line").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Column").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Lexeme").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Type").append("</th>");
        html.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append("Description").append("</th>");
        html.append("</tr>");

        for (Error error : errors) {
            html.append("<tr>");
            html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(error.getId()).append("</td>");
            html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(error.getLine()).append("</td>");
            html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(error.getColumn()).append("</td>");
            html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(error.getCharacter()).append("</td>");
            html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(error.getErrorType()).append("</td>");
            html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(error.getDescription()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");

        try {
            FileWriter writer = new FileWriter("TablaErrores.html");
            writer.write(html.toString());
            writer.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
