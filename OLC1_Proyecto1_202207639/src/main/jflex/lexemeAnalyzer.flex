package com.Analyzer;

import java.io.*;
import java.util.ArrayList;
import com.Classes.Token;
import com.Classes.TokenConstant;

%%
%public
%class LexemeAnalyzer

%line
%column

%{
    public ArrayList<Token> tokens = new ArrayList<Token>();
%}

%function next_token
%{
    StringBuffer buffer = new StringBuffer();
%}

%type Token
%eofval{
    return new Token(tokens.size(),-1, -1, null,null,TokenConstant.EOF);
%eofval}

JUMP = [\n]
DIGIT = [0-9]
LETTER = [a-zA-Z]
WHITESPACE = [ \t\r]

AT = "@"
DOT = "."
SMALLER = "<"
GREATHER = ">"
EXCLAMATION = "!"
QUOT = "\"" | "&quot;"
LQUOT = "\“" | "&ldquo;"
RQUOT =  "\”"| "&rdquo;"


/*EXPRESIONES REGULARES*/
ARRAY = {AT}{IDENTIFIER}
STRING = ({QUOT}|{LQUOT}) ~({QUOT}|{RQUOT})
DOUBLE = {DIGIT}+({DOT}{DIGIT}+)?
IDENTIFIER = {LETTER}({LETTER}|{DIGIT})*
SIMPLE_COMMENT = {EXCLAMATION} .* {JUMP}
MULTI_COMMENT = {SMALLER}{EXCLAMATION}( . | {JUMP})*{EXCLAMATION}{GREATHER}

%%

/*SIMBOLOS*/

"=" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"=",TokenConstant.EQUAL));
    return tokens.get(tokens.size()-1);}

"(" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"(",TokenConstant.LPAREN));
    return tokens.get(tokens.size()-1);}

")" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),")",TokenConstant.RPAREN));
    return tokens.get(tokens.size()-1);}

"[" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"[",TokenConstant.LBRACKET));
    return tokens.get(tokens.size()-1);}

"]" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"]",TokenConstant.RBRACKET));
    return tokens.get(tokens.size()-1);}

"," {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),",",TokenConstant.COMMA));
    return tokens.get(tokens.size()-1);}

";" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),";",TokenConstant.SEMICOLON));
    return tokens.get(tokens.size()-1);}

":" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),":",TokenConstant.COLON));
    return tokens.get(tokens.size()-1);}

"::" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"::",TokenConstant.DOUBLECOLON));
    return tokens.get(tokens.size()-1);}

"<-" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"<-",TokenConstant.LARROW));
    return tokens.get(tokens.size()-1);}

"->" {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"->",TokenConstant.RARROW));
    return tokens.get(tokens.size()-1);}


/*PALABRAS RESERVADAS*/
"PROGRAM" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"PROGRAM",TokenConstant.PROGRAM));
    return tokens.get(tokens.size()-1);}

"END" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"END",TokenConstant.END));
    return tokens.get(tokens.size()-1);}

"END PROGRAM" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"END PROGRAM",TokenConstant.ENDPROGRAM));
    return tokens.get(tokens.size()-1);}

"VAR" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"VAR",TokenConstant.VAR));
    return tokens.get(tokens.size()-1);}

"ARR" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"ARR",TokenConstant.ARR));
    return tokens.get(tokens.size()-1);}

/*TIPOS DE DATOS*/
"CHAR[]" | "DOUBLE" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"",TokenConstant.DATATYPE));
    return tokens.get(tokens.size()-1);}

/*OPERACIONES ARITMETICAS*/
"SUM" | "RES" | "MUL" | "DIV" | "MOD" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"",TokenConstant.ARITFUNC));
    return tokens.get(tokens.size()-1);}

/*OPERACIONES ESTADISTICAS*/
"MEDIA" | "MEDIANA" | "MODA" | "VARIANZA" | "MAX" | "MIN" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"",TokenConstant.ESTFUNC));
    return tokens.get(tokens.size()-1);}

/*IMPRESION DE EXPRESIONES*/
"CONSOLE" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"CONSOLE",TokenConstant.CONSOLE));
    return tokens.get(tokens.size()-1);}

"PRINT" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"PRINT",TokenConstant.PRINT));
    return tokens.get(tokens.size()-1);}

"COLUMN" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"COLUMN",TokenConstant.COLUMN));
    return tokens.get(tokens.size()-1);}

/*FUNCIONES DE GRAFICACION*/
"GRAPHBAR" | "GRAPHLINE" | "GRAPHPIE" | "HISTOGRAM" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"GRAPHBAR",TokenConstant.GRAPHTYPE));
    return tokens.get(tokens.size()-1);}

"TITULO" | "TITULOX" | "TITULOY" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"TITULO",TokenConstant.TITLE));
    return tokens.get(tokens.size()-1);}

"EJEX" | "EJEY" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"EJES",TokenConstant.EJES));
    return tokens.get(tokens.size()-1);}

"VALUES" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"VALUES",TokenConstant.VALUES));
    return tokens.get(tokens.size()-1);}

"LABEL" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"LABEL",TokenConstant.LABEL));
    return tokens.get(tokens.size()-1);}

"EXEC" {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"EXEC",TokenConstant.EXEC));
    return tokens.get(tokens.size()-1);}



<YYINITIAL>{WHITESPACE} {/*Ignore*/}
<YYINITIAL>{SIMPLE_COMMENT} {/*Ignore*/}
<YYINITIAL>{MULTI_COMMENT} {/*Ignore*/}

<YYINITIAL>{JUMP} {
    yyline++;
    yycolumn = 0;}

<YYINITIAL>{IDENTIFIER} {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"L(L|D)*",TokenConstant.ID));
    return tokens.get(tokens.size()-1);}

<YYINITIAL>{DOUBLE} {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"D(P|D+)?",TokenConstant.NUM));
    return tokens.get(tokens.size()-1);}

<YYINITIAL>{STRING} {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext().replaceAll("[\"“”]", ""),"\".*\"",TokenConstant.STRING));
    return tokens.get(tokens.size()-1);}

<YYINITIAL>{ARRAY} {
    tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"@L(L|D)*",TokenConstant.ARRAY));
    return tokens.get(tokens.size()-1);}

[^] {tokens.add(new Token(tokens.size(),yyline, yycolumn, yytext(),"",TokenConstant.ERROR));
    return tokens.get(tokens.size()-1);}
